package org.khaproject.notice.model.web.notice.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.khaproject.notice.model.entity.Notice;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UpdateNoticeReq {
    @Schema(description = "공지사항 인덱스")
    private Long ntceIdx;
    @Schema(description = "공지사항 명")
    @NotNull
    private String ntceNm;
    @Schema(description = "공지사항 내용")
    @NotNull
    private String ntceConts;
    @Schema(description = "시작 일시", example = "2023-12-12 11:00:00")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime srtDt;
    @Schema(description = "종료 일시", example = "2023-12-13 11:00:00")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endDt;
    @Schema(description = "저장한 첨부파일 인덱스 목록", example = "[\"1\",\"2\",\"3\"]")
    private List<Long> fileIdxList;

    @Builder
    public UpdateNoticeReq(Long ntceIdx, String ntceNm, String ntceConts, LocalDateTime srtDt, LocalDateTime endDt) {
        this.ntceIdx = ntceIdx;
        this.ntceNm = ntceNm;
        this.ntceConts = ntceConts;
        this.srtDt = srtDt;
        this.endDt = endDt;
    }

    public Notice toEntity() {
        return Notice.builder().ntceIdx(ntceIdx).ntceNm(ntceNm).ntceConts(ntceConts).srtDt(srtDt).endDt(endDt).build();
    }
}
