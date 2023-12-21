package com.example.config;

import com.example.content.adapter.in_memory.InMemoryContentRepository;
import com.example.content.core.port.driven.ContentRepository;
import com.example.content.core.port.driver.CreateContent;
import com.example.content.core.port.driver.CreateContentLogic;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.ViewAllContentLogic;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class AppConfiguration {

    @Singleton
    CreateContent createContent(ContentRepository repository) {
        return new CreateContentLogic(repository);
    }

    @Singleton
    ViewAllContent viewAllContent(ContentRepository repository) {
        return new ViewAllContentLogic(repository);
    }

    @Singleton
    ContentRepository repository() {
        return new InMemoryContentRepository();
    }

}
