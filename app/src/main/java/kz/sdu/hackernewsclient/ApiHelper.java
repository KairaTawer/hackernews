package kz.sdu.hackernewsclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    public static final String BASE_URL = "https://hacker-news.firebaseio.com/";

    public static HackerNewsService apiService;

    public static HackerNewsService getApiService() {
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
            apiService = retrofit.create(HackerNewsService.class);
        }
        return apiService;
    }
}