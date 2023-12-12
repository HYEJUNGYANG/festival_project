package com.festival.festival.controller.admin;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.ReviewDTO;
import com.festival.festival.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;
    @GetMapping("admin_page/review_list")//리스트 불러오기
    public void list_review(PageRequestDTO pageRequestDTO, Model model, String keyword) {
        log.info("pageRequestDTO:" + pageRequestDTO);
        int page = pageRequestDTO.getPage();
        int pageNum = 10 * (page - 1);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("result", reviewService.getList(pageRequestDTO));
    }

    @GetMapping("/admin_page/review_detail")
    public void read(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("idx : " + idx);
        ReviewDTO dto = reviewService.read(idx);
        model.addAttribute("dto", dto);
    }
    @GetMapping("/admin_page/review_delete")
    public String deleteReview(Long idx){

        reviewService.deleteReview(idx);
        return "redirect:/admin_page/review_list";
    }

}