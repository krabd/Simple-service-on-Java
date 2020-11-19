package com.krab.rest.camunda;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

@ProcessApplication("Dinner App DMN")
public class DinnerApplication  extends ServletProcessApplication {

    @PostDeploy
    public void evaluateDecisionTable(ProcessEngine processEngine) {

        DecisionService decisionService = processEngine.getDecisionService();

        VariableMap variables = Variables.createVariables()
                .putValue("season", "Summer")
                .putValue("guestsCount", 10);

        DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("cafe", variables);
        String desiredDish = dishDecisionResult.getSingleEntry();

        System.out.println("Desired dish: " + desiredDish);
    }
}
