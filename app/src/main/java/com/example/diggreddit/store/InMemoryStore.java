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
            return hashMap.get(TOPIC_LIST);
        }
        return null;
    }

    private void clearHashMap() {
        hashMap.clear();
    }

    /**
     * Method to add data according to vote descending
     * @param topicModelCompare Add Topic Model
     * @return List of sorted Topic Model list
     */
    private List<TopicModel> addSortedList(TopicModel topicModelCompare) {
        List<TopicModel> topicModelList=getTopicList();
        for(int i=0;i<topicModelList.size();i++) {
            TopicModel topicModel=topicModelList.get(i);
            if(topicModelCompare.getVote()>=topicModel.getVote()) {
                if(topicModelCompare.getTopicDescription().equalsIgnoreCase(topicModel.getTopicDescription())) {
                    topicModelList.remove(topicModel);
                    topicModelList.add(i,topicModelCompare);
                }
                else {
                    topicModelList.add(i, topicModelCompare);
                }
                return topicModelList;
            }
        }
        topicModelList.add(topicModelCompare);
        return topicModelList;
    }


}
