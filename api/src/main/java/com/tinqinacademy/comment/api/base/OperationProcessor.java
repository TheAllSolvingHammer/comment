package com.tinqinacademy.comment.api.base;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import io.vavr.control.Either;

public interface OperationProcessor<T extends OperationOutput,E extends OperationInput> {
    Either<ErrorsProcessor,T> process(E input);

}
