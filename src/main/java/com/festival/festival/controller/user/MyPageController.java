package com.festival.festival.controller.user;

import com.festival.festival.auth.PrincipalDetails;
import com.festival.festival.dto.*;
import com.festival.festival.entity.Reserve;
import com.festival.festival.entity.Review;
import com.festival.festival.entity.User;
import com.festival.festival.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/mypage")
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final ReserveService reserveService;
    private final ReviewService reviewService;
    private final FestivalService festivalService;
    private final ExpService expService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/profile"})
    public String modify(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        log.info("=====form/loginInfo=========");
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();

        UserDTO dto = userService.read(user.getId());
        model.addAttribute("dto", dto);

        return "/mypage/mypage-profile";
    }

    @PostMapping({"/profile_modify"}) // 닉네임, 전화번호 정보 수정
    public String modify_nick_tel(String nick, String tel,Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String id = principal.getUsername();

        log.info(id,nick,tel);
        userService.modify_nick_tel(id,nick,tel);

        return "redirect:/mypage/profile";
    }

    @GetMapping("/reservation")
    public String reservation(PageRequestDTO pageRequestDTO, Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String id = principal.getUsername();

        List<Reserve> dto = reserveService.selectReserve(id);
        model.addAttribute("dto", dto);

        return "/mypage/mypage-reservation";
    }


    @GetMapping("/profile/pw-change")
    public String pwChange() {
        return "/mypage/pw-change";
    }

    @ResponseBody
    @PostMapping("/profile/pw-change")
    public String pwChange(@RequestParam("pw") String input_pw, @RequestParam("new_pw") String new_pw, Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        String id = user.getId();

        String login_Pw = user.getPw();
        log.info("pw:"+input_pw);
        if (passwordEncoder.matches(input_pw, login_Pw)) {
            // Passwords match
            String encodePwd = bCryptPasswordEncoder.encode(new_pw);
            userService.updatePw(id, encodePwd);
            return "success";
        } else {
            // Passwords do not match
            log.info("=========비밀번호 다름!!!!===========");
            return "fail";
        }
    }

    @GetMapping("/favorite")
    public String favorite(Authentication authentication, Model model) {
        log.info("=====form/loginInfo=========");
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        UserDTO dto = userService.read(user.getId());

        if (dto.getF_list().length() != 0) {
            String f_list = dto.getF_list().substring(2);
            String[] replacedStr = f_list.split("@@");

            int[] result = Arrays.stream(replacedStr)
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();
            log.info(Arrays.toString(result));
            log.info("-========================"+ festivalService.getListByIdxs(result));
            model.addAttribute("fDTO", festivalService.getListByIdxs(result));
        }
        else {
            int[] result = new int[0];
            model.addAttribute("fDTO", festivalService.getListByIdxs(result));
        }


        model.addAttribute("dto", dto);


        if (dto.getE_list().length() != 0) {
            String e_list = dto.getE_list().substring(2);
            String[] replacedStr1 = e_list.split("@@");
            log.info("================================" + replacedStr1[0]);
            int[] result1 = Arrays.stream(replacedStr1)  // replacedStr -> replacedStr1
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();
            log.info(Arrays.toString(result1));

            model.addAttribute("eDTO", expService.getListByIdxs(result1));
        } else {
            int[] result1 = new int[0];
            model.addAttribute("eDTO", expService.getListByIdxs(result1));
        }

        return "/mypage/mypage-favorite";
    }

    @PostMapping("/favorite")
    public String removeFavorite(Authentication authentication, @RequestParam String type, @RequestParam int itemId){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        UserDTO userDTO = userService.read(user.getId());

        if (type.equals("f")) {
            if (userDTO.getF_list().length() != 0) {
                String f_list = userDTO.getF_list().substring(2);
                List<String> items = new ArrayList<>(Arrays.asList(f_list.split("@@")));
                items.remove(String.valueOf(itemId));
                if (items.size() == 0) {
                    userDTO.setF_list("");
                } else {
                    String list_f = String.join("@@", items);
                    userDTO.setF_list("@@" + list_f + "@@");
                }
            }
        } else if (type.equals("e")) {
            if (userDTO.getE_list().length() != 0) {
                String e_list = userDTO.getE_list().substring(2);
                List<String> items = new ArrayList<>(Arrays.asList(e_list.split("@@")));
                items.remove(String.valueOf(itemId));
                if (items.size() == 0) {
                    userDTO.setE_list("");
                } else {
                    String list_e = String.join("@@", items);
                    userDTO.setE_list("@@" + list_e + "@@");
                }
            }
        }

        userService.update(userService.dtoToEntity(userDTO));
        return "redirect:/mypage/favorite";
    }


    @GetMapping("/reservation/detail")
    public String reservationDetail(int num, Model model) {

        log.info("num : " + num);
        ReserveDTO dto = reserveService.read(num);
        ExpDTO expDTO = expService.read(dto.getE_idx());

        model.addAttribute("dto", dto);
        model.addAttribute("imgPath", expDTO.getFilepath());

        return "/mypage/mypage-reservation-detail";
    }


    @GetMapping("/review")
    public String review(PageRequestDTO pageRequestDTO, Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String id = principal.getUsername();

        List<Review> dto = reviewService.selectReview(id);
        model.addAttribute("dto", dto);

        log.info("dtor값 !!!!!!!"+dto);

        return "/mypage/mypage-review";
    }

    @GetMapping("/review/write")
    public String reviewWriteInteger(int num, Model model, HttpServletRequest request) {

        log.info("num : " + num);


        String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);

        log.info("requestURI : " + requestURI);

        ReserveDTO dto = reserveService.read(num);
        model.addAttribute("dto", dto);

        log.info("dto : " + dto);

        return "/mypage/review-write";
    }

    @ResponseBody
    @PostMapping("/review/write")
    public String reviewInsert(ReserveDTO reserveDTO, Authentication authentication, ReviewDTO dto){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setU_id(user.getId());
        reviewDTO.setU_nick(user.getNick());
        reviewDTO.setContent(dto.getContent());
        reviewDTO.setStar(dto.getStar());
        reviewDTO.setE_idx(dto.getE_idx());
        reviewDTO.setE_name(dto.getE_name());


        reviewService.insertReview(reviewDTO);
        reserveService.modifyReviewYon(reserveDTO);

        return "/mypage/mypage-review";
    }

    @GetMapping("/review/modify")
    public String reviewModify(Long idx,Model model,HttpServletRequest request){

        String requestURI = request.getRequestURI();
        model.addAttribute("requestURI", requestURI);

        ReviewDTO dto = reviewService.read(idx);
        model.addAttribute("dto",dto);

        return "/mypage/review-write";

    }

    @PostMapping("/review/modify")
    public String reviewModify(Authentication authentication,ReviewDTO dto){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setIdx(dto.getIdx());
        reviewDTO.setU_id(user.getId());
        reviewDTO.setU_nick(user.getNick());
        reviewDTO.setContent(dto.getContent());
        reviewDTO.setStar(dto.getStar());
        reviewDTO.setE_idx(dto.getE_idx());
        reviewDTO.setE_name(dto.getE_name());

        reviewService.updateReview(reviewDTO);

        return "/mypage/mypage-review";

    }

    @GetMapping("/review/delete")
    public String deleteReview(Long idx){

        reviewService.deleteReview(idx);

        return "redirect:/mypage/review";
    }

    /*    @RequestMapping("/reservation/cancel")*/
    @RequestMapping(value="/reservation/cancel", method = RequestMethod.GET)
    public String reserveCancel(int num, RedirectAttributes redirect) {

        reserveService.modifyCancel(num);
        redirect.addAttribute("num",num);

        return "redirect:/mypage/reservation/detail";
    }

}
