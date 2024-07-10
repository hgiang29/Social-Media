package com.social.socialapi.config;

import com.social.socialapi.service.NotifierStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class NotificationServiceConfig {

    @Bean
    public Map<String, NotifierStrategy> notifierStrategyMap(List<NotifierStrategy> strategies) {
        return strategies.stream()
                .collect(Collectors.toMap(strategy -> strategy.getClass().getSimpleName().replace("NotifierStrategy", "").toUpperCase(), Function.identity()));
    }
}