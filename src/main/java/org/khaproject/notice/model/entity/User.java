package org.khaproject.notice.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("사용자_인덱스")
    private Long userIdx;
    @Column(length = 20, nullable = false, updatable = false)
    @Comment("사용자_아이디")
    private String userId;
    @Comment("사용자_명")
    @Column(length = 50, nullable = false, updatable = false)
    private String userNm;
}
