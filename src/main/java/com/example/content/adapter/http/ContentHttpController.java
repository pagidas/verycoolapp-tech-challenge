package com.example.content.adapter.http;

import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driver.CreateContent;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.model.CreateContentRequest;
import com.example.content.core.port.driver.model.ViewContent;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.util.List;

@Controller("/content")
public class ContentHttpController {

    private final CreateContent createContent;
    private final ViewAllContent viewAllContent;

    public ContentHttpController(CreateContent createContent, ViewAllContent viewAllContent) {
        this.createContent = createContent;
        this.viewAllContent = viewAllContent;
    }

    @Post(value = "/user/{userId}", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
    HttpResponse<?> createContent(@PathVariable UserId userId, CompletedFileUpload file) {
        try {
            var request = new CreateContentRequest(
                    userId,
                    file.getContentType().get().toString(),
                    new ContentTitle(file.getFilename()),
                    file.getBytes()
            );
            createContent.invoke(request);
            return HttpResponse.ok();
        } catch (Exception e) {
            return HttpResponse.serverError(e.getMessage());
        }
    }

    @Get(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON)
    HttpResponse<List<ViewContent>> viewAllContent(@PathVariable UserId userId) {
        var response = viewAllContent.invoke(userId);
        return HttpResponse.ok(response);
    }
}
