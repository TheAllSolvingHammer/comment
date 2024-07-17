package com.tinqinacademy.comment.api.model.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ExceptionService {
    ResponseErrorBody handle(MethodArgumentNotValidException ex);
    ResponseErrorBody handle(InputException ex);

}
