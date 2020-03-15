package com.example.diggreddit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diggreddit.R;
import com.example.diggreddit.model.TopicModel;
import java.util.List;
import butterknife.BindView;

public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.ViewHolder> {

    private List<TopicModel> topicModelList;
    private OnItemClickListener listener;
    private Context context;

    public TopicListAdapter(List<TopicModel> topicModelList,OnItemClickListener listener) {
        this.topicModelList=topicModelList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.item_topic_list,(ViewGroup) null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      final TopicModel topicModel=topicModelList.get(position);
      holder.textViewTitle.setText(topicModel.getTitle());
      holder.textViewTitleDescription.setText(topicModel.getTopicDescription());
      if(topicModel.getVote()<=0) {
          holder.textViewVote.setText(context.getString(R.string.str_vote));
      }
      else {
          holder.textViewVote.setText(String.valueOf(topicModel.getVote()));
      }
      holder.imageViewUpVote.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(listener!=null){
                  listener.onUpVoteClick(topicModel);
              }
          }
      });
      holder.imageViewDownVote.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(listener!=null){
                  listener.onDownVoteCLick(topicModel);
              }
          }
      });
    }

    @Override
    public int getItemCount() {
        return topicModelList.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_title) TextView textViewTitle;
        @BindView(R.id.text_view_title_description) TextView textViewTitleDescription;
        @BindView(R.id.image_view_upvote) ImageView imageViewUpVote;
        @BindView(R.id.image_view_downvote) ImageView imageViewDownVote;
        @BindView(R.id.text_view_vote) TextView textViewVote;


         ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener {
        void onUpVoteClick(TopicModel topicModel);
        void onDownVoteCLick(TopicModel topicModel);
    }
}
