package com.festival.festival.controller.admin;

import com.festival.festival.auth.PrincipalDetails;
import com.festival.festival.dto.PageRequestDTO;
import com.festival.festival.dto.UserDTO;
import com.festival.festival.entity.User;
import com.festival.festival.repository.UserRepository;
import com.festival.festival.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/admin_login")
    public String loginForm() {
        log.info("=====admin_login page access=========");
        return "/admin_page/admin_login";

    }

    public String user_detail(Model model) {
        model.addAttribute("data", "hello!");
        return "detail";
    }

    @GetMapping("admin_page/user_list")//리스트 불러오기
    public void list_user(PageRequestDTO pageRequestDTO, Model model, String keyword) {
        log.info("pageRequestDTO:" + pageRequestDTO);
        int page = pageRequestDTO.getPage();
        int pageNum = 10 * (page - 1);
        model.addAttribute("pageNum", pageNum);
    model.addAttribute("result",userService.getList(pageRequestDTO));
}


    @GetMapping("/admin_page/user_detail")
    public void read(String id, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ){

        log.info("id : " + id);
        UserDTO dto = userService.read(id);
        model.addAttribute("dto", dto);

    }
    @GetMapping("/index")
    public void index(){
        log.info("=====================index===================");
    }

    @GetMapping("/loginInfo")
    @ResponseBody
    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("=====form/loginInfo=========");
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        userService.entityToDto(user);
        System.out.println(user);

        return user.toString();
    }
}
