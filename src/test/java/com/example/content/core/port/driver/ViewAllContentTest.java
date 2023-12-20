package com.example.content.core.port.driver;

import com.example.content.core.port.driven.InMemoryContentRepository;
import com.example.content.core.port.driven.SaveContent;

public class ViewAllContentTest implements ViewAllContentTestSpec {

    private static final InMemoryContentRepository contentRepository = new InMemoryContentRepository();
    private static final ViewAllContent viewAllContent = new ViewAllContentLogic(contentRepository);

    @Override
    public ViewAllContent viewAllContent() {
        return viewAllContent;
    }

    @Override
    public SaveContent saveContent() {
        return contentRepository;
    }
}
