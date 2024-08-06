package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentInput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentOutput;
import com.tinqinacademy.comment.api.model.user.update.UserUpdateOperation;
import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import com.tinqinacademy.comment.persistence.repositories.CommentRepository;
import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;

@Service
@Slf4j
public class UserUpdateCommentOperationImpl extends BaseProcess implements UserUpdateOperation {
    private final CommentRepository commentRepository;


    public UserUpdateCommentOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsProcessor, UpdateCommentOutput> process(@Valid UpdateCommentInput input) {
        return validateInput(input).flatMap(validInput -> Try.of(() -> {
                    log.info("Start update comment {}", input);
                    CommentEntity commentEntity=commentRepository.getReferenceById(UUID.fromString(input.getCommentID()));
                    commentEntity.setContent(input.getContent());
                    commentEntity.setLastEditDate(LocalDate.now());
                    commentRepository.flush();
                    UpdateCommentOutput updateCommentOutput = UpdateCommentOutput.builder()
                            .id(commentEntity.getId().toString())
                            .build();
                    log.info("End update comment{}",updateCommentOutput);
                    return updateCommentOutput;
                }).toEither()
                .mapLeft(throwable -> API.Match(throwable).of(
                        Case($(instanceOf(IllegalArgumentException.class)),e -> {
                            log.error("Wrong arguments: {}", e.getMessage());
                            return ErrorsProcessor.builder()
                                    .httpStatus(HttpStatus.NOT_FOUND)
                                    .statusCode(HttpStatus.NOT_FOUND.value())
                                    .message(e.getMessage())
                                    .build();
                        }),
                        Case($(instanceOf(EntityNotFoundException.class)), e -> {
                            log.error("Entity was not found: {}", e.getMessage());
                            return ErrorsProcessor.builder()
                                    .httpStatus(HttpStatus.NOT_FOUND)
                                    .statusCode(HttpStatus.NOT_FOUND.value())
                                    .message(e.getMessage())
                                    .build();
                        })
                )));
    }
}
