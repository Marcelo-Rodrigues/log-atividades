package com.rod.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serializacao {
	private static Gson obterGson() {
		return new GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				.registerTypeAdapterFactory(OptionalTypeAdapter.FACTORY)
				.create();
	}

	public static String serializar(Object src) {
		return obterGson().toJson(src);
	}
	
	public static <T> T deSerializar(String json, Class <T>classOfT) {
		return obterGson().fromJson(json, classOfT);
	}

}
