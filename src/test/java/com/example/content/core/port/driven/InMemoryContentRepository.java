package com.example.content.core.port.driven;

import com.example.content.core.domain.micro_types.UserId;
import com.example.content.core.port.driven.model.ContentEntity;

import java.util.*;

public class InMemoryContentRepository implements ContentRepository {

    private final HashMap<UserId, List<ContentEntity>> store;

    public InMemoryContentRepository() {
        store = new HashMap<>();
    }

    @Override
    public List<ContentEntity> getContent(UserId userId) {
        var content = store.get(userId);
        return Objects.requireNonNullElse(content, Collections.emptyList());
    }

    @Override
    public void saveContent(ContentEntity content) {
        var found = store.get(content.userId());
        if (found != null) {
            var newList = copy(found, content);
            store.put(content.userId(), newList);
        } else store.put(content.userId(), List.of(content));
    }

    private static <T> List<T> copy(Collection<T> given, T e) {
        var result = new ArrayList<T>(given.size() + 1);
        result.addAll(given);
        result.add(e);
        return result;
    }
}
