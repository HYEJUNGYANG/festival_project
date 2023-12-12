package com.festival.festival.controller.user;

import com.festival.festival.auth.PrincipalDetails;
import com.festival.festival.dto.ExpDTO;
import com.festival.festival.dto.UserDTO;
import com.festival.festival.entity.Exp;
import com.festival.festival.entity.Review;
import com.festival.festival.entity.User;
import com.festival.festival.service.ExpService;
import com.festival.festival.service.ReviewService;
import com.festival.festival.service.UserService;
import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/experience")
@Log4j2
public class ExperienceController {

    @Autowired
    private ExpService expService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;


    @GetMapping("")
    public String list(@RequestParam(value = "zone", defaultValue = "전체") String zone, Model model) {
        List<Exp> dto = expService.getList(zone);
        model.addAttribute("dto", dto);

        return "/experience/experience";
    }

    @GetMapping("/detail")
//    defaultValue 임시로 넣은거라서 나중에 테스트 완료되면 뺄 것!
    public String detail(@RequestParam(value = "idx", defaultValue = "10") Long idx, Principal principal, Model model) {
        String eList = null;

        if (principal != null) {
            String id = principal.getName();
            UserDTO userDTO = userService.read(id);
            eList = userDTO.getE_list();
        }

        ExpDTO expDTO = expService.read(idx);
        List<Review> reviewDto = reviewService.getList(idx);

        //@@1@@로 되어있는 태그를 #1,#2로 변환
        String savedTags = expDTO.getTag(); // 데이터베이스에서 읽어온 해시태그

        String[] tags = savedTags.split("@@"); // "@@"로 해시태그 분리

        List<String> taglist = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (String tag : tags) {
            if (!tag.isEmpty()) {
                taglist.add("#"+tag); // 각 해시태그에서 "@@"를 제거하고 "#"을 추가하여 StringBuilder에 추가
            }
        }
        String tag = sb.toString(); // 변환된 형식인 "#12,#34,#56" 형태의 문자열

        model.addAttribute("taglist", taglist);
        model.addAttribute("idx", "@@" + idx + "@@");
        model.addAttribute("dto", expDTO);
        model.addAttribute("review", reviewDto);
        model.addAttribute("eList", eList);

        return "/experience/experience-detail";
    }

    @Transactional
    @PostMapping("/detail")
    public String toggleFavorite(Authentication authentication, @RequestParam long itemId) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        UserDTO userDTO = userService.read(user.getId());

        String e_list = userDTO.getE_list();
        List<String> items;

        // 찜 목록에서 해당 체험 id를 추가하거나 제거
        if (e_list == null || e_list.isEmpty()) {
            items = new ArrayList<>();
        } else {
            e_list = e_list.substring(2);
            items = new ArrayList<>(Arrays.asList(e_list.split("@@")));
        }

        String itemIdStr = String.valueOf(itemId);
        if (items.contains(itemIdStr)) { // 이미 찜 목록에 있으면 제거
            items.remove(itemIdStr);
        } else {
            items.add(itemIdStr); // 찜 목록에 없으면 추가
        }

        if (items.size() == 0) {
            userDTO.setE_list("");
        } else {
            String list_e = String.join("@@", items);
            userDTO.setE_list("@@" + list_e + "@@");
        }
        userService.update(userService.dtoToEntity(userDTO));
        return "redirect:/experience/detail?idx=" + itemId;
    }
}
