package com.festival.festival.controller.admin;

import com.festival.festival.dto.NoticeDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {

    @Autowired
    private final NoticeService noticeService;

    @GetMapping("admin_page/notice_list")//리스트 불러오기
    public void notice_review(PageRequestDTO pageRequestDTO, Model model, String keyword) {
        log.info("pageRequestDTO:" + pageRequestDTO);
        int page = pageRequestDTO.getPage();
        int pageNum = 10 * (page - 1);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("result",noticeService.getList(pageRequestDTO));
    }


    @GetMapping("/admin_page/notice_detail")
    public void read(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("idx : " + idx);
        NoticeDTO dto = noticeService.read(idx);
        model.addAttribute("dto", dto);
    }

    @GetMapping("/admin_page/notice_writedo")
    public String writeNotice() throws Exception {
        return "/admin_page/notice_write";
    }

    @PostMapping("/admin_page/notice_insertdo")
    public String insertNotice(NoticeDTO dto, RedirectAttributes redirectAttributes, Model model, MultipartFile file) throws IOException {

        /*noticeService.insertNotice(notice);*/
        Long ip = noticeService.join(dto,file);
        redirectAttributes.addFlashAttribute("msg",ip);

        return "redirect:/admin_page/notice_list";

    }
    @GetMapping("/admin_page/notice_delete")
    public String deleteNotice(Long idx){

        noticeService.deleteNotice(idx);
        return "redirect:/admin_page/notice_list";
    }


    @GetMapping("/admin_page/notice_modify")
    public void modifyNotice(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){


        log.info("idx : "+ idx);
        NoticeDTO dto = noticeService.read(idx);
        model.addAttribute("dto", dto);

    }

    @PostMapping("/admin_page/notice_modify")
    public String updateNotice(@ModelAttribute NoticeDTO noticeDto,
                               @RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException {
        noticeService.update(noticeDto.getIdx(), noticeDto, file);
        redirectAttributes.addFlashAttribute("message", "Successfully updated notice");
        return "redirect:/admin_page/notice_list";
    }

}

