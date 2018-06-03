package com.komarmoss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomObjectMapper extends ObjectMapper {
    private static final String EMPTY_STR_RPL = "-";

    public CustomObjectMapper() {
        super();
        /*final DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
        sp.setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(EMPTY_STR_RPL);
            }
        });
        setSerializerProvider(sp);*/
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        enable(SerializationFeature.INDENT_OUTPUT);
    }

}
