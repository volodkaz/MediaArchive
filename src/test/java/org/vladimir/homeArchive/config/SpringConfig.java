package org.vladimir.homeArchive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.vladimir.homeArchive.domain.DateMapper;

@Configuration
@ComponentScan("org.vladimir.homeArchive")
@SpringBootTest
public class SpringConfig {
    @Autowired
    protected DateMapper dateMapper;
}
