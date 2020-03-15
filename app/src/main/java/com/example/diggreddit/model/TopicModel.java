package com.example.diggreddit.model;

public class TopicModel {

    private String title;
    private String topicDescription;
    private long vote;

    public TopicModel(String title, String topicDescription, long vote) {
        this.title = title;
        this.topicDescription = topicDescription;
        this.vote = vote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public long getVote() {
        return vote;
    }

    public void setVote(long vote) {
        this.vote = vote;
    }
}
