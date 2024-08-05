package com.tinqinacademy.comment.core;

import com.tinqinacademy.comment.api.model.user.get.GetCommentsInput;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsOutput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentInput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentOutput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentInput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentOutput;
import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import com.tinqinacademy.comment.persistence.repositories.CommentRepository;
import com.tinqinacademy.comment.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentUserServiceImpl implements CommentUserService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public GetCommentsOutput getComments(GetCommentsInput input) {
        log.info("Start getComments {}",input);
        GetCommentsOutput output = GetCommentsOutput.builder()
                .id("FD322")
                .firstName("Yordan")
                .lastName("Simeonov")
                .content("Mnogo sum qk")
                .publishDate(LocalDate.of(2023,12,12))
                .lastEditedDate(LocalDate.of(2024,1,1))
                .lastEditedBy("Dido")
                .build();
        log.info("End getComments {}",output);
        return output;
    }

    @Override
    public LeaveCommentOutput leaveComment(LeaveCommentInput input) {
        log.info("Start leaveComment {}",input);
    //todo
        userRepository.getReferenceById(input.getUserID());
        CommentEntity comment= CommentEntity.builder().build();
        LeaveCommentOutput leaveCommentOutput = LeaveCommentOutput.builder()
                .id("323KF")
                .build();
        log.info("End leaveComment {}",leaveCommentOutput);
        return leaveCommentOutput;

    }

    @Override
    public UpdateCommentOutput updateComment(UpdateCommentInput input) {
        log.info("Start updateComment {}",input);
        UpdateCommentOutput updateCommentOutput = UpdateCommentOutput.builder()
                .id("FR234")
                .build();
        log.info("End updateComment {}",updateCommentOutput);
        return updateCommentOutput;
    }
}
