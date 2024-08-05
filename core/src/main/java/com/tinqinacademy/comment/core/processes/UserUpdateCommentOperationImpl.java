package com.tinqinacademy.comment.core.processes;
import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentInput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentOutput;
import com.tinqinacademy.comment.api.model.user.update.UserUpdateOperation;
import io.vavr.control.Either;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;

public class UserUpdateCommentOperationImpl extends BaseProcess implements UserUpdateOperation {
    public UserUpdateCommentOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator) {
        super(conversionService, errorMapper, validator);
    }

    @Override
    public Either<ErrorsProcessor, UpdateCommentOutput> process(UpdateCommentInput input) {
        return null;
    }
}
