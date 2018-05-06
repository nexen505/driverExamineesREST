package com.komarmoss.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

import java.io.IOException;

class CustomObjectMapper extends ObjectMapper {
    private static final String EMPTY_STR_RPL = "-";

    CustomObjectMapper() {
        super();
        DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
        sp.setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(EMPTY_STR_RPL);
            }
        });
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        setSerializerProvider(sp);
    }

}
