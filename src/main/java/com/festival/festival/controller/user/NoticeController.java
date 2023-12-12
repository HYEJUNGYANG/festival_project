package com.festival.festival.controller.user;

import com.festival.festival.dto.NoticeDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {
    @Autowired
    private final NoticeService noticeService;

    @GetMapping("")
    public String list(PageRequestDTO pageRequestDTO, Model model, String keyword) {
        log.info("pageRequestDTO:" + pageRequestDTO);
        int page = pageRequestDTO.getPage();
        int pageNum = 10 * (page - 1);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("result", noticeService.getList(pageRequestDTO));
        model.addAttribute("count", noticeService.count());
        return "/notice/notice";
    }

    @GetMapping("/detail")
    public String detail(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("idx : " + idx);
        NoticeDTO dto = noticeService.read(idx);

        log.info("이전 값들!!!! " + noticeService.getPrevIdxAndTitle(idx));
        log.info("다음 값들!!!! " + noticeService.getNextIdxAndTitle(idx));

        model.addAttribute("dto", dto);
        model.addAttribute("prev", noticeService.getPrevIdxAndTitle(idx));
        model.addAttribute("next", noticeService.getNextIdxAndTitle(idx));

        return "/notice/notice-detail";
    }
}
