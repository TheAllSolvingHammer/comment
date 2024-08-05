package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOperation;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOutput;
import io.vavr.control.Either;
import jakarta.validation.Validator;
import org.springframework.core.convert.ConversionService;

public class AdminEditOperationImpl extends BaseProcess implements AdminEditOperation {
    public AdminEditOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator) {
        super(conversionService, errorMapper, validator);
    }

    @Override
    public Either<ErrorsProcessor, AdminEditOutput> process(AdminEditInput input) {
        return null;
    }
}
