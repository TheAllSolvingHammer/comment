package com.tinqinacademy.comment.api.model.admin.edit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comment.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder(toBuilder = true)
public class AdminEditInput implements OperationInput {
    @JsonIgnore
    private String commentID;
    @NotBlank(message = "RoomID an not be blank")
    private String roomID;
    @NotBlank(message = "First name not be blank")
    private String firstName;
    @NotBlank(message = "Last name can not be blank")
    private String lastName;
    @NotBlank(message = "Content can not be blank")
    private String content;
}
