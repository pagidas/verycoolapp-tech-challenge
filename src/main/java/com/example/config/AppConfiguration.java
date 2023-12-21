package com.example.config;

import com.example.content.adapter.in_memory.InMemoryContentRepository;
import com.example.content.core.domain.micro_types.ContentId;
import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.port.driven.ContentRepository;
import com.example.content.core.port.driver.CreateContent;
import com.example.content.core.port.driver.CreateContentLogic;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.ViewAllContentLogic;
import com.example.content.core.port.driver.model.ViewContent;
import io.micronaut.context.annotation.Factory;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Singleton;

@Factory
@SerdeImport(ViewContent.class)
@SerdeImport(ContentId.class)
@SerdeImport(ContentTitle.class)
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
