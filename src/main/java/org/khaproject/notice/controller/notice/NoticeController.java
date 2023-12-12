package org.khaproject.notice.controller.notice;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khaproject.notice.model.web.common.response.ApiRes;
import org.khaproject.notice.model.web.notice.request.DeleteNoticeReq;
import org.khaproject.notice.model.web.notice.request.GetNoticeReq;
import org.khaproject.notice.model.web.notice.request.UpdateNoticeReq;
import org.khaproject.notice.model.web.notice.response.DeleteNoticeRes;
import org.khaproject.notice.model.web.notice.response.GetNoticeRes;
import org.khaproject.notice.model.web.notice.response.UpdateNoticeRes;
import org.khaproject.notice.service.notice.NoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("rproject")
@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeService noticeService;
    @Operation(operationId = "update-notice", tags = {"notice"}
            , summary = "공지사항 등록/수정"
            , description = "공지사항을 등록/수정한다."
    )
    @PostMapping(value = "update")
    public ApiRes<UpdateNoticeRes> updateNotice(@RequestBody UpdateNoticeReq param) {
        return new ApiRes<> (noticeService.updateNotice(param));
    }

    @Operation(operationId = "get-notice-detail", tags = {"notice"}
            , summary = "공지사항 조회"
            , description = "공지사항을 조회한다."
    )
    @PostMapping(value = "get/detail")
    public ApiRes<GetNoticeRes> getNoticeDetail(@RequestBody GetNoticeReq param) {
        return new ApiRes<> (noticeService.getNoticeDetail(param));
    }

    @Operation(operationId = "delete-notice", tags = {"notice"}
            , summary = "공지사항 삭제"
            , description = "공지사항을 삭제한다."
    )
    @PostMapping(value = "delete")
    public ApiRes<DeleteNoticeRes> deleteNotice(@RequestBody DeleteNoticeReq param) {
        return new ApiRes<> (noticeService.deleteNotice(param));
    }
}
