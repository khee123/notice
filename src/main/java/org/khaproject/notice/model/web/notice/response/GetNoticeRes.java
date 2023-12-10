package org.khaproject.notice.model.web.notice.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.khaproject.notice.model.dto.NoticeDto;

import java.time.LocalDateTime;

@Getter
public class GetNoticeRes {
    @Schema(description = "공지사항 명")
    private String ntceNm;
    @Schema(description = "공지사항 내용")
    private String ntceConts;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(description = "등록일시")
    private LocalDateTime regDt;
    @Schema(description = "등록 자")
    private String regUserNm;
    @Schema(description = "조회수")
    private Long viewCnt;


    public GetNoticeRes(NoticeDto notice) {
        this.ntceNm = notice.getNtceNm();
        this.ntceConts = notice.getNtceConts();
        this.regDt = notice.getRegDt();
        this.regUserNm = notice.getRegUserNm();
        this.viewCnt = notice.getViewCnt();
    }
}
