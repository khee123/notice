package org.khaproject.notice.controller.notice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.khaproject.notice.NoticeApplication;
import org.khaproject.notice.model.entity.File;
import org.khaproject.notice.model.entity.Notice;
import org.khaproject.notice.model.entity.User;
import org.khaproject.notice.model.web.notice.request.DeleteNoticeReq;
import org.khaproject.notice.model.web.notice.request.GetNoticeReq;
import org.khaproject.notice.model.web.notice.request.UpdateNoticeReq;
import org.khaproject.notice.repository.file.FileRepository;
import org.khaproject.notice.repository.notice.NoticeRepository;
import org.khaproject.notice.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoticeControllerTest {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Flyway flyway;

    @Value("${spring.upload.path}")
    private String uploadPath;

    private static final String BASE_URL = "/rproject";

    Notice initNoticeData = new Notice();
    File fileSaveInfo1 = new File();
    File fileSaveInfo2 = new File();
    @BeforeAll
    void init() {
        System.out.println("===== NoticeControllerTest Data Generate Start =====");

        userRepository.save(
                User.builder()
                .userNm("김희애")
                .userId(NoticeApplication.userId)
                .build()
        );

        MockMultipartFile file1 = new MockMultipartFile(
                "file",
                "file_test_1.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "통합 테스트용 파일 데이터_1".getBytes()
        );

        MockMultipartFile file2 = new MockMultipartFile(
                "file",
                "file_test_2.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "통합 테스트용 파일 데이터_2".getBytes()
        );


        fileSaveInfo1 = fileRepository.save(File.builder()
                .fileNm(RandomStringUtils.randomAlphanumeric(10))
                .orgnFileNm(file1.getOriginalFilename())
                .fileSize(file1.getSize())
                .filePath(uploadPath)
                .tempYn("Y")
                .delYn("N")
                .regUser(NoticeApplication.userId)
                .build()
        );

        fileSaveInfo2 = fileRepository.save(File.builder()
                .fileNm(RandomStringUtils.randomAlphanumeric(10))
                .orgnFileNm(file2.getOriginalFilename())
                .fileSize(file2.getSize())
                .filePath(uploadPath)
                .tempYn("Y")
                .delYn("N")
                .regUser(NoticeApplication.userId)
                .build()
        );

        initNoticeData = noticeRepository.save(
                Notice.builder()
                .ntceNm("통합 테스트 공지사항 데이터_1")
                .ntceConts("공지사항 내용입니다.")
                .srtDt(LocalDateTime.now())
                .endDt(LocalDateTime.now().plusDays(1))
                .delYn("N")
                .regUser(NoticeApplication.userId)
                .modUser(NoticeApplication.userId)
                .build()
        );

        System.out.println("===== NoticeControllerTest Data Generate End =====");
    }

    @Test
    @DisplayName("공지사항 등록/수정 테스트")
    @Order(1)
    void updateNotice() throws Exception {
        // 등록
        UpdateNoticeReq createNoticeInfo = new UpdateNoticeReq();
        createNoticeInfo.setNtceNm("통합 테스트 공지사항 등록 데이터_1");
        createNoticeInfo.setNtceConts("공지사항 내용입니다.");
        createNoticeInfo.setSrtDt(LocalDateTime.now());
        createNoticeInfo.setEndDt(LocalDateTime.now().plusDays(1));
        createNoticeInfo.setFileIdxList(new ArrayList<>(Arrays.asList(fileSaveInfo1.getFileIdx(), fileSaveInfo2.getFileIdx())));

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createNoticeInfo))
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());

        // 수정
        UpdateNoticeReq updateNoticeInfo = new UpdateNoticeReq();
        updateNoticeInfo.setNtceIdx(initNoticeData.getNtceIdx());
        updateNoticeInfo.setNtceNm("통합 테스트 공지사항 데이터 수정_2");
        updateNoticeInfo.setNtceConts("공지사항 내용 수정했습니다.");
        updateNoticeInfo.setSrtDt(LocalDateTime.now().plusDays(1));
        updateNoticeInfo.setEndDt(LocalDateTime.now().plusDays(2));

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(createNoticeInfo))
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공지사항 조회 테스트")
    @Order(2)
    void getNoticeDetail() throws Exception {
        GetNoticeReq detailRequest = new GetNoticeReq();
        detailRequest.setNtceIdx(initNoticeData.getNtceIdx());

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_URL + "/get/detail")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(detailRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공지사항 삭제 테스트")
    @Order(3)
    void deleteNotice() throws Exception {
        DeleteNoticeReq deleteRequest = new DeleteNoticeReq();
        deleteRequest.setNtceIdx(initNoticeData.getNtceIdx());

        ResultActions result = mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_URL + "/delete")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(deleteRequest))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}