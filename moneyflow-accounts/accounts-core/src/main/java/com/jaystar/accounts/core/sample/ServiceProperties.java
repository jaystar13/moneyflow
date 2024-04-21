package com.jaystar.accounts.core.sample;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("service")
public class ServiceProperties {

    private String message;
}
