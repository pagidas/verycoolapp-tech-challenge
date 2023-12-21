package com.example.content.core.port.driver;

import com.example.content.core.domain.micro_types.ContentId;
import com.example.content.core.port.driven.ContentRepository;
import com.example.content.core.port.driven.model.ContentEntity;
import com.example.content.core.port.driver.model.CreateContentRequest;

import java.util.UUID;

public class CreateContentLogic implements CreateContent {

    private final ContentRepository contentRepository;

    public CreateContentLogic(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public void invoke(CreateContentRequest request) {
        var toSave = new ContentEntity(
                new ContentId(UUID.randomUUID()),
                request.userId(),
                request.mediaType(),
                request.contentTitle(),
                ""
        );
        contentRepository.saveContent(toSave);
    }
}
