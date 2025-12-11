package com.cms.controller;

import com.cms.model.Topic;
import com.cms.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "*")
public class TopicController {
    private final TopicService service;
    public TopicController(TopicService service) {
        this.service = service;
    }

    // 1. GET All Topic
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(service.getAllTopics());
    }

    // 2. GET Topics By ID
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTopicById(id));
    }

    // 3. CREATE Topic
    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        return ResponseEntity.ok(service.createTopic(topic));
    }

    // 4. UPDATE Topic
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topicDetails) {
        return ResponseEntity.ok(service.updateTopic(id, topicDetails));
    }

    // 5. DELETE Topic
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopic(@PathVariable Long id) {
        service.deleteTopic(id);
        return ResponseEntity.ok("Topic with topicID " + id + " deleted successfully.");
    }

    // 6. Get Topic By Name
    @GetMapping("/by-name")
    public ResponseEntity<Topic> getTopicByName(@RequestParam String name) {
        return ResponseEntity.ok(service.getTopicByName(name));
    }

    // 7. SEARCH Topics By Keyword
    @GetMapping("/search")
    public ResponseEntity<List<Topic>> searchTopics(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchTopics(keyword));
    }

}