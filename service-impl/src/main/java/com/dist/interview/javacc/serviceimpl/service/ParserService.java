package com.dist.interview.javacc.serviceimpl.service;

import com.dist.interview.javacc.dal.mongodb.entity.CandidateEntity;
import com.dist.interview.javacc.infra.exception.ValidationException;
import com.dist.interview.javacc.infra.model.*;
import com.dist.interview.javacc.serviceimpl.converter.CandidateConverter;
import com.dist.interview.javacc.serviceimpl.service.parser.ParseException;
import com.dist.interview.javacc.serviceimpl.service.parser.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParserService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MongoTemplate mongoTemplate;
    public ParsedQuery parseQuery(ParserInput query) {
        QueryParser parser = new QueryParser(new java.io.StringReader(query.getSyntax()));
        try {
            ParsedQuery parsedQuery = parser.statement();
            validateQuery(parsedQuery);
            return parsedQuery;
        } catch (ParseException | ValidationException e) {
            throw new IllegalArgumentException("Failed to parse query: " + e.getMessage());
        }
    }
    public void validateQuery(ParsedQuery query) throws ValidationException {
        if (query.getTemplateName() == null || query.getTemplateName().trim().isEmpty()) {
            throw new ValidationException("Template name must not be null or empty.");
        }

        if (query.getConditions() == null || query.getConditions().isEmpty()) {
            throw new ValidationException("Conditions must not be null or empty.");
        }

        for (Condition condition : query.getConditions()) {
            validateCondition(condition);
        }
    }

    private void validateCondition(Condition condition) throws ValidationException {
        if (condition.getLeftOperand() != null && condition.getOperator() != null && condition.getRightOperand() != null) {
            validateOperand(condition.getLeftOperand());
            validateOperand(condition.getRightOperand());
            if (condition.getOperator() == null) {
                throw new ValidationException("Operator in condition must not be null.");
            }
        } else if (condition.getLeft() != null && condition.getLogicalOperator() != null && condition.getRight() != null) {
            validateCondition(condition.getLeft());
            validateCondition(condition.getRight());
        } else {
            throw new ValidationException("Condition is incomplete or malformed.");
        }
    }

    private void validateOperand(String operand) throws ValidationException {
        if (operand == null || operand.trim().isEmpty()) {
            throw new ValidationException("Operand must not be null or empty.");
        }
    }

    public List<Candidate> findCandidatesByParsedQuery(ParsedQuery parsedQuery) {
        Criteria criteria = buildCriteriaFromCondition(parsedQuery.getConditions().get(0));
        Query query = new Query(criteria);
        List<CandidateEntity> entities = mongoTemplate.find(query, CandidateEntity.class);
        return CandidateConverter.INSTANCE.fromEntityList(entities);
    }

    private Criteria buildCriteriaFromCondition(Condition condition) {
        if (condition.getLeftOperand() != null && condition.getOperator() != null && condition.getRightOperand() != null) {
            return createSimpleCriteria(condition);
        }

        if (condition.getLogicalOperator() != null) {
            Criteria leftCriteria = buildCriteriaFromCondition(condition.getLeft());
            Criteria rightCriteria = buildCriteriaFromCondition(condition.getRight());

            switch (condition.getLogicalOperator()) {
                case AND:
                    return new Criteria().andOperator(leftCriteria, rightCriteria);
                case OR:
                    return new Criteria().orOperator(leftCriteria, rightCriteria);
                default:
                    throw new IllegalArgumentException("Unsupported logical operator: " + condition.getLogicalOperator());
            }
        }

        throw new IllegalArgumentException("Invalid condition: " + condition);
    }

    private Criteria createSimpleCriteria(Condition condition) {
        String field = condition.getLeftOperand().replace("candidate.", ""); // Remove prefix
        String value = condition.getRightOperand();

        switch (condition.getOperator()) {
            case GT:
                return Criteria.where(field).gt(Double.valueOf(value));
            case GTE:
                return Criteria.where(field).gte(Double.valueOf(value));
            case LT:
                return Criteria.where(field).lt(Double.valueOf(value));
            case LTE:
                return Criteria.where(field).lte(Double.valueOf(value));
            case EQ:
                return Criteria.where(field).is(value);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + condition.getOperator());
        }
    }
    public void sendEmail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
        message.setFrom("tphase10@gmail.com");

        mailSender.send(message);

    }
    public List<Candidate> getQualifiedCandidates(String interviewId) {
        MatchOperation matchCandidates = Aggregation.match(Criteria.where("interviewId").is(interviewId));
        LookupOperation lookupInterviews = Aggregation.lookup("interview", "interviewId", "_id", "interviewDetails");
        UnwindOperation unwindInterviews = Aggregation.unwind("interviewDetails");
        LookupOperation lookupCriteria = Aggregation.lookup("criteria", "interviewDetails.criteriaId", "_id", "criteriaDetails");
        UnwindOperation unwindCriteria = Aggregation.unwind("criteriaDetails");
        MatchOperation matchScore = Aggregation.match(Criteria.where("interviewScore")
                .gt(50.0));
        ProjectionOperation projectFields = Aggregation.project("id", "name", "email", "appliedPosition", "skills",
                "interviewScore", "responseTime", "feedback", "rating", "status", "createdAt");
        Aggregation aggregation = Aggregation.newAggregation(
                matchCandidates,
                lookupInterviews,
                unwindInterviews,
                lookupCriteria,
                unwindCriteria,
                matchScore,
                projectFields
        );
        List<CandidateEntity> entities = mongoTemplate.aggregate(aggregation, "candidate", CandidateEntity.class).getMappedResults();
        return CandidateConverter.INSTANCE.fromEntityList(entities);

    }
}
