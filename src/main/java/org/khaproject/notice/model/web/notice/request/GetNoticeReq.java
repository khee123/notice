package org.khaproject.notice.model.web.notice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class GetNoticeReq {
    @Schema(description = "공지사항 인덱스")
    @NotNull
    private Long ntceIdx;
}
