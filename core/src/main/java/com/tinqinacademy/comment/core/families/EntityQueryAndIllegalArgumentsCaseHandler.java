package com.tinqinacademy.comment.core.families;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.exceptions.QueryException;
import io.vavr.API;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;
@Slf4j
public class EntityQueryAndIllegalArgumentsCaseHandler {
    public static ErrorsProcessor handleThrowable(Throwable throwable) {
        return API.Match(throwable).of(
                Case($(instanceOf(EntityNotFoundException.class)), e -> {
                    log.error("Entity was not found: {}", e.getMessage());
                    return ErrorsProcessor.builder()
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(e.getMessage())
                            .build();
                }),
                Case($(instanceOf(IllegalArgumentException.class)), e -> {
                    log.error("Wrong arguments: {}", e.getMessage());
                    return ErrorsProcessor.builder()
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(e.getMessage())
                            .build();
                }),
                Case($(instanceOf(QueryException.class)), e -> {
                    log.error("Wrong arguments for query: {}", e.getMessage());
                    return ErrorsProcessor.builder()
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(e.getMessage())
                            .build();
                })
        );
    }
}
