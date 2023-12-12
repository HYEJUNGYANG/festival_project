package com.festival.festival.controller.admin;

import com.festival.festival.dto.AnswerDTO;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.QuestionDTO;
import com.festival.festival.entity.Answer;
import com.festival.festival.service.AnswerService;
import com.festival.festival.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private final QuestionService questionService;

    private final AnswerService answerService;

    @GetMapping("admin_page/qa_list")
    public void list_qa(PageRequestDTO pageRequestDTO, Model model){
        log.info("pageRequestDTO:" + pageRequestDTO);
        model.addAttribute("result", questionService.getList(pageRequestDTO));
    }

    @GetMapping("/admin_page/qa_detail")
    public void read(Long idx, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("idx : " + idx);
        QuestionDTO dto = questionService.read(idx);
        if(dto.getAnswer() != null) {
            log.info("answer reading!!!");
            AnswerDTO a_dto = answerService.read(dto.getAnswer());
            //dto.setAnswer(a_dto);
            model.addAttribute("answer", a_dto);
        }
        model.addAttribute("dto", dto);
    }

    @PostMapping("/admin_page/qa_insertdo") //답변을 처음 등록할 경우
    public String insertAnswer(AnswerDTO dto, @RequestParam("idx") Long q_idx){
        log.info("=========================question insert :" + dto);
        Answer answer = answerService.insertAnswer(dto); //answer에 답변내용 넣기
        log.info("===============idx=======:"+answer);
        QuestionDTO questionDTO = questionService.read(q_idx);

        questionDTO.setAnswer(answer); //생성된 답변을 질문에 지정하기

        questionDTO.setYn('y'); //답변이 저장되고 답변대기에서 완료로 변경

        questionService.insertAnswer(questionDTO);
        return "redirect:/admin_page/qa_list";

    }

    @PostMapping("/admin_page/qa_modify")// 이미 있는 답변을 수정할 경우
    public String modifyAnswer(AnswerDTO dto){
        log.info("================answer"+dto);
        answerService.modifyAnswer(dto);
        return "redirect:/admin_page/qa_list";
    }



}