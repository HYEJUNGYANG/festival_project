package com.festival.festival.controller.user;

import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.QuestionDTO;
import com.festival.festival.dto.UserDTO;
import com.festival.festival.entity.Question;
import com.festival.festival.service.QuestionService;
import com.festival.festival.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/qna")
@Log4j2
@RequiredArgsConstructor
public class QnAController {

    private final UserService userService;
    private final QuestionService questionService;

    @GetMapping("")
    public String list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("pageRequestDTO:" + pageRequestDTO);
        log.info("값 어떻게 보이는지 확인좀: " + questionService.getList(pageRequestDTO));
        model.addAttribute("result", questionService.getList(pageRequestDTO));

        //로그인한 유저의 아이디를 가져옴
        model.addAttribute("user_id", questionService.findUserid());

        //게시글 총 개수 세기
        model.addAttribute("count",questionService.count());


        return "/qna/qna";
    }

    @GetMapping("/write")
    public String write(HttpServletRequest request, Model model) {
        String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);
        return "/qna/qna-write";
    }

    @Transactional
    @PostMapping("/write")
    public String insertQuestion(Principal principal, @RequestParam HashMap<String, Object> map) {
        UserDTO dto = userService.read(principal.getName());
        QuestionDTO questionDTO = new QuestionDTO();

        log.info("글 작성 이후 map값: " + map);

        char priv = map.containsKey("priv") ? map.get("priv").toString().charAt(0) : 'n';

        questionDTO.setTitle(map.get("title").toString());
        questionDTO.setU_id(dto.getId());
        questionDTO.setU_nick(dto.getNick());
        questionDTO.setYn('n');
        questionDTO.setPriv(priv);
        questionDTO.setQ_content(map.get("content").toString());

        questionService.insertQuestion(questionDTO);

        return "redirect:/qna";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam(value = "idx") Long idx, HttpServletRequest request, Model model) {
        String requestURI = request.getRequestURI();
        QuestionDTO dto = questionService.read(idx);

        model.addAttribute("requestURI", requestURI);
        log.info("uri값좀 보자 젭라23414!$#$@!$!@$!@$" + requestURI);
        model.addAttribute("dto", dto);


        return "/qna/qna-write";
    }

    @Transactional
    @PostMapping("/modify")
    public String updateQuestion(Principal principal, @RequestParam HashMap<String, Object> map) {
        UserDTO dto = userService.read(principal.getName());
        QuestionDTO prevQ = questionService.read(Long.valueOf(map.get("idx").toString()));
        QuestionDTO questionDTO = new QuestionDTO();

        log.info("글 작성 이후 map값: " + map);

        char priv = map.containsKey("priv") ? map.get("priv").toString().charAt(0) : 'n';

        questionDTO.setIdx(Long.valueOf(map.get("idx").toString()));
        questionDTO.setTitle(map.get("title").toString());
        questionDTO.setU_nick(dto.getNick());
        questionDTO.setYn(prevQ.getYn());
        questionDTO.setPriv(priv);
        questionDTO.setQ_content(map.get("content").toString());

        questionService.updateQuestion(questionDTO);

        return "redirect:/qna";
    }

    @GetMapping("/qna_delete")
    public String deleteNotice(Long idx){

        questionService.deleteQuestion(idx);
        return "redirect:/qna";
    }
}
