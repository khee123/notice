package org.khaproject.notice.repository.notice;

import org.khaproject.notice.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {
    @Transactional
    @Modifying
    @Query("update from Notice n set n.viewCnt = :viewCnt where n.ntceIdx = :ntceIdx and n.delYn = 'N'")
    void updateViewCount(Long ntceIdx, Long viewCnt);

    @Transactional
    @Modifying
    @Query("update from Notice n set n.delYn = 'Y' where n.ntceIdx = :ntceIdx")
    void deleteById(Long ntceIdx);
}
