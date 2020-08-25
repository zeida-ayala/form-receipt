package com.company.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

public class JsonSerializer implements ISerializer{
    private final DateFormat dateFormat;
    public JsonSerializer(DateFormat dateFormat) {
        this.dateFormat = dateFormat; //new SimpleDateFormat("dd/MM/yyyy HH:mm a");
    }

    @Override
    public <T> T write(String path, T obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(dateFormat);
        try {
            objectMapper.writeValue(new File(path), obj);
            return obj;
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public <T> T read(String path, Class clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(dateFormat);
        try {
            return  (T) objectMapper.readValue(new File(path), clazz);
        } catch (IOException e) {
            throw e;
        }
    }
}
