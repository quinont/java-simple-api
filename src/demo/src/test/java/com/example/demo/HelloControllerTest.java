package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        HelloController messagesController = new HelloController();
        messagesController.getMessages().clear();
    }

    @Test
    public void testSayHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("Hello, Spring Boot!")));
    }

    @Test
    public void testGetMessages() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/messages"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    public void testAddMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"Test Message\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("Message added")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addedMessage", is("\"Test Message\"")));
    }

    @Test
    public void testUpdateMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"Initial Message\""));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/messages/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"Updated Message\""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("Message updated at index 0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedMessage", is("\"Updated Message\"")));
    }

    @Test
    public void testDeleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"Message to Delete\""));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/messages/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", is("Message removed at index 0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.removedMessage", is("\"Test Message\"")));
    }
}

