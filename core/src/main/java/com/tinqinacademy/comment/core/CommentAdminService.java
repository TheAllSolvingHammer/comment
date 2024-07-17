package com.tinqinacademy.comment.core;

import com.tinqinacademy.comment.api.model.admin.AdminDeleteInput;
import com.tinqinacademy.comment.api.model.admin.AdminDeleteOutput;
import com.tinqinacademy.comment.api.model.admin.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.AdminEditOutput;

public interface CommentAdminService {
    AdminEditOutput edit(AdminEditInput input);
    AdminDeleteOutput delete(AdminDeleteInput input);
}
