package org.khaproject.notice.model.web.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.khaproject.notice.framwork.ResultCodeEnums;

@Setter
@Getter
public class ApiRes<T> {
    @Schema(description = "결과 코드", example = "000")
    private String code;
    @Schema(description = "결과 메세지")
    private String message;
    @Schema(description = "결과 데이터")
    private T data;

    // response 결과를 코드로만 내려줄 경우 사용
    public ApiRes(ResultCodeEnums resultCodeInfo) {
        this.code = resultCodeInfo.getCode();
        this.message = resultCodeInfo.getMessage();
    }

    public ApiRes(T data) {
        this.code = ResultCodeEnums.SUCCESS.getCode();
        this.message = ResultCodeEnums.SUCCESS.getMessage();
        this.data = data;
    }

    public ApiRes(ResultCodeEnums resultCodeInfo, T data) {
        this.code = resultCodeInfo.getCode();
        this.message = resultCodeInfo.getMessage();
        this.data = data;
    }
}
