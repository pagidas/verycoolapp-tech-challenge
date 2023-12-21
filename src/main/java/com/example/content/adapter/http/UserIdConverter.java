package com.example.content.adapter.http;

import com.example.content.core.domain.micro_types.UserId;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import jakarta.inject.Singleton;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class UserIdConverter implements TypeConverter<String, UserId> {
    @Override
    public Optional<UserId> convert(String rawUuid, Class<UserId> targetType, ConversionContext context) {
        return Optional.of(new UserId(UUID.fromString(rawUuid)));
    }
}
