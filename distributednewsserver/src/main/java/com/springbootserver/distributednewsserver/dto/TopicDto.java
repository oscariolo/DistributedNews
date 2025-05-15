package com.springbootserver.distributednewsserver.dto;

public class TopicDto {
    // This DTO is used to transfer topic data between the client and server
    private String topicName;
    private int partitions;
    private short replicationFactor;

    public TopicDto() {
        // Default constructor
        partitions = 2;
        replicationFactor = -1;
        
    }
    public TopicDto(String topicName, int partitions, short replicationFactor) {
        this.topicName = topicName;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}