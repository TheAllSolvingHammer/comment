package com.tinqinacademy.comment.api.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder(toBuilder = true)
public class AdminDeleteInput {
    @JsonIgnore
    private String commentID;

}
