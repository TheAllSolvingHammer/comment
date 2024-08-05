package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteInput;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOperation;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOutput;
import io.vavr.control.Either;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;

public class AdminDeleteOperationImpl extends BaseProcess implements AdminDeleteOperation {

    public AdminDeleteOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator) {
        super(conversionService, errorMapper, validator);
    }

    @Override
    public Either<ErrorsProcessor, AdminDeleteOutput> process(AdminDeleteInput input) {
        return null;
    }
}
