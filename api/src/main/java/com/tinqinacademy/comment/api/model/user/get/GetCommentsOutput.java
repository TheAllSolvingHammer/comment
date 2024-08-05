package com.tinqinacademy.comment.api.model.user.get;

import com.tinqinacademy.comment.api.base.OperationOutput;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class GetCommentsOutput implements OperationOutput {
    private String id;
    private String firstName;
    private String lastName;
    private String content;
    private LocalDate publishDate;
    private LocalDate lastEditedDate;
    private String lastEditedBy;
}
