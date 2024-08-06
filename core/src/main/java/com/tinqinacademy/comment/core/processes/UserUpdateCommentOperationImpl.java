package com.tinqinacademy.comment.core.processes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.comment.api.exceptions.ErrorsProcessor;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentInput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentOutput;
import com.tinqinacademy.comment.api.model.user.update.UserUpdateOperation;
import com.tinqinacademy.comment.core.families.EntityAndIllegalArgumentCaseHandler;
import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import com.tinqinacademy.comment.persistence.repositories.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class UserUpdateCommentOperationImpl extends BaseProcess implements UserUpdateOperation {
    private final CommentRepository commentRepository;
    private ObjectMapper objectMapper;

    public UserUpdateCommentOperationImpl(ConversionService conversionService, ErrorsProcessor errorMapper, Validator validator, CommentRepository commentRepository, ObjectMapper objectMapper) {
        super(conversionService, errorMapper, validator);
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Either<ErrorsProcessor, UpdateCommentOutput> process(UpdateCommentInput input) {
        return validateInput(input).flatMap(validInput -> Try.of(() -> {
                    log.info("Start update comment {}", input);
                    CommentEntity commentEntity=commentRepository.getReferenceById(UUID.fromString(input.getCommentID()));
                    CommentEntity commentPatch = CommentEntity.builder()
                            .id(UUID.fromString(input.getCommentID()))
                            .content(input.getContent())
                            .lastEditDate(LocalDate.now())
                            .build();
                    JsonNode commentNode = objectMapper.valueToTree(commentEntity);
                    JsonNode inputNode= objectMapper.valueToTree(commentPatch);
                    try{
                        JsonMergePatch patch = JsonMergePatch.fromJson(inputNode);
                        JsonNode patchedNode = patch.apply(commentNode);
                        CommentEntity patchedComment = objectMapper.treeToValue(patchedNode,CommentEntity.class);
                        commentRepository.saveAndFlush(patchedComment);
                        UpdateCommentOutput updateCommentOutput = UpdateCommentOutput.builder()
                            .id(patchedComment.getId().toString())
                            .build();
                        log.info("End update comment{}",updateCommentOutput);
                        return updateCommentOutput;
                    }catch (JsonPatchException | IOException e) {
                        throw new RuntimeException("Failed to apply patch to room: " + e.getMessage(), e);
                    }
                }).toEither()
                .mapLeft(EntityAndIllegalArgumentCaseHandler::handleThrowable));
    }
}
