package com.dist.interview.javacc.wsrest;

import com.dist.interview.javacc.infra.model.Candidate;
import com.dist.interview.javacc.serviceapi.InterviewParserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CandidateWS {
    @Autowired
    private InterviewParserModule parserModule;
    @PostMapping("/add-candidate")
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return parserModule.addCandidate(candidate);
    }
    @GetMapping("/find-candidates")
    public List<Candidate> findCandidate() {
        return parserModule.findAllCandidates().get();
    }
    @GetMapping("/test")
    public String hello() {
        return "Congratulations you did it \uD83C\uDF89\uD83C\uDF89";
    }
    @DeleteMapping("/delete/{id}")
    public String  removeCandidate(@RequestBody Candidate candidate) {
        return parserModule.deleteCandidate(candidate.getId()).get();
    }
}
