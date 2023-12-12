package com.festival.festival.controller.user;

import com.festival.festival.entity.Festival;
import com.festival.festival.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/search")
@Log4j2
@RequiredArgsConstructor
public class SearchController {

    private final FestivalRepository festivalRepository;

    @GetMapping("")
    public String search() {
        return "search";
    }

    @PostMapping("")
    public String keywordSearch(@RequestParam HashMap<String, Object> map, Model model) {
        log.info("넘어온 값 확인!! " + map);
        log.info("key값이 존재하는가? (keyword): " + !map.get("keyword").toString().isEmpty());
        log.info("key값이 존재하는가? (date): " + !map.get("date").toString().isEmpty());
        log.info("key값이 존재하는가? (area): " + !map.get("area").toString().isEmpty());

        List<String> zone = null;
        if (!map.get("area").toString().isEmpty()) {
            zone = List.of(map.get("area").toString().split(","));
            map.put("area", zone);
            log.info("zone 리스트 값 확인: " + zone);
        }

        model.addAttribute("keyword", map.get("keyword"));
        if (!map.get("area").toString().isEmpty()) {
            model.addAttribute("zone", zone);
        }
        if (!map.get("date").toString().isEmpty()) {
            model.addAttribute("date", map.get("date"));
        }

        List<Festival> dto = festivalRepository.getFestivalListByKeyword(map);
        model.addAttribute("dto", dto);

        return "/search";
    }
}
