package com.example.diggreddit.store;

import com.example.diggreddit.model.TopicModel;
import com.example.diggreddit.viewmodel.TopicListViewModel;

import java.util.ArrayList;
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
        if(getTopicList()!=null && !getTopicList().isEmpty()) {
          topicModelList.addAll(addSortedList(topicModel));
        }
        else {
            topicModelList.add(topicModel);
        }
        hashMap.put(TOPIC_LIST,topicModelList);
    }

    /**
     * Get Data from hashmap
     * @return Topic List
     */
    public List<TopicModel> getTopicList() {
        if(hashMap.containsKey(TOPIC_LIST)) {
            return hashMap.get(TOPIC_LIST);
        }
        return null;
    }

    /**
     * Method to add data according to vote descending
     * @param topicModelCompare Add Topic Model
     * @return List of sorted Topic Model list
     */
    private List<TopicModel> addSortedList(TopicModel topicModelCompare) {
        List<TopicModel> topicModelList=getTopicList();
        for(int i=0;i<getTopicList().size();i++) {
            TopicModel topicModel=getTopicList().get(i);
            if(topicModelCompare.getVote()>=topicModel.getVote()) {
                topicModelList.add(i,topicModelCompare);
                return topicModelList;
            }
        }
        topicModelList.add(topicModelCompare);
        return getTopicList();
    }


}
