package com.tinqinacademy.comment.core.processes;

import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOperation;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOutput;
import com.tinqinacademy.comment.core.families.EntityAndIllegalArgumentCaseHandler;
import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import com.tinqinacademy.comment.persistence.entities.UserEntity;
import com.tinqinacademy.comment.persistence.repositories.CommentRepository;
import com.tinqinacademy.comment.persistence.repositories.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
@Service
@Slf4j
public class AdminEditOperationImpl extends BaseProcess implements AdminEditOperation {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public AdminEditOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator, CommentRepository commentRepository, UserRepository userRepository) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Either<ErrorsProcessor, AdminEditOutput> process(AdminEditInput input) {
        return validateInput(input).flatMap(validInput -> Try.of(() -> {
            log.info("Started admin edit operation{}",input);
                   CommentEntity commentEntity = commentRepository.getReferenceById(UUID.fromString(input.getCommentID()));
                   UserEntity userEntity = userRepository.getReferenceById(commentEntity.getUser().getId());
                   commentEntity.setRoomID(UUID.fromString(input.getRoomID()));
                   userEntity.setFirstName(input.getFirstName());
                   userEntity.setLastName(input.getLastName());
                   commentEntity.setContent(input.getContent());
                   commentEntity.setLastEditDate(LocalDate.now());
                   userRepository.flush();
                   commentRepository.flush();
                   AdminEditOutput adminEditOutput = AdminEditOutput.builder()
                           .id(commentEntity.getId().toString())
                           .build();
                   log.info("End admin edit operation{}",adminEditOutput);
                   return adminEditOutput;

                }).toEither()
                .mapLeft(EntityAndIllegalArgumentCaseHandler::handleThrowable));
    }

}
