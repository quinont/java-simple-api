package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final List<String> messages = new ArrayList<>();

    @GetMapping("/hello")
    public Map<String, String> sayHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, Spring Boot!");
        return response;
    }

    @GetMapping("/messages")
    public List<String> getMessages() {
        return messages;
    }

    @PostMapping("/messages")
    public Map<String, String> addMessage(@RequestBody String message) {
        messages.add(message);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Message added");
        response.put("addedMessage", message);
        return response;
    }

    @PutMapping("/messages/{index}")
    public Map<String, String> updateMessage(@PathVariable int index, @RequestBody String updatedMessage) {
        Map<String, String> response = new HashMap<>();
        if (index < messages.size()) {
            messages.set(index, updatedMessage);
            response.put("message", "Message updated at index " + index);
            response.put("updatedMessage", updatedMessage);
        } else {
            response.put("error", "Invalid index");
        }
        return response;
    }

    @DeleteMapping("/messages/{index}")
    public Map<String, String> deleteMessage(@PathVariable int index) {
        Map<String, String> response = new HashMap<>();
        if (index < messages.size()) {
            String removedMessage = messages.remove(index);
            response.put("message", "Message removed at index " + index);
            response.put("removedMessage", removedMessage);
        } else {
            response.put("error", "Invalid index");
        }
        return response;
    }
}
