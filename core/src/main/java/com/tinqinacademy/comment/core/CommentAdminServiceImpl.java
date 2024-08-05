package com.tinqinacademy.comment.core;

import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteInput;
import com.tinqinacademy.comment.api.model.admin.delete.AdminDeleteOutput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditInput;
import com.tinqinacademy.comment.api.model.admin.edit.AdminEditOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentAdminServiceImpl implements CommentAdminService {
    @Override
    public AdminEditOutput edit(AdminEditInput input) {
        log.info("Started admin edit {}",input);
        AdminEditOutput output = AdminEditOutput.builder()
                .id("1234")
                .build();
        log.info("Finished admin edit {}",input);
        return output;
    }

    @Override
    public AdminDeleteOutput delete(AdminDeleteInput input) {
        log.info("Started admin delete {}",input);
        AdminDeleteOutput adminDeleteOutput = AdminDeleteOutput.builder()
                .message("TEST")
                //.message("Successfully deleted comment")
                .build();
        log.info("Finished admin delete {}",input);
        return adminDeleteOutput;
    }


}
