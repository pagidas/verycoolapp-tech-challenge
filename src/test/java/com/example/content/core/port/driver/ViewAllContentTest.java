package com.example.content.core.port.driver;

import com.example.content.adapter.in_memory.InMemoryContentRepository;

public class ViewAllContentTest implements ViewAllContentTestSpec {

    private static final InMemoryContentRepository contentRepository = new InMemoryContentRepository();
    private static final ViewAllContent viewAllContent = new ViewAllContentLogic(contentRepository);
    private static final CreateContent createContent = new CreateContentLogic(contentRepository);

    @Override
    public ViewAllContent viewAllContent() {
        return viewAllContent;
    }

    @Override
    public CreateContent createContent() {
        return createContent;
    }

}
