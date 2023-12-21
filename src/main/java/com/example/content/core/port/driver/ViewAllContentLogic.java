package com.example.content.core.port.driver;

import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driven.ContentRepository;
import com.example.content.core.port.driven.model.ContentEntity;
import com.example.content.core.port.driver.model.ViewContent;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ViewAllContentLogic implements ViewAllContent {

    private final ContentRepository contentRepository;

    public ViewAllContentLogic(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public List<ViewContent> invoke(UserId userId) {
        return contentRepository.getContent(userId)
                .stream()
                .map(toView)
                .collect(Collectors.toList());
    }

    private static final Function<ContentEntity, ViewContent> toView =
            entity -> new ViewContent(entity.id(), entity.title(), entity.type(), entity.url());
}
