package com.example.content.core.port.driven.model;

import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.domain.micro_types.UserId;

public record ContentEntity(
        UserId userId,
        String type,
        ContentTitle title,
        String url
) {}
