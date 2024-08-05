package com.tinqinacademy.comment.api.model.user.get;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comment.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class GetCommentsInput implements OperationInput {
    @JsonIgnore
    private String roomID;
}
