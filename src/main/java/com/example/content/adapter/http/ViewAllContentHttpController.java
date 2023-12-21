package com.example.content.adapter.http;

import com.example.content.core.domain.micro_types.ContentId;
import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driver.ViewAllContent;
import com.example.content.core.port.driver.model.ViewContent;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.serde.annotation.SerdeImport;

import java.util.List;

@Controller
@SerdeImport(ViewContent.class)
@SerdeImport(ContentId.class)
@SerdeImport(ContentTitle.class)
public class ViewAllContentHttpController {

    private final ViewAllContent viewAllContent;

    public ViewAllContentHttpController(ViewAllContent viewAllContent) {
        this.viewAllContent = viewAllContent;
    }

    @Get(value = "/content/user/{userId}", produces = MediaType.APPLICATION_JSON)
    HttpResponse<List<ViewContent>> viewAllContent(@PathVariable UserId userId) {
        var response = viewAllContent.invoke(userId);
        return HttpResponse.ok(response);
    }
}
