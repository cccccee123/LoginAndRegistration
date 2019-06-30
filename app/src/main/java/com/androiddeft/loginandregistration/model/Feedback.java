package com.androiddeft.loginandregistration.model;

public class Feedback {

    private String topic;
    private String content;

    public Feedback(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    @Override
    public String toString() {
        return topic + "\n " + content;
    }


}
