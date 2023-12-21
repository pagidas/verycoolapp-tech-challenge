package com.example.content.core.port.driver;

import com.example.content.core.port.driver.model.CreateContentRequest;

@FunctionalInterface
public interface CreateContent {
    void invoke(CreateContentRequest request);
}
