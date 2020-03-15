package com.example.diggreddit.store;

import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.viewmodel.TopicListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    /**
     * Add Data to hashmap
     * @param topicModel Topic Model to Add.
     */
    public void putItemToList(TopicModel topicModel) {
        List<TopicModel> topicModelList=new ArrayList<>();
        topicModelList.add(topicModel);
        if(getTopicList()!=null && !getTopicList().isEmpty()) {
          topicModelList.addAll(getTopicList());
        }
        if(hashMap!=null) {
            clearHashMap();
            hashMap.put(TOPIC_LIST, topicModelList);
        }
    }

    /**
     * Get Data from hashmap
     * @return Topic List
     */
    public List<TopicModel> getTopicList() {
        if(hashMap.containsKey(TOPIC_LIST)) {
            List<TopicModel> topicModelList = hashMap.get(TOPIC_LIST);
            if (topicModelList != null) {
                Collections.sort(topicModelList, new Comparator<TopicModel>() {
                    @Override
                    public int compare(TopicModel lhs, TopicModel rhs) {
                        return Long.compare(rhs.getVote(), lhs.getVote());
                    }
                });
                return hashMap.get(TOPIC_LIST);
            }
        }
        return null;
    }

    private void clearHashMap() {
        hashMap.clear();
    }
}
