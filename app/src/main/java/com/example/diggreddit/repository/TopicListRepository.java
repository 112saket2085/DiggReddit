package com.example.diggreddit.repository;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.diggreddit.model.AddTopicResponseModel;
import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.store.InMemoryStore;
import com.example.diggreddit.ui.fragment.TopicListFragment;

import java.util.ArrayList;
import java.util.List;

public class TopicListRepository {

    private static final long DUMMY_ADD_TOPIC_TIME = 3000;
    private static TopicListRepository instance;

    public static TopicListRepository getInstance() {
        if(instance==null) {
            instance = new TopicListRepository();
        }
        return instance;
    }



    /**
     * Add Topic Model to Topic List Memory
     * @return AddTopicResponse LiveData containing status of Add Data.
     */
    public LiveData<AddTopicResponseModel> getResponse(TopicModel topicModel) {
        final MutableLiveData<AddTopicResponseModel> mutableLiveData=new MutableLiveData<>();
        InMemoryStore.getInstance().putItemToList(topicModel);
        // Added Delay of 3 seconds to add topic and assuming response coming from server.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mutableLiveData.setValue(new AddTopicResponseModel(true,"Topic Added Successfully"));
            }
        },DUMMY_ADD_TOPIC_TIME);
        return mutableLiveData;
    }


    /**
     * Create LiveData Instance
     * @return TopicListLiveData - containing list of Topics.
     */
    public LiveData<List<TopicModel>> getTopicListResponseData() {
        MutableLiveData<List<TopicModel>> mutableLiveData=new MutableLiveData<>();
        List<TopicModel> topicModelList=InMemoryStore.getInstance().getTopicList();
        if(topicModelList==null) {
            initDummyTopics();
        }
        // This add change to observer data in fragment and changed can be observed in onChanged method.
        mutableLiveData.setValue(InMemoryStore.getInstance().getTopicList());
        return mutableLiveData;
    }

    /*-*-
      Added 5 Dummy Topics assuming response coming from server.
     */
    private void initDummyTopics() {
        InMemoryStore inMemoryStore=InMemoryStore.getInstance();
        inMemoryStore.putItemToList(new TopicModel("Politics","Politics are popular during every election year.",100));
        inMemoryStore.putItemToList(new TopicModel("Recipes","Recipes are a great way to draw traffic to your blog.",200));
        inMemoryStore.putItemToList(new TopicModel("Beginner guides","Before you can convince someone that you know the advanced stuff, start with 101 beginner guides.",2158));
        inMemoryStore.putItemToList(new TopicModel("Personal stories","Learning how to tell a story is an art. Once you master this skill, the quality of your blog posts will improve.",150));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",700));
        inMemoryStore.putItemToList(new TopicModel("Beginner guides","Before you can convince someone that you know the advanced stuff, start with 101 beginner guides.",2158));
        inMemoryStore.putItemToList(new TopicModel("Personal stories","Learning how to tell a story is an art. Once you master this skill, the quality of your blog posts will improve.",150));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",728));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",950));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",1258));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",714));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",412));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",312));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",568));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",800));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",458));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",744));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",100));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",215));
        inMemoryStore.putItemToList(new TopicModel("Product reviews","If you want to monetize your blog instantly, this is a smart move.",800));
    }

}
