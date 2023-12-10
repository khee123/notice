package org.khaproject.notice.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.khaproject.notice.model.web.common.response.ApiRes;
import org.khaproject.notice.model.web.common.response.UploadFileRes;
import org.khaproject.notice.service.file.FileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequestMapping("common")
@RequiredArgsConstructor
@RestController
public class FileController {
    private final FileService fileService;
    @Operation(operationId = "upload-file", tags = {"file"}
            , summary = "파일 업로드"
            , description = "파일을 업로드 한다."
    )
    @PostMapping(value = "upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiRes<List<UploadFileRes>> updateNotice(@RequestParam(value = "file") List<MultipartFile> files) {
        return new ApiRes<> (fileService.uploadFile(files));
    }
}
