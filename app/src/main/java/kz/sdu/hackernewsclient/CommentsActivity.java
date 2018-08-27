package kz.sdu.hackernewsclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

    private CommentAdapter adapter;
    private RecyclerView recyclerView;
    ArrayList<Comment> commentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        int postID = getIntent().getIntExtra("postID", 0);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_comments);

        adapter = new CommentAdapter(commentList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommentsActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        Call postCall = ApiHelper.getApiService().getPost(postID);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                for (int i : response.body().getKids()) {
                    Call postsCall = ApiHelper.getApiService().getComment(i);

                    postsCall.enqueue(new Callback<Comment>() {
                        @Override
                        public void onResponse(Call<Comment> call, Response<Comment> response) {
                            commentList.add(response.body());
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                }
                Log.e("axascsac", commentList.size() + "");
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }

}
