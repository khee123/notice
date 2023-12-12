package org.khaproject.notice.repository.notice;

import config.JpaTestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.khaproject.notice.NoticeApplication;
import org.khaproject.notice.model.dto.NoticeDto;
import org.khaproject.notice.model.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
//@Import(JpaTestConfig.class)
@ActiveProfiles(profiles = "test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NoticeRepositoryCustomTest {
    @Autowired
    NoticeRepository noticeRepository;

    @Test
    @DisplayName("공지사항 등록/수정하기")
    void updateNotice() {
        // save
        // given
        Notice saveNoticeInfo = Notice.builder()
                .ntceNm("공지사항 저장 단위 테스트")
                .ntceConts("공지사항 내용입니다.")
                .srtDt(LocalDateTime.now())
                .endDt(LocalDateTime.now().plusDays(1))
                .delYn("N")
                .regUser(NoticeApplication.userId)
                .modUser(NoticeApplication.userId)
                .build();

        // when
        Notice saveResult = noticeRepository.save(saveNoticeInfo);

        // then
        Assertions.assertThat(saveNoticeInfo.getNtceConts()).isEqualTo(saveResult.getNtceConts());
        Assertions.assertThat(saveNoticeInfo.getNtceConts()).isEqualTo(saveResult.getNtceConts());
        Assertions.assertThat(saveNoticeInfo.getSrtDt()).isEqualTo(saveResult.getSrtDt());
        Assertions.assertThat(saveNoticeInfo.getEndDt()).isEqualTo(saveResult.getEndDt());
        Assertions.assertThat(saveNoticeInfo.getModDt()).isEqualTo(saveResult.getModDt());

        //update
        // given
        Notice updateNoticeInfo = Notice.builder()
                .ntceIdx(saveResult.getNtceIdx())
                .ntceNm("공지사항 수정 단위 테스트")
                .ntceConts("공지사항 내용 수정 했습니다.")
                .srtDt(LocalDateTime.now().plusDays(1))
                .endDt(LocalDateTime.now().plusDays(2))
                .modUser("hakim_up")
                .build();
        // when
        Notice updateResult = noticeRepository.save(updateNoticeInfo);
        // then
        Assertions.assertThat(updateResult.getNtceNm()).isNotEqualTo(saveResult.getNtceNm());
        Assertions.assertThat(updateResult.getNtceConts()).isNotEqualTo(saveResult.getNtceConts());
        Assertions.assertThat(updateResult.getSrtDt()).isNotEqualTo(saveResult.getSrtDt());
        Assertions.assertThat(updateResult.getEndDt()).isNotEqualTo(saveResult.getEndDt());
        Assertions.assertThat(updateResult.getModDt()).isNotEqualTo(saveResult.getModDt());
    }


    // updateViewCount
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
                .regUser(NoticeApplication.userId)
                .modUser(NoticeApplication.userId)
                .build();

        Notice saveResult = noticeRepository.save(saveNoticeInfo);

        // 조회수 증가
//        Long viewCnt = noticeRepository.findById(saveResult.getNtceIdx()).orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다.")).getViewCnt();
//        noticeRepository.updateViewCount(saveResult.getNtceIdx(), viewCnt);

        // when
        NoticeDto noticeDetail = noticeRepository.selectNoticeDetail(saveResult.getNtceIdx());

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