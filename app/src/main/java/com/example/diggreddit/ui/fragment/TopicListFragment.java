package com.example.diggreddit.ui.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diggreddit.R;
import com.example.diggreddit.ui.adapter.TopicListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;

public class TopicListFragment extends BaseFragment {

    @BindView(R.id.recycler_view_topic) RecyclerView recyclerViewTopic;
    @BindView(R.id.fab_add) FloatingActionButton floatingActionButton;

    private TopicListAdapter topicListAdapter;

    @Override
    int getLayoutId() {
        return R.layout.fragment_topic_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewTopic.setLayoutManager(linearLayoutManager);
        topicListAdapter = new TopicListAdapter();
        recyclerViewTopic.setAdapter(topicListAdapter);
    }
}
