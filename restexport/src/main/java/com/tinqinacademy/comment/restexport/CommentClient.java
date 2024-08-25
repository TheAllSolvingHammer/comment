package com.tinqinacademy.comment.restexport;


import com.tinqinacademy.comment.api.enums.Mappings;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOutput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOutput;
import com.tinqinacademy.comment.api.model.user.get.GetCommentsOutput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentInput;
import com.tinqinacademy.comment.api.model.user.leave.LeaveCommentOutput;
import com.tinqinacademy.comment.api.model.user.update.UpdateCommentOutput;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

@Headers({
        "Content-Type: application/json"
})
public interface CommentClient {

    @RequestLine("GET "+ Mappings.GET)
    GetCommentsOutput getComments(@Param String roomID);

    @RequestLine("POST "+Mappings.POST)
    LeaveCommentOutput leaveComment(@Param String roomID, @RequestBody LeaveCommentInput input);

    @RequestLine("PATCH "+Mappings.PATCH)
    UpdateCommentOutput updateComment(@Param String commentID, @Param String content );

    @RequestLine("PUT "+Mappings.PUT)
    AdminEditOutput updateSystemComment(@Param String commentID, @RequestBody AdminEditInput input);

    @RequestLine("DELETE "+Mappings.DELETE)
    AdminDeleteOutput deleteSystemComment(@Param String commentID) ;

}
