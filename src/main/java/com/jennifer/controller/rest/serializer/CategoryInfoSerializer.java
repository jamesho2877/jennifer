package com.jennifer.controller.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.jennifer.entity.CategoryInfo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Serializer for CategoryInfo
 */

public class CategoryInfoSerializer extends StdSerializer<List> {

    public CategoryInfoSerializer() {
        this(null);
    }

    public CategoryInfoSerializer(Class<List> t) {
        super(t);
    }

    @Override
    public void serialize(List list, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        List<CategoryInfo> categoryInfos = (List<CategoryInfo>) list;
        jgen.writeStartArray(); // [
        for(CategoryInfo categoryInfo : categoryInfos) {
            jgen.writeStartObject(); // {
            jgen.writeNumberField("id", categoryInfo.getId()); // "id" : "value1"
            jgen.writeStringField("name", categoryInfo.getName()); // "name" : "value2"
            jgen.writeNumberField("placeOrder", categoryInfo.getPlaceOrder()); // "placeOrder" : "value2"
            jgen.writeFieldName("superCategoryInfo"); // superCategoryInfo:

            CategoryInfo superCategoryInfo = categoryInfo.getSuperCategoryInfo();
            if(superCategoryInfo != null){
                jgen.writeStartObject(); // {
                jgen.writeNumberField("id", superCategoryInfo.getId()); // "id" : "value1"
                jgen.writeStringField("name", superCategoryInfo.getName()); // "name" : "value2"
                jgen.writeNumberField("placeOrder", superCategoryInfo.getPlaceOrder()); // "placeOrder" : "value2"
                jgen.writeEndObject(); // }
            } else {
                jgen.writeNull();
            }
            jgen.writeEndObject(); // }
        }
        jgen.writeEndArray(); // ]
    }
}
