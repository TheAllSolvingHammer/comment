package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentInput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentOutput;
import com.tinqinacademy.comment.api.model.user.leave.UserLeaveCommentOperation;
import io.vavr.control.Either;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;

public class UserLeaveCommentOperationImpl extends BaseProcess implements UserLeaveCommentOperation {
    public UserLeaveCommentOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator) {
        super(conversionService, errorMapper, validator);
    }

    @Override
    public Either<ErrorsProcessor, LeaveCommentOutput> process(LeaveCommentInput input) {
        return null;
    }
}
