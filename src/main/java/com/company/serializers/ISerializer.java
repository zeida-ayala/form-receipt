package com.company.serializers;

import java.io.IOException;

public interface ISerializer {
    <T> T write(String path, T obj) throws IOException;
    <T> T read(String filePath, Class clazz) throws IOException;
}
