package com.festival.festival.controller.user;

import com.festival.festival.auth.PrincipalDetails;
import com.festival.festival.dto.FestivalDTO;
import com.festival.festival.dto.UserDTO;
import com.festival.festival.entity.Festival;
import com.festival.festival.entity.User;
import com.festival.festival.service.FestivalService;
import com.festival.festival.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/festival")
@Log4j2
@RequiredArgsConstructor
public class FestivalController {

    @Autowired
    private final FestivalService festivalService;
    @Autowired
    private final UserService userService;

    @GetMapping("")
    public String list(@RequestParam(value = "zone", defaultValue = "전체") String zone, Model model) {

        List<Festival> dto = festivalService.getList(zone);
        model.addAttribute("dto", dto);
        return "/festival/festival";
    }

    @GetMapping("/detail")
    //    defaultValue 임시로 넣은거라서 나중에 테스트 완료되면 뺄 것!
    public String detail(@RequestParam(value = "idx", defaultValue = "1") Long idx, Principal principal, Model model) {
        if (idx != null) {

            String fList = null;

            if (principal != null) {
                String id = principal.getName();
                UserDTO userDTO = userService.read(id);
                fList = userDTO.getF_list();

            }

            FestivalDTO festivalDTO = festivalService.read(idx);

            //@@1@@로 되어있는 태그를 #1,#2로 변환
            String savedTags = festivalDTO.getTag(); // 데이터베이스에서 읽어온 해시태그

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
            model.addAttribute("dto", festivalDTO);
            model.addAttribute("fList", fList);
        }
        return "/festival/festival-detail";
    }

    @Transactional
    @PostMapping("/detail")
    public String toggleFavorite(Authentication authentication, @RequestParam long itemId) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        UserDTO userDTO = userService.read(user.getId());

        String f_list = userDTO.getF_list();
        List<String> items;

        // 찜 목록에서 해당 체험 id를 추가하거나 제거
        if (f_list == null || f_list.isEmpty()) {
            items = new ArrayList<>();
        } else {
            f_list = f_list.substring(2);
            items = new ArrayList<>(Arrays.asList(f_list.split("@@")));
        }

        String itemIdStr = String.valueOf(itemId);
        if (items.contains(itemIdStr)) { // 이미 찜 목록에 있으면 제거
            items.remove(itemIdStr);
        } else {
            items.add(itemIdStr); // 찜 목록에 없으면 추가
        }

        if (items.size() == 0) {
            userDTO.setF_list("");
        } else {
            String list_f = String.join("@@", items);
            userDTO.setF_list("@@" + list_f + "@@");
        }

        userService.update(userService.dtoToEntity(userDTO));
        return "redirect:/festival/detail?idx=" + itemId;
    }
}
