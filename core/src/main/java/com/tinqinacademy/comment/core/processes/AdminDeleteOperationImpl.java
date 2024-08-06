package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.exceptions.QueryException;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteInput;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOperation;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOutput;
import com.tinqinacademy.comment.core.families.EntityQueryAndIllegalArgumentsCaseHandler;
import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import com.tinqinacademy.comment.persistence.repositories.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AdminDeleteOperationImpl extends BaseProcess implements AdminDeleteOperation {
    private final CommentRepository commentRepository;
    public AdminDeleteOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsProcessor, AdminDeleteOutput> process(AdminDeleteInput input) {
        return validateInput(input).flatMap(validInput -> Try.of(() -> {
                   log.info("Started admin delete {}",input);
                   Optional<CommentEntity> commentEntityOptional=commentRepository.findById(UUID.fromString(input.getCommentID()));
                   if(commentEntityOptional.isEmpty()){
                       throw new QueryException("No entity was found with such id");
                   }
                   commentRepository.delete(commentEntityOptional.get());
                   AdminDeleteOutput adminDeleteOutput = AdminDeleteOutput.builder()
                           .message("Successfully deleted a comment with id "+input.getCommentID())
                           .build();
                   log.info("End admin delete {}",adminDeleteOutput);
                   return adminDeleteOutput;
                }).toEither()
                .mapLeft(EntityQueryAndIllegalArgumentsCaseHandler::handleThrowable));

    }
}
