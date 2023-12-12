package com.festival.festival.controller.user;

import com.festival.festival.dto.UserDTO;
import com.festival.festival.entity.Role;
import com.festival.festival.entity.User;
import com.festival.festival.repository.UserRepository;
import com.festival.festival.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/join")
@Log4j2
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("")
    public String join() {
        return "join";
    }

    @PostMapping("")
    public String join(@ModelAttribute User user, Model model){
        log.info("=====join/setRole=========");
        user.setRole(Role.ROLE_USER);
        log.info("=====join/passwordencode=========");
        String encodePwd = bCryptPasswordEncoder.encode(user.getPw());
        user.setPw(encodePwd);
        log.info("=====join/save=========");
        userRepository.save(user);  //반드시 패스워드 암호화해야함
        log.info("=====join/redirect=========");

        model.addAttribute("msg", "회원 가입이 완료되었습니다!");

        return "/login";
    }

    @PostMapping("/check_id")
    @ResponseBody
    public UserDTO checkId(String id) {
        UserDTO dto = userService.read(id);
        log.info(id);
        return dto;
    }
}
