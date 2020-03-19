package com.example.diggreddit.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.diggreddit.R;
import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.store.InMemoryStore;
import com.example.diggreddit.ui.adapter.TopicListAdapter;
import com.example.diggreddit.viewmodel.TopicListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class TopicListFragment extends BaseFragment implements TopicListAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recycler_view_topic) RecyclerView recyclerViewTopic;
    @BindView(R.id.fab_add) FloatingActionButton floatingActionButton;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
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
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void addTopicListObserver() {
        topicListViewModel.getTopicListData().observe(getViewLifecycleOwner(), new Observer<List<TopicModel>>() {
            @Override
            public void onChanged(List<TopicModel> topicModelList) {
                swipeRefreshLayout.setRefreshing(false);
                TopicListFragment.this.topicModelList.clear();
                TopicListFragment.this.topicModelList.addAll(topicModelList);
                getBundleValues();
                topicListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getBundleValues() {
        Bundle bundle=getArguments();
        if(bundle!=null) {
            boolean refresh = bundle.getBoolean(AddTopicFragment.BUNDLE_SCROLL_TO_BOTTOM);
            if(refresh) {
                recyclerViewTopic.scrollToPosition(topicModelList.size()-1);
                bundle.putBoolean(AddTopicFragment.BUNDLE_SCROLL_TO_BOTTOM,false);
            }
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getParentActivity());
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
    }

    @Override
    public void onDownVoteCLick(TopicModel topicModel) {
        int position=topicModelList.indexOf(topicModel);
        topicModel.setVote(topicModel.getVote()-1);
        topicListAdapter.notifyItemChanged(position);
    }

    @Override
    public void onRefresh() {
        addTopicListObserver();
    }
}
