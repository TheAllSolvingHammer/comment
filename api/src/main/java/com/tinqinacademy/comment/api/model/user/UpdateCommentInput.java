package com.tinqinacademy.comment.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class UpdateCommentInput {
    @JsonIgnore
    private String commentID;
    @NotBlank(message = "Content can not be blank")
    private String content;
}
