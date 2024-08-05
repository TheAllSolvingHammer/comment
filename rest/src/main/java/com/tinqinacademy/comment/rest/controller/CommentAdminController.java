package com.tinqinacademy.comment.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteInput;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOutput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOutput;
import com.tinqinacademy.comment.core.CommentAdminService;
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
public class CommentAdminController {
    private final CommentAdminService commentAdminService;
    private ObjectMapper objectMapper;

    @Autowired
    public CommentAdminController(CommentAdminService commentAdminService, ObjectMapper objectMapper) {
        this.commentAdminService = commentAdminService;
        this.objectMapper = objectMapper;
    }
    @PutMapping(Mappings.PUT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is updated by admin"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    public ResponseEntity<AdminEditOutput> updateSystemComment(@PathVariable String commentID,@Valid @RequestBody AdminEditInput input) {
        AdminEditInput adminEditInput = input.toBuilder()
                .commentID(commentID)
                .build();
        return ResponseEntity.ok(commentAdminService.edit(adminEditInput));
    }
    @DeleteMapping(Mappings.DELETE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is deleted by admin"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    public ResponseEntity<AdminDeleteOutput> deleteSystemComment(@PathVariable String commentID) {
        AdminDeleteInput adminDeleteInput = AdminDeleteInput.builder()
                .commentID(commentID)
                .build();
        return ResponseEntity.ok(commentAdminService.delete(adminDeleteInput));
    }
}
