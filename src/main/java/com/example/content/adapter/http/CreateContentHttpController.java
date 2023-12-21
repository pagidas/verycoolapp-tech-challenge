package com.example.content.adapter.http;

import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driver.CreateContent;
import com.example.content.core.port.driver.model.CreateContentRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;

@Controller
public class CreateContentHttpController {

    private final CreateContent createContent;

    @Inject
    public CreateContentHttpController(CreateContent createContent) {
        this.createContent = createContent;
    }

    @Post(value = "/content/user/{userId}", consumes = MediaType.MULTIPART_FORM_DATA, produces = MediaType.TEXT_PLAIN)
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
}
