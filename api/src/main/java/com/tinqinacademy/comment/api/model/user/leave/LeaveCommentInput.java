package com.tinqinacademy.comment.api.model.user.leave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comment.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder(toBuilder = true)
public class LeaveCommentInput implements OperationInput {
    @JsonIgnore
    private String roomID;
    private UUID userID;
    @NotBlank(message = "First name can not be blank")
    private String firstName;
    @NotBlank(message = "Last name can not be blank")
    private String lastName;
    @NotBlank(message = "Content should not be blank")
    private String content;
}
