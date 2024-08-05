package com.tinqinacademy.comment.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsInput;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsOutput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentInput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentOutput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentInput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentOutput;
import com.tinqinacademy.comment.core.CommentUserService;
import com.tinqinacademy.comment.rest.enums.Mappings;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class CommentController {

    private final CommentUserService commentUserService;
    private final ObjectMapper objectMapper;


    @Autowired

    public CommentController(CommentUserService commentUserService, ObjectMapper objectMapper) {
        this.commentUserService = commentUserService;
        this.objectMapper = objectMapper;


    }
    @GetMapping(Mappings.GET)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is retrieved"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    public ResponseEntity<GetCommentsOutput> getComments(@PathVariable String roomID) {
        GetCommentsInput input = GetCommentsInput.builder()
                .roomID(roomID)
                .build();
        return ResponseEntity.ok(commentUserService.getComments(input));
    }
    @PostMapping(Mappings.POST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment was left on the blogpost"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    public ResponseEntity<LeaveCommentOutput> leaveComment(@PathVariable String roomID, @RequestBody @Valid LeaveCommentInput input) {
        LeaveCommentInput inputComment = input.toBuilder()
                .roomID(roomID)
                .build();
        return ResponseEntity.ok(commentUserService.leaveComment(inputComment));
    }
    @PatchMapping(Mappings.PATCH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is updated"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    public ResponseEntity<UpdateCommentOutput> updateComment(@PathVariable String commentID, @RequestParam String content ) {
            UpdateCommentInput updateCommentInput = UpdateCommentInput.builder()
                    .commentID(commentID)
                    .content(content)
                    .build();
            return ResponseEntity.ok(commentUserService.updateComment(updateCommentInput));
    }

}
