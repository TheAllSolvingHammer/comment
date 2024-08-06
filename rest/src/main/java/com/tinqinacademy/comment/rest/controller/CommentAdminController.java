package com.tinqinacademy.comment.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteInput;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOperation;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOperation;
import com.tinqinacademy.comment.rest.enums.Mappings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class CommentAdminController extends BaseController{
    private final ObjectMapper objectMapper;
    private final AdminEditOperation adminEditOperation;
    private final AdminDeleteOperation adminDeleteOperation;

    @Autowired
    public CommentAdminController(ObjectMapper objectMapper, AdminEditOperation adminEditOperation, AdminDeleteOperation adminDeleteOperation) {
        this.objectMapper = objectMapper;
        this.adminEditOperation = adminEditOperation;
        this.adminDeleteOperation = adminDeleteOperation;
    }
    @PutMapping(Mappings.PUT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is updated by admin"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    @Operation(summary = "Admin updates comment")
    public ResponseEntity<?> updateSystemComment(@PathVariable String commentID,@Valid @RequestBody AdminEditInput input) {
        AdminEditInput adminEditInput = input.toBuilder()
                .commentID(commentID)
                .build();
        return handleOperation(adminEditOperation.process(adminEditInput));
    }
    @DeleteMapping(Mappings.DELETE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment is deleted by admin"),
            @ApiResponse(responseCode = "400", description = "Wrong syntax"),
            @ApiResponse(responseCode = "403", description = "Forbidden request"),
            @ApiResponse(responseCode = "404", description = "Server was not found")
    })
    @Operation(summary = "Admin deletes comment")
    public ResponseEntity<?> deleteSystemComment(@PathVariable String commentID) {
        AdminDeleteInput adminDeleteInput = AdminDeleteInput.builder()
                .commentID(commentID)
                .build();
        return handleOperation(adminDeleteOperation.process(adminDeleteInput));
    }
}
