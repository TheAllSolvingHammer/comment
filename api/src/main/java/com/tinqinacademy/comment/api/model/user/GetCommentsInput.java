package com.tinqinacademy.comment.api.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class GetCommentsInput {
    @JsonIgnore
    private String roomID;
}
