package kz.sdu.hackernewsclient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    private ArrayList<Post> list;
    private Context context;

    public PostAdapter(ArrayList<Post> list){
        this.list=list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        context = parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.author.setText("by " + list.get(position).getBy());

        holder.mItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostWebActivity.class);
                intent.putExtra("url", list.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.showComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentsActivity.class);
                intent.putExtra("postID", list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, author;
        Button showComments;
        LinearLayout mItemLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            mItemLayout = (LinearLayout) itemView.findViewById(R.id.layout_item);
            title = (TextView) itemView.findViewById(R.id.textView_title);
            author = (TextView) itemView.findViewById(R.id.author);
            showComments = (Button) itemView.findViewById(R.id.button_showComments);
        }
    }
}