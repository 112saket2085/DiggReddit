package com.example.diggreddit.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.diggreddit.R;
import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.ui.adapter.TopicListAdapter;
import com.example.diggreddit.viewmodel.TopicListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class TopicListFragment extends BaseFragment implements TopicListAdapter.OnItemClickListener{

    @BindView(R.id.recycler_view_topic) RecyclerView recyclerViewTopic;
    @BindView(R.id.fab_add) FloatingActionButton floatingActionButton;
    private List<TopicModel> topicModelList=new ArrayList<>();
    private TopicListAdapter topicListAdapter;
    private TopicListViewModel topicListViewModel;

    @Override
    int getLayoutId() {
        return R.layout.fragment_topic_list;
    }

    @Override
    String getTitle() {
        return getString(R.string.str_home);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        TopicListViewModel.TopicListFactory topicListFactory=new TopicListViewModel.TopicListFactory(getParentActivity().getApplication());
        topicListViewModel= new ViewModelProvider(getParentActivity().getViewModelStore(),topicListFactory).get(TopicListViewModel.class);
        addTopicListObserver();
    }

    private void addTopicListObserver() {
        topicListViewModel.getTopicListData().observe(getViewLifecycleOwner(), new Observer<List<TopicModel>>() {
            @Override
            public void onChanged(List<TopicModel> topicModelList) {
               TopicListFragment.this.topicModelList.clear();
                TopicListFragment.this.topicModelList.addAll(topicModelList);
                topicListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewTopic.setLayoutManager(linearLayoutManager);
        topicListAdapter = new TopicListAdapter(topicModelList,this);
        recyclerViewTopic.setAdapter(topicListAdapter);
    }

    @OnClick(R.id.fab_add)
    public void onFabClick(View view) {
        navigateTo(R.id.action_topic_list_to_add_topic);
    }

    @Override
    public void onUpVoteClick(TopicModel topicModel) {
        int position=topicModelList.indexOf(topicModel);
        topicModel.setVote(topicModel.getVote()+1);
        topicListAdapter.notifyItemChanged(position);
        topicListViewModel.changeVote(topicModel);
    }

    @Override
    public void onDownVoteCLick(TopicModel topicModel) {
        int position=topicModelList.indexOf(topicModel);
        topicModel.setVote(topicModel.getVote()-1);
        topicListAdapter.notifyItemChanged(position);
        topicListViewModel.changeVote(topicModel);
    }
}
