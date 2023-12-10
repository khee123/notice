package org.khaproject.notice.repository.notice;

import org.khaproject.notice.model.dto.NoticeDto;

public interface NoticeRepositoryCustom {
    Long updateViewCount(Long ntceIdx, Long viewCnt);

    NoticeDto selectNoticeDetail(Long ntceIdx);
}
