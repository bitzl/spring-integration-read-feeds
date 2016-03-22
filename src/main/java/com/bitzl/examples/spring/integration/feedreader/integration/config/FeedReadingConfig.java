package com.bitzl.examples.spring.integration.feedreader.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@EnableIntegration
public class FeedReadingConfig {

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(1000).get();
    }

    @Bean
    public IntegrationFlow feedReadingFlow() throws MalformedURLException {
        URL url = new URL("http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/world/rss.xml");
        return IntegrationFlows
                .from((IntegrationFlows.MessageSourcesFunction) messageSources -> messageSources.feed(url, "BBC Key"))
//                .transform(new SummaryTransfomer())
                .handle(System.out::println)
                .get();
    }

}
