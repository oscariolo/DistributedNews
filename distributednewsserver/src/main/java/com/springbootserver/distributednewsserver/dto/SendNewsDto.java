package com.springbootserver.distributednewsserver.dto;

public class SendNewsDto {

    private String topicName;
    private String message;

    public SendNewsDto() {
        // Default constructor
        topicName = "default-topic";
        message = "default-message";
    }

    public SendNewsDto(String topicName, String message) {
        this.topicName = topicName;
        this.message = message;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
