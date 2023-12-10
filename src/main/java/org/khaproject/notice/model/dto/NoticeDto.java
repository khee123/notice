package org.khaproject.notice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NoticeDto {
    private String ntceNm;
    private String ntceConts;
    private LocalDateTime regDt;
    private String regUserNm;
    private Long viewCnt;
}
