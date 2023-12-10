package org.khaproject.notice.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_ntce")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공지사항_인덱스")
    private Long ntceIdx;
    @Column(length = 100, nullable = false)
    @Comment("공지사항_명")
    private String ntceNm;
    @Column(columnDefinition = "text", nullable = false)
    @Comment("공지사항_내용")
    private String ntceConts;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Comment("시작_일시")
    private LocalDateTime srtDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Comment("종료_일시")
    private LocalDateTime endDt;
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault("0")
    @Comment("조회수")
    private Long viewCnt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @Comment("등록_일시")
    private LocalDateTime regDt;
    @Column(length = 20, nullable = false, updatable = false)
    @Comment("등록_자")
    private String regUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @LastModifiedDate
    @Column(nullable = false)
    @ColumnDefault("current_timestamp")
    @Comment("수정_일시")
    private LocalDateTime modDt;
    @Column(length = 20, nullable = false)
    @Comment("수정_자")
    private String modUser;

    @Builder
    public Notice(Long ntceIdx, String ntceNm, String ntceConts, LocalDateTime srtDt, LocalDateTime endDt, LocalDateTime modDt, String modUser) {
        this.ntceIdx = ntceIdx;
        this.ntceNm = ntceNm;
        this.ntceConts = ntceConts;
        this.srtDt = srtDt;
        this.endDt = endDt;
        this.modDt = modDt;
        this.modUser = modUser;
    }
}
