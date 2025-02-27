package com.distr.interview.javacc.infra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Condition {
    private Condition left;
    private LogicalOperator logicalOperator;
    private Condition right;
    private String leftOperand;
    private Operator operator;
    private String rightOperand;
    private boolean isParenthesized;

    public Condition(Condition left, LogicalOperator logicalOperator, Condition right, boolean isParenthesized) {
        this.left = left;
        this.logicalOperator = logicalOperator;
        this.right = right;
        this.isParenthesized = isParenthesized;
    }
    public Condition(String leftOperand, Operator operator, String rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
        this.isParenthesized = false;
    }
}
