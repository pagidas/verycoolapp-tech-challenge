package com.example.content.core.port.driver.model;

import com.example.content.core.domain.micro_types.ContentTitle;

public record ViewContent(
        ContentTitle title,
        String type,
        String url
) {}
