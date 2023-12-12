package com.festival.festival.controller.user;

import com.festival.festival.auth.PrincipalDetails;
import com.festival.festival.dto.ExpDTO;
import com.festival.festival.dto.ReserveDTO;
import com.festival.festival.dto.UserDTO;
import com.festival.festival.entity.User;
import com.festival.festival.service.ExpService;
import com.festival.festival.service.ReserveService;
import com.festival.festival.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/reservation")
@Log4j2
public class ReservationController {

    @Autowired
    private ExpService expService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReserveService reserveService;

    @GetMapping("")
    public String reservation(@RequestParam(value = "idx") Long idx, Authentication authentication, Model model) {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        User user = principalDetails.getUser();

        ExpDTO expDTO = expService.read(idx);

        model.addAttribute("user", user);
        model.addAttribute("dto", expDTO);

        return "/reservation/reservation";
    }

    @Transactional
    @PostMapping("")
    public String reserveInsert(ReserveDTO reserveDTO, Principal principal, RedirectAttributes attributes) {
        UserDTO userDTO = userService.read(principal.getName());

        reserveDTO.setU_id(userDTO.getId());
        reserveDTO.setU_tel(userDTO.getTel());
        reserveDTO.setU_name(userDTO.getName());
        reserveDTO.setReview('n');
        reserveDTO.setState("예약확정");

        List<Integer> numList = reserveService.getNumList();
        int num = 0;
        while (true) {
            num = (int) (Math.random() * 99999999 + 10000000); // 10000000 ~ 99999999까지 랜덤한 수 생성
            if (!numList.contains(num)) {
                break;
            }
        }
        reserveDTO.setNum(num);

        reserveService.insertReserve(reserveDTO);
        log.info("업데이트 전 count: " + expService.read(reserveDTO.getE_idx()).getCount());
        expService.modifyCount((long) reserveDTO.getCount(), reserveDTO.getE_idx());
        attributes.addAttribute("idx", reserveDTO.getE_idx());
        attributes.addFlashAttribute("rDTO", reserveDTO);

        return "redirect:/reservation/result";
    }

    @Transactional
    @RequestMapping("/result")
    public String reservationResult(@RequestParam("idx") Long idx, @ModelAttribute("rDTO") ReserveDTO reserveDTO, Model model) {
        log.info("dto 잘 넘어오나?" + reserveDTO);  // ok
        log.info("업데이트 후 count: " + expService.read(idx)); // ok 잘 적용됐음

        model.addAttribute("exp", expService.read(idx));
        model.addAttribute("dto", reserveDTO);
        return "/reservation/reservation-result";
    }
}
