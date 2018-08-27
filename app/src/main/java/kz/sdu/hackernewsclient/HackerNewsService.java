package kz.sdu.hackernewsclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HackerNewsService {
    @GET("v0/newstories.json?print=pretty")
    Call<List<Integer>> getNewStories();

    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();

    @GET("v0/beststories.json?print=pretty")
    Call<List<Integer>> getBestStories();

    @GET("v0/item/{postId}.json?print=pretty")
    Call<Post> getPost(@Path("postId") int id);

    @GET("v0/item/{postId}.json?print=pretty")
    Call<Comment> getComment(@Path("postId") int id);
}