package com.festival.festival.controller.admin;

import com.festival.festival.dto.ExpDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.service.ExpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
public class ExpController {

    private final ExpService expService;

    @GetMapping("admin_page/exp_list")//리스트 불러오기
    public void exp_review(PageRequestDTO pageRequestDTO, Model model, String keyword) {
        log.info("pageRequestDTO:" + pageRequestDTO);
        int page = pageRequestDTO.getPage();
        int pageNum = 10 * (page - 1);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("result",expService.getList(pageRequestDTO));
    }

    @GetMapping("/admin_page/exp_detail")
    public void read(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("idx : " + idx);
        ExpDTO dto = expService.read(idx);

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

    @GetMapping("/admin_page/exp_writedo")
    public String writeExp(){
        return "/admin_page/exp_write";
    }

    @PostMapping("/admin_page/exp_insertdo")
    public String insertExp(ExpDTO dto, RedirectAttributes redirectAttributes, Model model, MultipartFile file) throws IOException {

        //새로 추가된 엔티티의 번호
        Long ip = expService.join(dto,file);
        redirectAttributes.addFlashAttribute("msg",ip);
        return "redirect:/admin_page/exp_list";

    }

    @GetMapping("/admin_page/exp_delete")
    public String deleteExp(Long idx){

        expService.deleteExp(idx);
        return "redirect:/admin_page/exp_list";
    }


    @GetMapping("/admin_page/exp_modify")
    public void modifyExp(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){


        log.info("idx : "+ idx);
        ExpDTO dto = expService.read(idx);

        //@@1@@로 되어있는 태그를 #1,#2로 변환
        String savedTags = dto.getTag(); // 터베이스에서 읽어온 해시태그

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

/*    // post 방식이라 같아도 상관없음!!
    @Transactional
    @PostMapping("/admin_page/exp_modify")
    public String modify2(ExpDTO dto){

        expService.modifyExp(dto);
        return "redirect:/admin_page/exp_list";

    }*/

    @PostMapping("/admin_page/exp_modify")
    public String updateExp(@ModelAttribute ExpDTO expDto,
                            @RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) throws IOException {
        expService.update(expDto.getIdx(), expDto, file);
        redirectAttributes.addFlashAttribute("message", "Successfully updated exp");
        return "redirect:/admin_page/exp_list";
    }




}