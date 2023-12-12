package com.festival.festival.controller.admin;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.ReserveDTO;
import com.festival.festival.service.ReserveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class ReserveController {

    @Autowired
    private final ReserveService reserveService;

    @GetMapping("admin_page/reserve_list")
    public void list_reserve(PageRequestDTO pageRequestDTO, Model model){
        log.info("pageRequestDTO:" + pageRequestDTO);
        model.addAttribute("result", reserveService.getList(pageRequestDTO));
    }

    @GetMapping("/admin_page/reserve_detail")
    public void read(int num, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("reservenum : " + num);
        ReserveDTO dto = reserveService.read(num);
        model.addAttribute("dto", dto);
    }

@GetMapping("/admin_page/reserve_modify")
public void modifyReserve(int num, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){


    log.info("num : "+ num);
    ReserveDTO dto = reserveService.read(num);
    model.addAttribute("dto", dto);

}

    @PostMapping("/admin_page/reserve_modify")
    public String modify2(ReserveDTO dto){

        reserveService.modifyReserve(dto);

        return "redirect:/admin_page/reserve_list";

    }




}