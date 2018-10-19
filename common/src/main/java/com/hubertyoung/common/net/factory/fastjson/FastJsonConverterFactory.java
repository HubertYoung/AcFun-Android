package com.hubertyoung.common.net.factory.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/19 10:26
 * @since:V1.0.0
 * @desc:com.hubertyoung.common.net.factory.fastjson
 */
public class FastJsonConverterFactory extends Converter.Factory {

	private ParserConfig mParserConfig = ParserConfig.getGlobalInstance();
	private int featureValues = JSON.DEFAULT_PARSER_FEATURE;
	private Feature[] features;

	private SerializeConfig serializeConfig;
	private SerializerFeature[] serializerFeatures;

	/**
	 * Create an default instance for conversion. Encoding to JSON and
	 * decoding from JSON (when no charset is specified by a header) will use UTF-8.
	 *
	 * @return The instance of FastJsonConverterFactory
	 */
	public static FastJsonConverterFactory create() {
		return new FastJsonConverterFactory();
	}

	private FastJsonConverterFactory() {
	}

	@Override
	public Converter< ResponseBody, ? > responseBodyConverter( Type type, Annotation[] annotations, Retrofit retrofit ) {
		return new FastJsonResponseBodyConverter<>( type, mParserConfig, featureValues, features );
	}

	@Override
	public Converter< ?, RequestBody > requestBodyConverter( Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit ) {
		return new FastJsonRequestBodyConverter<>( serializeConfig, serializerFeatures );
	}

	public ParserConfig getParserConfig() {
		return mParserConfig;
	}

	public FastJsonConverterFactory setParserConfig( ParserConfig config ) {
		this.mParserConfig = config;
		return this;
	}

	public int getParserFeatureValues() {
		return featureValues;
	}

	public FastJsonConverterFactory setParserFeatureValues( int featureValues ) {
		this.featureValues = featureValues;
		return this;
	}

	public Feature[] getParserFeatures() {
		return features;
	}

	public FastJsonConverterFactory setParserFeatures( Feature[] features ) {
		this.features = features;
		return this;
	}

	public SerializeConfig getSerializeConfig() {
		return serializeConfig;
	}

	public FastJsonConverterFactory setSerializeConfig( SerializeConfig serializeConfig ) {
		this.serializeConfig = serializeConfig;
		return this;
	}

	public SerializerFeature[] getSerializerFeatures() {
		return serializerFeatures;
	}

	public FastJsonConverterFactory setSerializerFeatures( SerializerFeature[] features ) {
		this.serializerFeatures = features;
		return this;
	}
}
