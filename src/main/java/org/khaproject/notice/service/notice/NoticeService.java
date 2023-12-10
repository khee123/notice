package org.khaproject.notice.service.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khaproject.notice.NoticeApplication;
import org.khaproject.notice.framwork.ResultCodeEnums;
import org.khaproject.notice.model.dto.NoticeDto;
import org.khaproject.notice.model.entity.Notice;
import org.khaproject.notice.model.web.notice.request.DeleteNoticeReq;
import org.khaproject.notice.model.web.notice.request.GetNoticeReq;
import org.khaproject.notice.model.web.notice.request.UpdateNoticeReq;
import org.khaproject.notice.model.web.notice.response.GetNoticeRes;
import org.khaproject.notice.repository.file.FileRepository;
import org.khaproject.notice.repository.notice.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final FileRepository fileRepository;

    /*
     * 공지사항 등록/수정
     */
    @Transactional
    public ResultCodeEnums updateNotice(UpdateNoticeReq param) {
        Notice noticeInfo = param.toEntity();
        noticeInfo.setModUser(NoticeApplication.userId);
        noticeInfo.setRegUser(NoticeApplication.userId);

        // 공지사항 등록/수정
        Notice noticeDetail = noticeRepository.save(noticeInfo);

        // 첨부파일 등록
        fileRepository.deleteFile("menu_ntce", noticeDetail.getNtceIdx());
        fileRepository.updateFile("menu_ntce", noticeDetail.getNtceIdx(), param.getFileIdxList());

        return ResultCodeEnums.SUCCESS;
    }

    /*
     * 공지사항 조회
     */
    public GetNoticeRes getNoticeDetail(GetNoticeReq param) {
        // 조회수 조회
        Long viewCnt = noticeRepository.findById(param.getNtceIdx()).orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다.")).getViewCnt();
        // 조회수 증가
        noticeRepository.updateViewCount(param.getNtceIdx(), viewCnt);

        // 공지사항 조회
        NoticeDto noticeDetail = noticeRepository.selectNoticeDetail(param.getNtceIdx());

        return new GetNoticeRes(noticeDetail);
    }

    /*
     * 공지사항 삭제
     */
    public ResultCodeEnums deleteNotice(DeleteNoticeReq param) {
        noticeRepository.deleteById(param.getNtceIdx());

        return ResultCodeEnums.SUCCESS;
    }
}
