package com.dist.interview.javacc.wsrest;

import com.dist.interview.javacc.infra.model.EmailRequest;
import com.dist.interview.javacc.serviceapi.InterviewParserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parser")
public class ParserWS {
    @Autowired
    private InterviewParserModule interviewParserModule;
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            interviewParserModule.sendEmail(emailRequest);
            return ResponseEntity.ok("Email sent successfully to " + emailRequest.getTo());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}
