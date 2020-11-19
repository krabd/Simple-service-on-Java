package com.krab.rest.camunda;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;

@ProcessApplication("Dinner App DMN")
public class DinnerApplication  extends ServletProcessApplication {
}
