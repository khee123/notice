package org.khaproject.notice.repository.notice.Impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.khaproject.notice.model.dto.NoticeDto;
import org.khaproject.notice.repository.notice.NoticeRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import static org.khaproject.notice.model.entity.QNotice.notice;
import static org.khaproject.notice.model.entity.QUser.user;

@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public NoticeDto selectNoticeDetail(Long ntceIdx) {
        return query.select(Projections.fields(
                NoticeDto.class
                , notice.ntceNm
                , notice.ntceConts
                , notice.regDt
                , notice.viewCnt
                , user.userNm.as("regUserNm")
        ))
        .from(notice)
        .innerJoin(user).on(notice.regUser.eq(user.userId))
        .where(notice.ntceIdx.eq(ntceIdx)
                .and(notice.delYn.eq("N")))
        .fetchOne();
    }

}
