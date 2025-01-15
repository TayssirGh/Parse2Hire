package com.dist.interview.javacc.serviceimpl;

import com.dist.interview.javacc.serviceapi.InterviewParserModule;
import com.dist.interview.javacc.serviceimpl.service.ParserService;
import com.dist.interview.javacc.serviceimpl.entity.CandidateService;
//import com.dist.interview.javacc.serviceimpl.entity.CriteriaService;
import com.dist.interview.javacc.serviceimpl.entity.InterviewService;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewParserModuleImpl implements InterviewParserModule {
    @Autowired
    @Delegate
    private CandidateService service;
    @Autowired
    @Delegate
    private InterviewService interviewService;
//    @Autowired
//    @Delegate
//    private CriteriaService criteriaService;
    @Autowired
    @Delegate
    private ParserService parserService;
}
