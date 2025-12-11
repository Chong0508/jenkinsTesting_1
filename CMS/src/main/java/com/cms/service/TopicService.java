package com.cms.service;

import com.cms.model.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> getAllTopics();

    Topic getTopicById(Long id);

    Topic createTopic(Topic topic);

    Topic updateTopic(Long id, Topic topicDetails);

    void deleteTopic(Long id);

    Topic getTopicByName(String name);

    List<Topic> searchTopics(String keyword);
}
