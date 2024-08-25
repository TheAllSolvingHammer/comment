package com.tinqinacademy.comment.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsInput;
import com.tinqinacademy.comment.api.model.user.get.UserGetCommentsOperation;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentInput;
import com.tinqinacademy.comment.api.model.user.leave.UserLeaveCommentOperation;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentInput;
import com.tinqinacademy.comment.api.model.user.update.UserUpdateOperation;
import com.tinqinacademy.comment.api.enums.Mappings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@Validated
public class CommentController extends BaseController{

    private final ObjectMapper objectMapper;
    private final UserLeaveCommentOperation userLeaveCommentOperation;
    private final UserGetCommentsOperation userGetCommentsOperation;
    private final UserUpdateOperation userUpdateCommentOperation;


    @GetMapping(Mappings.GET)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is retrieved"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    @Operation(summary = "Gets a list of all comments")
    public ResponseEntity<?> getComments(@PathVariable String roomID) {
        GetCommentsInput input = GetCommentsInput.builder()
                .roomID(roomID)
                .build();
        return handleOperation(userGetCommentsOperation.process(input));
    }
    @PostMapping(Mappings.POST)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment was left on the blogpost"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    @Operation(summary = "Leaves a comment for a room")
    public ResponseEntity<?> leaveComment(@PathVariable String roomID, @RequestBody LeaveCommentInput input) {
        LeaveCommentInput inputComment = input.toBuilder()
                .roomID(roomID)
                .build();
        return handleOperation(userLeaveCommentOperation.process(inputComment));
    }
    @PatchMapping(Mappings.PATCH)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is updated"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    @Operation(summary = "Updates a comment for a room")
    public ResponseEntity<?> updateComment(@PathVariable String commentID, @RequestParam String content ) {
            UpdateCommentInput updateCommentInput = UpdateCommentInput.builder()
                    .commentID(commentID)
                    .content(content)
                    .build();
            return handleOperation(userUpdateCommentOperation.process(updateCommentInput));
    }

}
