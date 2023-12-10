package org.khaproject.notice.framwork;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
            title = "Notice Rest API"
            , description = "공지사항 API 명세서"
            , version = "1.0"
        )
        ,tags = {
            @Tag(name="notice", description = "공지사항")
            , @Tag(name = "file", description = "첨부파일 처리")
        }
)
@Configuration
public class SwaggerConfig {
}
