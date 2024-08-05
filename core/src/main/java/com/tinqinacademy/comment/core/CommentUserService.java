package com.tinqinacademy.comment.core;

import com.tinqinacademy.comment.api.model.user.get.GetCommentsInput;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsOutput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentInput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentOutput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentInput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentOutput;

public interface CommentUserService {
    GetCommentsOutput getComments(GetCommentsInput input);
    LeaveCommentOutput leaveComment(LeaveCommentInput input);
    UpdateCommentOutput updateComment(UpdateCommentInput input);
}
