package com.example.diggreddit.store;

import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.viewmodel.TopicListViewModel;

import java.util.HashMap;
import java.util.List;

public class InMemoryStore {

    private static final String TOPIC_LIST = "topic_list";
    private static InMemoryStore instance;
    private HashMap<String, List<TopicModel>> hashMap=new HashMap<>();

    public static InMemoryStore getInstance() {
        if(instance==null) {
            instance=new InMemoryStore();
        }
        return instance;
    }

    public void putItemToList(TopicModel topicModel) {
        List<TopicModel> topicModelList=getTopicList();
        topicModelList.add(topicModel);
        hashMap.put(TOPIC_LIST,topicModelList);
    }

    public List<TopicModel> getTopicList() {
        if(hashMap.containsKey(TOPIC_LIST)) {
            return hashMap.get(TOPIC_LIST);
        }
        return null;
    }


}
