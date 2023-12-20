package com.example.content.core.port.driven;

import com.example.content.core.port.driven.model.ContentEntity;

/**
 * A testing write operation to help testing production read operations.
 * It will likely get removed when we will build the production write operation.
 */
@FunctionalInterface
public interface SaveContent {
    void invoke(ContentEntity content);
}
