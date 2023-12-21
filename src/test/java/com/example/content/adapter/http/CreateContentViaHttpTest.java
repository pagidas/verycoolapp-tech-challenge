package com.example.content.adapter.http;

import com.example.Application;
import com.example.content.core.port.driven.ContentRepository;
import com.example.content.core.port.driver.CreateContent;
import com.example.content.core.port.driver.CreateContentTestSpec;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.ViewAllContentLogic;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@MicronautTest(application = Application.class)
public class CreateContentViaHttpTest implements CreateContentTestSpec {

    private final CreateContent createContent;
    private final ViewAllContent viewAllContent;

    @Inject
    public CreateContentViaHttpTest(
            @Named("CreateContentHttpClient") CreateContent createContent,
            ContentRepository contentRepository
    ) {
        this.createContent = createContent;
        this.viewAllContent = new ViewAllContentLogic(contentRepository);
    }

    @Override
    public CreateContent createContent() {
        return createContent;
    }

    @Override
    public ViewAllContent viewAllContent() {
        return viewAllContent;
    }
}
