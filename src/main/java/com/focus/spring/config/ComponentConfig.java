package com.focus.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.focus.spring.dao.v4","com.focus.spring.service.v4.impl"})
public class ComponentConfig {
}
