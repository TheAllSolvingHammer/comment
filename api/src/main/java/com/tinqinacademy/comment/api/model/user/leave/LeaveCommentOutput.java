package com.tinqinacademy.comment.api.model.user.leave;

import com.tinqinacademy.comment.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class LeaveCommentOutput implements OperationOutput {
    private String id;
}
