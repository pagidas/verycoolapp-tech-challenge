package com.example.content.core.port.driven;

import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driven.model.ContentEntity;

import java.util.List;

public interface ContentRepository {
    List<ContentEntity> getContent(UserId userId);
    void saveContent(ContentEntity content);
}
