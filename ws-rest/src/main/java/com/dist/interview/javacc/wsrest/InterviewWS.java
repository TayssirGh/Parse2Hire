package com.dist.interview.javacc.wsrest;

import com.dist.interview.javacc.infra.model.Interview;
import com.dist.interview.javacc.serviceapi.InterviewParserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview")
public class InterviewWS {
    @Autowired
    private InterviewParserModule parserModule;
    @PostMapping("/add-interview")
    public Interview addInterview(@RequestBody Interview interview) {
        return parserModule.addInterview(interview);
    }
    @GetMapping("/find-interviews")
    public List<Interview> findInterview() {
        return parserModule.findAllInterviews().get();
    }
    @GetMapping("/test")
    public String hello() {
        return "Congratulations you did it \uD83C\uDF89\uD83C\uDF89";
    }
    @DeleteMapping("/delete/{id}")
    public String  removeInterview(@PathVariable String id) {
        return parserModule.deleteInterview(id).get();
    }
}
