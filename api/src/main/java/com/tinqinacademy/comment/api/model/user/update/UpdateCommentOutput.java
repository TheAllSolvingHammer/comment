package com.tinqinacademy.comment.api.model.user.update;

import com.tinqinacademy.comment.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class UpdateCommentOutput implements OperationOutput {
    private String id;
}
