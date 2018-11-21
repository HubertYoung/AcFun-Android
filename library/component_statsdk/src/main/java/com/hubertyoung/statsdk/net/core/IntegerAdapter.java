package com.hubertyoung.statsdk.net.core;


import com.hubertyoung.statsdk.net.gson.JsonDeserializationContext;
import com.hubertyoung.statsdk.net.gson.JsonDeserializer;
import com.hubertyoung.statsdk.net.gson.JsonElement;

import java.lang.reflect.Type;

/**
 * @author Aidi on 2018/1/8.
 */
public class IntegerAdapter implements JsonDeserializer<Integer > {

    @Override
    public Integer deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        try {
            return json.getAsInt();
        } catch (Exception e) {
            return 0;
        }
    }

}
