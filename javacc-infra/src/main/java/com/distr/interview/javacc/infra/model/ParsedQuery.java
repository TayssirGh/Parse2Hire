package com.distr.interview.javacc.infra.model;

import lombok.Data;

import java.util.List;
@Data
public class ParsedQuery {
    private String templateName;
    private List<Condition> conditions ;
}
