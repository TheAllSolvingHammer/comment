package com.tinqinacademy.comment.core;

import com.tinqinacademy.comment.api.model.user.*;

public interface CommentUserService {
    GetCommentsOutput getComments(GetCommentsInput input);
    LeaveCommentOutput leaveComment(LeaveCommentInput input);
    UpdateCommentOutput updateComment(UpdateCommentInput input);
}
