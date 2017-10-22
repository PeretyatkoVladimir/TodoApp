package ua.artcode.todo.utils;

import com.google.gson.Gson;
import com.mashape.unirest.http.ObjectMapper;

/**
 * Created by serhii on 22.10.17.
 */
public class JsonUtils {
    public static ObjectMapper getObjectMapper() {
        return new ObjectMapper() {

            private Gson gson = new Gson();

            public <T> T readValue(String value, Class<T> valueType) {

                return gson.fromJson(value, valueType);

            }

            public String writeValue(Object value) {
                return gson.toJson(value);
            }
        };
    }
}
