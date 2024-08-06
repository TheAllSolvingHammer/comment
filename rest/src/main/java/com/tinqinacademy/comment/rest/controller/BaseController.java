package com.tinqinacademy.comment.rest.controller;

import com.tinqinacademy.comment.api.base.OperationOutput;
import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    public ResponseEntity<?> handleOperation(Either<ErrorsProcessor, ? extends OperationOutput> result) {
        return result.fold(
                error -> ResponseEntity.status(error.getHttpStatus()).body(error),
                success-> ResponseEntity.status(HttpStatus.OK.value()).body(success)
        );
    }
}
