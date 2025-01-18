package com.distr.interview.javacc.serviceimpl.service;

import com.distr.interview.javacc.serviceimpl.entity.CandidateService;
import com.distr.interview.javacc.serviceimpl.entity.InterviewService;
import com.distr.interview.javacc.serviceimpl.service.parser.QueryParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)

class ParserServiceTest {
    @InjectMocks
    private ParserService parserService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private InterviewService interviewService;

    @Mock
    private CandidateService candidateService;

    @Mock
    private QueryParser queryParser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testPopulateTemplate_ThrowsIOException() {
        String templateName = "invalidTemplate.txt";
        Map<String, String> placeholders = new HashMap<>();

        assertThrows(IllegalArgumentException.class,
                () -> parserService.populateTemplate(templateName, placeholders));
    }

}