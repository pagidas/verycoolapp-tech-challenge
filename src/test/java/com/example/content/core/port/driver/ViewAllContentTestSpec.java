package com.example.content.core.port.driver;

import com.example.content.core.domain.micro_types.ContentId;
import com.example.content.core.domain.micro_types.ContentTitle;
import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driven.SaveContent;
import com.example.content.core.port.driven.model.ContentEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface ViewAllContentTestSpec {

    ViewAllContent viewAllContent();
    SaveContent saveContent();

    @Test
    default void can_view_no_content_if_none_exist_for_user() {
        var content = viewAllContent().invoke(new UserId(UUID.randomUUID()));
        assertEquals(Collections.emptyList(), content);
    }

    @Test
    default void can_view_contents_for_a_user() {
        var givenUser = new UserId(UUID.randomUUID());
        List.of(
                new ContentEntity(new ContentId(UUID.randomUUID()),
                        givenUser,
                        "type",
                        new ContentTitle("title-1"),
                        "some-url"),
                new ContentEntity(new ContentId(UUID.randomUUID()),
                        givenUser,
                        "type",
                        new ContentTitle("title-2"),
                        "some-url")
        ).forEach(save());

        var result = viewAllContent().invoke(givenUser);
        assertEquals(2, result.size());
    }

    default Consumer<ContentEntity> save() {
        return entity -> saveContent().invoke(entity);
    }

}
