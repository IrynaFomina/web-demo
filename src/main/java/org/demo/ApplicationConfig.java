package org.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.demo.components","org.demo.web"})
public class ApplicationConfig {
}
