package org.khaproject.notice.repository.file.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.khaproject.notice.repository.file.FileRepositoryCustom;

import java.util.List;

import static org.khaproject.notice.model.entity.QFile.file;

@RequiredArgsConstructor
public class FileRepositoryCustomImpl {
    private final JPAQueryFactory query;

//    @Override
//    public void deleteFile(String menuCd, Long ntceIdx) {
//        query.update(file)
//            .set(file.delYn, "Y")
//            .where(file.menuCd.eq(menuCd).and(file.useIdx.eq(ntceIdx)))
//            .execute();
//    }
//
//    @Override
//    public void updateFile(String menuCd, Long ntceIdx, List<Long> fileIdxList) {
//        query.update(file)
//                .set(file.delYn, "N")
//                .set(file.tempYn, "N")
//                .set(file.useIdx, ntceIdx)
//                .set(file.menuCd, menuCd)
//                .where(file.fileIdx.in(fileIdxList))
//                .execute();
//    }
}
