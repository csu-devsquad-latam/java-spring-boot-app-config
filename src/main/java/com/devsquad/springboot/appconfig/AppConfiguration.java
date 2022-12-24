package com.devsquad.springboot.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


enum AzureCredentialsMethod{
    DEFAULT,
    ENVIRONMENT,
}

@Configuration
public class AppConfiguration {

    @Value("${appConfiguration.azureCredentialsMethod}")
    private AzureCredentialsMethod AzureCredentialsMethod;

    @Bean
    @Autowired
    public AzureCredentials azureCredentials() {
        return new AzureCredentials(AzureCredentialsMethod);
    }
}