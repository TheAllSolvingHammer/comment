package com.tinqinacademy.comment.api.model.exceptions;

import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
public class ResponseErrorBody
{
   private List<ErrorWrapper> errors;
}
