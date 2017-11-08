package com.jane.das.commons.config;

import com.jane.das.commons.config.repository.impl.CustomRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class JpaDataConfig {
}
