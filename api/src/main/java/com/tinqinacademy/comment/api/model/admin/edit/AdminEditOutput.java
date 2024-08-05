package com.tinqinacademy.comment.api.model.admin.edit;

import com.tinqinacademy.comment.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder(toBuilder = true)
public class AdminEditOutput implements OperationOutput {
    private String id;
}
