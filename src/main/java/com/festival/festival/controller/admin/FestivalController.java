package com.festival.festival.controller.admin;

import com.festival.festival.dto.FestivalDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.service.FestivalService;
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
public class FestivalController {

    @Autowired
    private final FestivalService festivalService;


    @GetMapping("admin_page/festival_list")//리스트 불러오기
    public void list_festival(PageRequestDTO pageRequestDTO, Model model, String keyword) {
        log.info("pageRequestDTO:" + pageRequestDTO);
        int page = pageRequestDTO.getPage();
        int pageNum = 10 * (page - 1);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("result",festivalService.getList(pageRequestDTO));
    }


    @GetMapping("/admin_page/festival_detail")
    public void read(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("idx : " + idx);
        FestivalDTO dto = festivalService.read(idx);
        //@@1@@로 되어있는 태그를 #1,#2로 변환
        String savedTags = dto.getTag(); // 데이터베이스에서 읽어온 해시태그

        String[] tags = savedTags.split("@@"); // "@@"로 해시태그 분리

        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            if (!tag.isEmpty()) {
                sb.append("#").append(tag); // 각 해시태그에서 "@@"를 제거하고 "#"을 추가하여 StringBuilder에 추가
            }
        }
        String tag = sb.toString(); // 변환된 형식인 "#12,#34,#56" 형태의 문자열
        model.addAttribute("dto", dto);
        model.addAttribute("tag", tag);
    }

    @GetMapping("/admin_page/festival_writedo")
    public String writeFestival(){
        return "/admin_page/festival_write";
    }

    @PostMapping("/admin_page/festival_insertdo")
    public String insertFestival(FestivalDTO dto, RedirectAttributes redirectAttributes, Model model, MultipartFile file) throws IOException {

        //새로 추가된 엔티티의 번호
        Long ip = festivalService.join(dto,file);
        redirectAttributes.addFlashAttribute("msg",ip);

        return "redirect:/admin_page/festival_list";

    }

    @GetMapping("/admin_page/festival_delete")
    public String deleteFestival(Long idx){

        festivalService.deleteFestival(idx);
        return "redirect:/admin_page/festival_list";
    }


    @GetMapping("/admin_page/festival_modify")
    public void modifyFestival(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("idx : " + idx);
        FestivalDTO dto = festivalService.read(idx);

        //@@1@@로 되어있는 태그를 #1,#2로 변환
        String savedTags = dto.getTag(); // 데이터베이스에서 읽어온 해시태그

        String[] tags = savedTags.split("@@"); // "@@"로 해시태그 분리

        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            if (!tag.isEmpty()) {
                sb.append("#").append(tag); // 각 해시태그에서 "@@"를 제거하고 "#"을 추가하여 StringBuilder에 추가
            }
        }
        String tag = sb.toString(); // 변환된 형식인 "#12,#34,#56" 형태의 문자열

        model.addAttribute("dto", dto);
        model.addAttribute("tag", tag);

    }


    /*@PostMapping("/admin_page/festival_modify")
    public String modify2(FestivalDTO dto){

        festivalService.modifyFestival(dto);
        return "redirect:/admin_page/festival_list";

    }*/


    @PostMapping("/admin_page/festival_modify")
    public String updateFestival(@ModelAttribute FestivalDTO festivalDto,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) throws IOException {
        festivalService.update(festivalDto.getIdx(), festivalDto, file);
        redirectAttributes.addFlashAttribute("message", "Successfully updated festival");
        return "redirect:/admin_page/festival_list";
    }


}
