package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentInput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentOutput;
import com.tinqinacademy.comment.api.model.user.leave.UserLeaveCommentOperation;
import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import com.tinqinacademy.comment.persistence.entities.UserEntity;
import com.tinqinacademy.comment.persistence.repositories.CommentRepository;
import com.tinqinacademy.comment.persistence.repositories.UserRepository;
import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserLeaveCommentOperationImpl extends BaseProcess implements UserLeaveCommentOperation {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public UserLeaveCommentOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator, UserRepository userRepository, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsProcessor, LeaveCommentOutput> process(@Valid LeaveCommentInput input) {
        return validateInput(input).flatMap(validInput -> Try.of(() -> {
                    log.info("Start leaveComment {}", input);
                    UserEntity userEntity = UserEntity.builder()
                            .firstName(input.getFirstName())
                            .lastName(input.getLastName())
                            .birthDate(input.getBirthDate())
                            .build();
                    userRepository.save(userEntity);
                    CommentEntity commentEntity = CommentEntity.builder()
                            .content(input.getContent())
                            .publishDate(LocalDate.now())
                            .user(userEntity)
                            .lastEditDate(LocalDate.now())
                            .roomID(UUID.fromString(input.getRoomID()))
                            .build();
                    CommentEntity save = commentRepository.save(commentEntity);
                    LeaveCommentOutput leaveCommentOutput = LeaveCommentOutput.builder()
                            .id(save.getId().toString())
                            .build();
                    log.info("End leaveComment {}", leaveCommentOutput);
                    return leaveCommentOutput;
                }).toEither()
                .mapLeft(throwable -> API.Match(throwable).of(
                        Case($(instanceOf(IllegalArgumentException.class)),e -> {
                            log.error("Wrong arguments: {}", e.getMessage());
                            return ErrorsProcessor.builder()
                                    .httpStatus(HttpStatus.NOT_FOUND)
                                    .statusCode(HttpStatus.NOT_FOUND.value())
                                    .message(e.getMessage())
                                    .build();
                        })
                )));

    }
}
