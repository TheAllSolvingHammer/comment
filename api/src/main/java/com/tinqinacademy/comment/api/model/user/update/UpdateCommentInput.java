package com.tinqinacademy.comment.api.model.user.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comment.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class UpdateCommentInput implements OperationInput {
    @JsonIgnore
    private String commentID;
    @NotBlank(message = "Content can not be blank")
    private String content;
}
