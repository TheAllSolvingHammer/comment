package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsInput;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsOutput;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsOutputItem;
import com.tinqinacademy.comment.api.model.user.get.UserGetCommentsOperation;
import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import com.tinqinacademy.comment.persistence.repositories.CommentRepository;
import io.vavr.API;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;
@Service
@Slf4j
public class UserGetCommentsOperationImpl extends BaseProcess implements UserGetCommentsOperation {
    private final CommentRepository commentRepository;
    @Autowired
    public UserGetCommentsOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsProcessor, GetCommentsOutput> process(GetCommentsInput input) {
        return validateInput(input).flatMap(validInput -> Try.of(() -> {
                    log.info("Start get comment {}", input);
                    List<CommentEntity> commentEntityList = commentRepository.findAllByRoomID(UUID.fromString(input.getRoomID()));
                    List<GetCommentsOutputItem> items = commentEntityList.stream()
                            .map(e -> {
                                String fullName = String.format("%s %s", e.getUser().getFirstName(), e.getUser().getLastName());
                                return GetCommentsOutputItem.builder()
                                        .id(e.getId())
                                        .content(e.getContent())
                                        .firstName(e.getUser().getFirstName())
                                        .lastName(e.getUser().getLastName())
                                        .publishDate(e.getPublishDate())
                                        .lastEditedBy(fullName)
                                        .lastEditedDate(e.getLastEditDate())
                                        .build();
                            })
                            .toList();

                    GetCommentsOutput getCommentsOutput = GetCommentsOutput.builder()
                            .commentsOutputList(items)
                            .build();
                    log.info("End get comments {}", getCommentsOutput);
                    return getCommentsOutput;
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
