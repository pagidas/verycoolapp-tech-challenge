package com.example.content.core.port.driver;

import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driver.model.ViewContent;

import java.util.List;

@FunctionalInterface
public interface ViewAllContent {
    List<ViewContent> invoke(UserId userId);
}
