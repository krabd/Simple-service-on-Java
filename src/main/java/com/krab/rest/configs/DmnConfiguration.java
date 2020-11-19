package com.krab.rest.configs;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class DmnConfiguration {

    private final ResourceLoader resourceLoader;

    @Autowired
    private DmnEngine dmnEngine;

    @Autowired
    public DmnConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DmnEngine createDmnEngine() {
        DmnEngineConfiguration configuration = DmnEngineConfiguration
                .createDefaultDmnEngineConfiguration();

        DmnEngine dmnEngine = configuration.buildEngine();
        return dmnEngine;
    }

    @Bean(name = "test")
    public DmnDecision createDecisionTest() {
        Resource resource = resourceLoader.getResource("classpath:cafeDmn.dmn");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dmnEngine.parseDecision("cafeDis", is);
    }
}
