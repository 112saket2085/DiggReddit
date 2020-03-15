package com.example.diggreddit.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.ui.fragment.TopicListFragment;

import java.util.List;

public class TopicListRepository {

    private static TopicListRepository instance;

    public static TopicListRepository getInstance() {
        if(instance==null) {
            instance = new TopicListRepository();
        }
        return instance;
    }

    public LiveData<List<TopicModel>> getTopicListResponseData() {
        MutableLiveData<List<TopicModel>> mutableLiveData=new MutableLiveData<>();
        return mutableLiveData;
    }

}
