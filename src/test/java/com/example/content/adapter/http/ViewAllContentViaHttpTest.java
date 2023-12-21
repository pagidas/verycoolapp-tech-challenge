package com.example.content.adapter.http;

import com.example.Application;
import com.example.content.core.port.driven.ContentRepository;
import com.example.content.core.port.driver.CreateContent;
import com.example.content.core.port.driver.CreateContentLogic;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.spec.ViewAllContentTestSpec;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Named;

@MicronautTest(application = Application.class)
public class ViewAllContentViaHttpTest implements ViewAllContentTestSpec {

    private final CreateContent createContent;
    private final ViewAllContent viewAllContent;

    public ViewAllContentViaHttpTest(
            @Named("ContentApiTestHttpClient") ViewAllContent viewAllContent,
            ContentRepository contentRepository
    ) {
        this.viewAllContent = viewAllContent;
        this.createContent = new CreateContentLogic(contentRepository);

    }

    @Override
    public ViewAllContent viewAllContent() {
        return viewAllContent;
    }

    @Override
    public CreateContent createContent() {
        return createContent;
    }
}
