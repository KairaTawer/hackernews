package kz.sdu.hackernewsclient.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kz.sdu.hackernewsclient.ApiHelper;
import kz.sdu.hackernewsclient.Model.Post;
import kz.sdu.hackernewsclient.Adapter.PostAdapter;
import kz.sdu.hackernewsclient.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewFragment extends Fragment {

    private static Call postIdsCall, postsCall;
    private static ArrayList<Post> result = new ArrayList<>();
    private static PostAdapter mAdapter;

    public NewFragment() {}

    public static TopFragment newInstance(int page) {
        TopFragment fragment = new TopFragment();

        getPosts();
        mAdapter = new PostAdapter(result);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView postList = (RecyclerView) rootView.findViewById(R.id.recyclerView_postList);

        postList.setAdapter(mAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        postList.setLayoutManager(mLayoutManager);

        return rootView;
    }

    private static void getPosts() {

        postIdsCall = ApiHelper.getApiService().getNewStories();

        postIdsCall.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                for(int i = 0; i < 10; i++) {
                    int postId = response.body().get(i);
                    postsCall = ApiHelper.getApiService().getPost(postId);

                    postsCall.enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            result.add(response.body());
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }
}