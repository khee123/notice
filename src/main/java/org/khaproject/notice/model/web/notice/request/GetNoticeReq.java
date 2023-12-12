package org.khaproject.notice.model.web.notice.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetNoticeReq {
    @Schema(description = "공지사항 인덱스")
    @NotNull
    private Long ntceIdx;
}
