package org.elcar.app;

import org.elcar.app.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties({ ApplicationProperties.class })
public class ElcarApplication  extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(ElcarApplication.class);

    private static Class<ElcarApplication> elcarApplicationClass = ElcarApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(ElcarApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(elcarApplicationClass);
    }
}
