package org.khaproject.notice.model.web.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.khaproject.notice.model.entity.File;

@Getter
public class UploadFileRes {
    @Schema(description = "파일 인덱스")
    private Long fileIdx;
    @Schema(description = "원본 파일 명")
    private String orgnFileNm;
    @Schema(description = "파일_크기")
    private Long fileSize;

    public UploadFileRes(File file) {
        this.fileIdx = file.getFileIdx();
        this.orgnFileNm = file.getOrgnFileNm();
        this.fileSize = file.getFileSize();

    }
}
