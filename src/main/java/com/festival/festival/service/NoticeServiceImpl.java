package com.festival.festival.service;

import com.festival.festival.dto.NoticeDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.PageResultDTO;
import com.festival.festival.entity.Notice;
import com.festival.festival.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private final NoticeRepository noticeRepository;

    @Override
    public NoticeDTO read(Long idx) {
        Optional<Notice> result = noticeRepository.findById(idx);

        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public Long join(NoticeDTO dto, MultipartFile file) throws IOException {
        /*우리의 프로젝트경로를 담아주게 된다 - 저장할 경로를 지정*/
        // 윈도우 경로
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\notice";
        // 맥북 경로
        // 맥북 경로
//        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/notice";

        /*식별자 . 랜덤으로 이름 만들어줌*/
        UUID uuid = UUID.randomUUID();

        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
        String fileName = uuid + "_" + file.getOriginalFilename();

        /*빈 껍데기 생성*/
        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        /*디비에 파일 넣기*/
        dto.setFilename(fileName);
        /*저장되는 경로*/
        dto.setFilepath("/files/notice/" + fileName); /*저장된파일의이름,저장된파일의경로*/

        log.info("회원가입정보DTO: "+dto);

        Notice entity = dtoToEntity(dto);

        log.info("회원가입정보entity: " + entity);

        noticeRepository.save(entity);

        return entity.getIdx();
    }

    @Override
    public PageResultDTO<NoticeDTO, Notice> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("idx").descending());
        Page<Notice> result = noticeRepository.findAll( pageable);
        Function<Notice, NoticeDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn );
    }

    @Override
    public Long count() {
        long count = noticeRepository.count();
        return count;
    }

    @Override
    public List<Object> getPrevIdxAndTitle(Long idx) {
        List<Object> prevIdxAndTitle = noticeRepository.getPrevIdxAndTitle(idx);
        return prevIdxAndTitle;
    }

    @Override
    public List<Object> getNextIdxAndTitle(Long idx) {
        List<Object> nextIdxAndTitle = noticeRepository.getNextIdxAndTitle(idx);
        return nextIdxAndTitle;
    }

    @Override
    public void insertNotice(Notice notice)
        {

            noticeRepository.save(notice);
    }

    @Override
    public void deleteNotice(Long idx){

        noticeRepository.deleteById(idx);
    }


    public void modifyNotice(NoticeDTO dto){
        noticeRepository.modifyById(dto);
    }

    @Override
    public Long update(Long idx, NoticeDTO dto, MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\notice";

        // 기존 파일 정보 가져오기
        Notice existingNotice = noticeRepository.findById(idx).orElse(null);
        String existingFileName = (existingNotice != null && existingNotice.getFilename() != null) ? existingNotice.getFilename() : null;
        String existingFilePath = (existingNotice != null && existingNotice.getFilepath() != null) ? existingNotice.getFilepath() : null;

        // 새 파일이 제공되었을 경우에만 업로드 및 기존 파일 삭제
        if (!file.isEmpty()) {
            // 기존 파일이 있을 경우 삭제
            if (existingFileName != null) {
                File oldFile = new File(projectPath + existingFilePath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            // 새 파일 업로드
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);

            // DTO 업데이트
            dto.setFilename(fileName);
            dto.setFilepath("/files/notice/" + fileName);
        } else {
            // 새 파일이 제공되지 않았을 경우, 기존 파일 정보를 유지
            dto.setFilename(existingFileName);
            dto.setFilepath(existingFilePath);
        }

        noticeRepository.modifyById(dto);

        return dto.getIdx();
    }

}
