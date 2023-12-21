package com.example.content.core.port.driver;

import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driver.model.CreateContentRequest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

interface CreateContentTestSpec {

    CreateContent createContent();
    ViewAllContent viewAllContent();

    @Test
    default void can_create_new_content_for_given_user() {
        var givenUser = new UserId(UUID.randomUUID());
        var request = new CreateContentRequest(
                givenUser,
                "jpg",
                new ContentTitle("photo"),
                new byte[] {}
        );

        createContent().invoke(request);

        var result = viewAllContent().invoke(givenUser);
        assertNotEquals(Collections.emptyList(), result);
        var created = result.getFirst();
        assertEquals("jpg", created.type());
    }

}
