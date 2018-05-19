package com.komarmoss.model.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.komarmoss.config.CustomObjectMapper;
import com.komarmoss.model.entity.Identifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public interface ValueObject<T extends Identifiable> extends Serializable {
    @NotNull
    T createEntity();

    @Nullable
    default String toJson() {
        try {
            return new CustomObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
