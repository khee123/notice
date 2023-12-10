package org.khaproject.notice;

import com.p6spy.engine.spy.P6SpyOptions;
import lombok.extern.slf4j.Slf4j;
import org.khaproject.notice.framwork.P6spyPrettyLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class NoticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class, args);
    }

    public static final String userId = "hakim";
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(P6spyPrettyLog.class.getName());
        log.info("notice application ready");
    }
}
