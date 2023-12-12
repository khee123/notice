package org.khaproject.notice.repository.notice;

import org.khaproject.notice.model.dto.NoticeDto;
import org.khaproject.notice.model.entity.Notice;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepositoryCustom {
    NoticeDto selectNoticeDetail(Long ntceIdx);
}
