package org.khaproject.notice.repository.notice;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.khaproject.notice.NoticeApplication;
import org.khaproject.notice.model.dto.NoticeDto;
import org.khaproject.notice.model.entity.Notice;
import org.khaproject.notice.repository.notice.Impl.NoticeRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.khaproject.notice.model.entity.QNotice.notice;
import static org.khaproject.notice.model.entity.QUser.user;


@DataJpaTest
@ActiveProfiles(profiles = "test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoticeRepositoryCustomTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    NoticeRepository noticeRepository;

    @MockBean
    Flyway flyway;

    @MockBean
    NoticeRepositoryCustomImpl noticeRepositoryCustomImpl;

    EntityManager em;

    @BeforeEach
    void init() {
        em = testEntityManager.getEntityManager();

        noticeRepository.save(Notice.builder()
                .ntceNm("공지사항 단위 테스트")
                .ntceConts("공지사항 내용입니다.")
                .srtDt(LocalDateTime.now())
                .endDt(LocalDateTime.now().plusDays(1))
                .delYn("N")
                .regUser(NoticeApplication.userId)
                .modUser(NoticeApplication.userId)
                .build());
    }

    @Test
    @DisplayName("공지사항 등록/수정하기")
    @Transactional
    void updateNotice() {
        // save
        Notice saveResult = noticeRepository.save(Notice.builder()
                .ntceNm("공지사항 저장 단위 테스트")
                .ntceConts("공지사항 내용입니다.")
                .srtDt(LocalDateTime.now())
                .endDt(LocalDateTime.now().plusDays(1))
                .delYn("N")
                .regUser(NoticeApplication.userId)
                .modUser(NoticeApplication.userId)
                .build());

        Assertions.assertThat(saveResult.getNtceIdx()).isNotNull();

        //update
        Notice updateResult = noticeRepository.save(Notice.builder()
                .ntceIdx(saveResult.getNtceIdx())
                .ntceNm("공지사항 수정 단위 테스트")
                .ntceConts("공지사항 내용 수정 했습니다.")
                .srtDt(LocalDateTime.now().plusDays(1))
                .endDt(LocalDateTime.now().plusDays(2))
                .modUser("hakim_up")
                .build());

        Assertions.assertThat(updateResult.getNtceNm()).isNotEqualTo("공지사항 저장 단위 테스트");
        Assertions.assertThat(updateResult.getNtceConts()).isNotEqualTo("공지사항 내용입니다.");
    }

    @Disabled
    @Test
    @DisplayName("공지사항 조회하기")
    void selectNoticeDetail() {
        // given
        Notice saveNoticeInfo = Notice.builder()
                .ntceNm("공지사항 조회 단위 테스트")
                .ntceConts("공지사항 내용입니다.")
                .srtDt(LocalDateTime.now())
                .endDt(LocalDateTime.now().plusDays(1))
                .delYn("N")
                .viewCnt(0L)
                .regUser(NoticeApplication.userId)
                .modUser(NoticeApplication.userId)
                .build();

        Notice saveResult = noticeRepository.save(saveNoticeInfo);

        // 조회수 증가
        Long viewCnt = noticeRepository.findById(saveResult.getNtceIdx()).orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다.")).getViewCnt();
        noticeRepository.updateViewCount(saveResult.getNtceIdx(), viewCnt+1);

        // when
        JPAQuery<Notice> query = new JPAQuery<>(em);
        NoticeDto noticeDetail = query.select(Projections.fields(
                        NoticeDto.class
                        , notice.ntceNm
                        , notice.ntceConts
                        , notice.regDt
                        , notice.viewCnt
                        , user.userNm.as("regUserNm")
                ))
                .from(notice)
                .innerJoin(user).on(notice.regUser.eq(user.userId))
                .where(notice.ntceIdx.eq(saveResult.getNtceIdx())
                        .and(notice.delYn.eq("N")))
                .fetchOne();

        // given
        Assertions.assertThat(saveResult.getNtceNm()).isEqualTo(noticeDetail.getNtceNm());
        Assertions.assertThat(saveResult.getNtceConts()).isEqualTo(noticeDetail.getNtceConts());
        Assertions.assertThat(saveResult.getViewCnt()).isEqualTo(noticeDetail.getViewCnt());
    }

    @Test
    @DisplayName("공지사항 삭제하기")
    void deleteNoticeDetail() {
        // given
        Notice saveNoticeInfo = Notice.builder()
                .ntceNm("공지사항 조회 단위 테스트")
                .ntceConts("공지사항 내용입니다.")
                .srtDt(LocalDateTime.now())
                .endDt(LocalDateTime.now().plusDays(1))
                .delYn("N")
                .regUser(NoticeApplication.userId)
                .modUser(NoticeApplication.userId)
                .build();

        Notice saveResult = noticeRepository.save(saveNoticeInfo);

        // when
        noticeRepository.deleteById(saveResult.getNtceIdx());

        // then
        Assertions.assertThat(noticeRepository.selectNoticeDetail(saveResult.getNtceIdx())).isNull();

    }
}