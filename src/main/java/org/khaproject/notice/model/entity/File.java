package org.khaproject.notice.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_file")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("첨부파일_인덱스")
    private Long fileIdx;
    @Column(name = "menu_cd", length = 10)
    @Comment("메뉴_코드")
    private String menuCd;
    @Column(name = "use_idx")
    @Comment("사용_인덱스")
    private Long useIdx;
    @Column(length = 100, nullable = false, updatable = false)
    @Comment("파일_명")
    private String fileNm;
    @Column(length = 100, nullable = false, updatable = false)
    @Comment("원본_파일_명")
    private String orgnFileNm;
    @Column(length = 200, nullable = false, updatable = false)
    @Comment("파일_경로")
    private String filePath;
    @Column(nullable = false, updatable = false)
    @Comment("파일_크기")
    private Long fileSize;
    @Column(columnDefinition = "char(1)", nullable = false)
    @ColumnDefault("'Y'")
    @Comment("임시_등록_여부(Y,N)")
    private String tempYn;
    @Column(columnDefinition = "char(1)", nullable = false, updatable = false)
    @ColumnDefault("'N'")
    @Comment("삭제_여부")
    private String delYn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @ColumnDefault("current_timestamp")
    @Comment("등록_일시")
    private LocalDateTime regDt;
    @Column(length = 20, nullable = false, updatable = false)
    @Comment("등록_자")
    private String regUser;
}
