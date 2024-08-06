package com.tinqinacademy.comment.api.model.user.get;

import com.tinqinacademy.comment.api.base.OperationOutput;
import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class GetCommentsOutput implements OperationOutput {
    private List<GetCommentsOutputItem> commentsOutputList;
}
