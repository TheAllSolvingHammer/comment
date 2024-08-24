package com.tinqinacademy.comment.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.rest.enums.Mappings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@SpringBootTest
@AutoConfigureMockMvc
public class CommentAdminControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    public void testDeleteSystemComment() throws Exception {
//        String commentID="123";
//
//
//
//        mockMvc.perform(delete(Mappings.DELETE,commentID)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//    @Test
//    public void testCreateSystemComment() throws Exception {
//        String commentID="123";
//        AdminEditInput adminEditInput=AdminEditInput.builder()
//                .content("azsumqk")
//                .commentID(commentID)
//                .roomID("34444")
//                .firstName("dan")
//                .lastName("simeonov")
//                .build();
//        mockMvc.perform(put(Mappings.PUT,commentID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(adminEditInput)))
//                .andExpect(status().isOk());
//    }


}