package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Topic;
import com.cms.repository.TopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic", "id", id));
    }

    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Topic updateTopic(Long id, Topic topicDetails) {
        Topic existing = getTopicById(id);

        existing.setName(topicDetails.getName());

        return topicRepository.save(existing);
    }

    @Override
    public void deleteTopic(Long id) {
        Topic existing = getTopicById(id);
        topicRepository.delete(existing);
    }

    @Override
    public Topic getTopicByName(String name) {
        return topicRepository.findByName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic", "name", name));
    }

    @Override
    public List<Topic> searchTopics(String keyword) {
        return topicRepository.findByNameContaining(keyword);
    }
}
