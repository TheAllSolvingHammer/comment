package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsInput;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsOutput;
import com.tinqinacademy.comment.api.model.user.get.UserGetCommentsOperation;
import io.vavr.control.Either;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;

public class UserGetCommentsOperationImpl extends BaseProcess implements UserGetCommentsOperation {
    public UserGetCommentsOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator) {
        super(conversionService, errorMapper, validator);
    }

    @Override
    public Either<ErrorsProcessor, GetCommentsOutput> process(GetCommentsInput input) {
        return null;
    }
}
