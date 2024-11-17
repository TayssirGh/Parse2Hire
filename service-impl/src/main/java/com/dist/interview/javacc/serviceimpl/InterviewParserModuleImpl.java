package com.dist.interview.javacc.serviceimpl;

import com.dist.interview.javacc.serviceapi.InterviewParserModule;
import com.dist.interview.javacc.serviceimpl.entity.InterviewParserService;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewParserModuleImpl implements InterviewParserModule {
    @Autowired
    @Delegate
    private InterviewParserService service;
}
