package com.dist.interview.javacc.wsrest;

import com.dist.interview.javacc.infra.model.Candidate;
import com.dist.interview.javacc.serviceapi.InterviewParserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("candidate")
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
    @PutMapping("/update-candidate")
    public Candidate updateCandidate(@RequestBody Candidate candidate) {
        return parserModule.updateCandidate(candidate);
    }
    @DeleteMapping("/delete/{id}")
    public String  removeCandidate(@PathVariable String id) {
        return parserModule.deleteCandidate(id).get();
    }

}
