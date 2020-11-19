package com.krab.rest.services;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DmnServiceImpl implements DmnService {

    private final DmnEngine dmnEngine;
    private final DmnDecision decision;

    @Autowired
    public DmnServiceImpl(DmnEngine dmnEngine, @Qualifier(value = "test") DmnDecision decision) {
        this.dmnEngine = dmnEngine;
        this.decision = decision;
    }

    public String validateCafe(String season, int guestsCount) {
        VariableMap variables = Variables
                .putValue("season", season)
                .putValue("guestsCount", guestsCount);

        DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, variables);
        return result.getSingleEntry();
    }
}
