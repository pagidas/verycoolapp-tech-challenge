package com.example.content.adapter.http;

import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driver.CreateContent;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.model.CreateContentRequest;
import com.example.content.core.port.driver.model.ViewContent;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
@Named("ContentApiTestHttpClient")
@SerdeImport(MultipartBody.Builder.class)
public class ContentApiHttpClient implements CreateContent, ViewAllContent {

    private final HttpClient httpClient;

    public ContentApiHttpClient(EmbeddedServer embeddedServer) {
        this.httpClient = HttpClient.create(embeddedServer.getURL());
    }

    @Override
    public void invoke(CreateContentRequest request) {
        var body = MultipartBody.builder().addPart(
                "file",
                request.contentTitle().value(),
                MediaType.of(request.mediaType()),
                request.mediaRawData()
        );
        httpClient.toBlocking()
                .exchange(HttpRequest
                        .POST("/content/user/%s".formatted(request.userId().value().toString()), body)
                        .contentType(MediaType.MULTIPART_FORM_DATA_TYPE)
                );
    }

    @Override
    public List<ViewContent> invoke(UserId userId) {
        return httpClient.toBlocking().retrieve(
                HttpRequest.GET("/content/user/%s".formatted(userId.value().toString())),
                Argument.listOf(ViewContent.class)
        );
    }

}
