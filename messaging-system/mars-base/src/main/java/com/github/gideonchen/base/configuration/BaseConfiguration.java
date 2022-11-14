package com.github.gideonchen.base.configuration;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BaseConfiguration {

    @Value("${base.name}")
    private String STATION_NAME;
}
