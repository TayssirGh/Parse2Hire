//package com.dist.interview.javacc.wsrest;
//
//import com.dist.interview.javacc.infra.model.Criteria;
//import com.dist.interview.javacc.serviceapi.InterviewParserModule;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/criteria")
//public class CriteriaWS {
//    @Autowired
//    private InterviewParserModule parserModule;
//    @PostMapping("/add-criteria")
//    public Criteria addCriteria(@RequestBody Criteria criteria) {
//        return parserModule.addCriteria(criteria);
//    }
//    @GetMapping("/find-criterias")
//    public List<Criteria> findCriteria() {
//        return parserModule.findAllCriterias().get();
//    }
//    @GetMapping("/test")
//    public String hello() {
//        return "Congratulations you did it \uD83C\uDF89\uD83C\uDF89";
//    }
//    @DeleteMapping("/delete/{id}")
//    public String  removeCriteria(@RequestBody Criteria criteria) {
//        return parserModule.deleteCriteria(criteria.getId()).get();
//    }
//}
