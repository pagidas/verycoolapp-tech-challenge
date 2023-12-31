package com.example.content.core.port.driver;

import com.example.content.adapter.in_memory.InMemoryContentRepository;
import com.example.content.core.port.driver.spec.CreateContentTestSpec;

public class CreateContentTest implements CreateContentTestSpec {

    private static final InMemoryContentRepository contentRepository = new InMemoryContentRepository();
    private static final CreateContent createContent = new CreateContentLogic(contentRepository);
    private static final ViewAllContent viewAllContent = new ViewAllContentLogic(contentRepository);

    @Override
    public CreateContent createContent() {
        return createContent;
    }

    @Override
    public ViewAllContent viewAllContent() {
        return viewAllContent;
    }
}
