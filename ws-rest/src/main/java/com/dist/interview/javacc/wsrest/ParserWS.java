package com.dist.interview.javacc.wsrest;

import com.dist.interview.javacc.infra.exception.ValidationException;
import com.dist.interview.javacc.infra.model.Candidate;
import com.dist.interview.javacc.infra.model.EmailRequest;
import com.dist.interview.javacc.infra.model.ParsedQuery;
import com.dist.interview.javacc.infra.model.ParserInput;
import com.dist.interview.javacc.serviceapi.InterviewParserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parser")
public class ParserWS {
    @Autowired
    private InterviewParserModule parserModule;
    @PostMapping("/find-parsed-candidates")
    public List<Candidate> findCandidates(@RequestBody ParsedQuery parsedQuery) {
        return parserModule.findCandidatesByParsedQuery(parsedQuery);
    }
    @PostMapping("/parse")
    public ParsedQuery parse(@RequestBody ParserInput input) {
        return parserModule.parseQuery(input);
    }
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            parserModule.sendEmail(emailRequest);
            return ResponseEntity.ok("Email sent successfully to " + emailRequest.getTo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
    @ExceptionHandler({IllegalArgumentException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return errorResponse;
    }
}
