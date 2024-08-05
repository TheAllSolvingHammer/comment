package com.tinqinacademy.comment.core;

import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteInput;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOutput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOutput;

public interface CommentAdminService {
    AdminEditOutput edit(AdminEditInput input);
    AdminDeleteOutput delete(AdminDeleteInput input);
}
