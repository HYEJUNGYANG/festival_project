package com.festival.festival.controller.admin;


import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.entity.Exp;
import com.festival.festival.entity.Festival;
import com.festival.festival.entity.Question;
import com.festival.festival.entity.Reserve;
import com.festival.festival.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


    @Controller
    @RequestMapping("/")
    @Log4j2
    @RequiredArgsConstructor
    public class AdminController {

        private final UserService userService;
        private final FestivalService festivalService;
        private final ExpService expService;
        private final QuestionService questionService;
        private final ReserveService reserveService;

        @GetMapping("admin_page/admin_login")
        public void login_admin(PageRequestDTO pageRequestDTO, Model model) {
            log.info("pageRequestDTO:" + pageRequestDTO);
            model.addAttribute("result");
        }

        @GetMapping("admin_page/admin_main")
        public void main_admin(PageRequestDTO pageRequestDTO, Model model) {
            //총 가입자수
            model.addAttribute("user_count", userService.count());
            //축제 게시글수
            model.addAttribute("fes_count", festivalService.count());
            //체험 게시글수
            model.addAttribute("exp_count", expService.count());
            //현재 예약건수(체험완료,취소완료 x)
            String state = "체험완료";
            String state2 = "취소완료";
            model.addAttribute("reserve_count", reserveService.count(state, state2));
            //축제 최신 게시글 3개 꺼내오기
            List<Festival> fes_dto = festivalService.getTop3List();
            model.addAttribute("fes_dto", fes_dto);
            //체험 최신 게시글 3개 꺼내오기
            List<Exp> exp_dto = expService.getTop3List();
            log.info("============================dto=" + exp_dto);
            model.addAttribute("exp_dto", exp_dto);
            //답변 대기 질문 3개 꺼내오기
            char yn = 'n';
            List<Question> q_dto = questionService.getTop3List(yn);
            log.info("============================dto=" + q_dto);
            model.addAttribute("q_dto", q_dto);
            //취소 요청 예약 3개 꺼내오기
            String cancel = "취소요청";
            List<Reserve> reserve_dto = reserveService.getTop3List(cancel);
            log.info("============================dto=" + reserve_dto);
            model.addAttribute("reserve_dto", reserve_dto);
        }


    }
