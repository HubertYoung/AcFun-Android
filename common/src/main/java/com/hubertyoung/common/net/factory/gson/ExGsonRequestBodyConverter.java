//package com.hubertyoung.common.net.factory.gson;
//
//
//import com.google.gson.Gson;
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonWriter;
//
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.nio.charset.Charset;
//
//import okhttp3.MediaType;
//import okhttp3.RequestBody;
//import okio.Buffer;
//import retrofit2.Converter;
//
///**
// * 作者：JIUU on 2017-7-10 16:00:51
// * QQ号：1344393464
// * 作用：gson请求体
// */
//
//public class ExGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
//
//
//    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
//    private static final Charset UTF_8 = Charset.forName("UTF-8");
//
//    private final Gson gson;
//    private final TypeAdapter<T> adapter;
//
//    ExGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
//        this.gson = gson;
//        this.adapter = adapter;
//    }
//    @Override
//    public RequestBody convert( T value) throws IOException {
//        Buffer buffer = new Buffer();
//        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
//        JsonWriter jsonWriter = gson.newJsonWriter(writer);
//        adapter.write(jsonWriter, value);
//        jsonWriter.close();
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
//    }
//}
