package com.tinqinacademy.comment.api.model.admin.delete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comment.api.base.OperationInput;
import com.tinqinacademy.comment.api.exceptions.InputException;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder(toBuilder = true)
public class AdminDeleteInput implements OperationInput {
    @JsonIgnore
    private String commentID;

}
