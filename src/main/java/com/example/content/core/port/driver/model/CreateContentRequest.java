package com.example.content.core.port.driver.model;

import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.domain.micro_types.UserId;

public record CreateContentRequest(
        UserId userId,
        String mediaType,
        ContentTitle contentTitle,
        byte[] mediaRawData
) {}
