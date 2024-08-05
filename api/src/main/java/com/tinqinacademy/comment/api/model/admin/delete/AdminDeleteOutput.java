package com.tinqinacademy.comment.api.model.admin.delete;

import com.tinqinacademy.comment.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDeleteOutput implements OperationOutput {
    private String message;
}
