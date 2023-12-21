package com.example.content.adapter.http;

import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.model.ViewContent;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
@Named("ViewAllContentHttpClient")
public class ViewAllContentHttpClient implements ViewAllContent {

    private final HttpClient httpClient;

    public ViewAllContentHttpClient(EmbeddedServer embeddedServer) {
        this.httpClient = HttpClient.create(embeddedServer.getURL());
    }

    @Override
    public List<ViewContent> invoke(UserId userId) {
        return httpClient.toBlocking().retrieve(
                HttpRequest.GET("/content/user/%s".formatted(userId.value().toString())),
                Argument.listOf(ViewContent.class)
        );
    }
}
