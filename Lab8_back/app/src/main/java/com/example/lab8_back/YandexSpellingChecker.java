package com.example.lab8_back;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.GsonConverterFactory;
import retrofit.http.GET;
import retrofit.http.Query;

public class YandexSpellingChecker {

    private static String YANDEX_API_KEY = "dict.1.1.20240523T125915Z.fb92c46c7d7b0efd.27d8db9e5a43356f5aaf65775264c5674113dd3c";
    private static final String YANDEX_API_BASE_URL = "https://dictionary.yandex.net/api/v1/";

    private YandexDictionaryService yandexDictionaryService;

    public YandexSpellingChecker(String API) {
        YANDEX_API_KEY = API;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YANDEX_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        yandexDictionaryService = retrofit.create(YandexDictionaryService.class);
    }

    public void checkSpelling(String word, SpellingCheckCallback callback) {
        Call<JsonObject> call = yandexDictionaryService.checkSpelling(YANDEX_API_KEY, word);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
                if (response.isSuccess() && response.body() != null) {
                    boolean isCorrect = response.body().getAsJsonObject("result").getAsJsonPrimitive("correct").getAsBoolean();
                    callback.onSpellingCheckComplete(isCorrect);
                } else {
                    if(!response.isSuccess()) Log.v("Debug","notSuccess");
                    if(response.body() == null) Log.v("Debug","isNull");
                    callback.onError("Error checking spelling: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError("Error checking spelling: " + t.getMessage());
            }
        });
    }

    private interface YandexDictionaryService {
        @GET("dicservice.json/lookup")
        Call<JsonObject> checkSpelling(@Query("key") String apiKey, @Query("text") String word);
    }

    public interface SpellingCheckCallback {
        void onSpellingCheckComplete(boolean isCorrect);
        void onError(String errorMessage);
    }
}