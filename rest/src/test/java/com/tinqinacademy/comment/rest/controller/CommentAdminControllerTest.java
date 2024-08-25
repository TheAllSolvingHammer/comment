package com.tinqinacademy.comment.rest.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


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