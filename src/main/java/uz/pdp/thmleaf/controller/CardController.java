package uz.pdp.thmleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.thmleaf.dto.CardRequestDto;
import uz.pdp.thmleaf.dto.CardResponseDto;
import uz.pdp.thmleaf.dto.UserResponseDto;
import uz.pdp.thmleaf.service.CardService;

import java.util.List;

@Controller
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @GetMapping("/create")
    public String createCardPage(Model model,HttpServletRequest req){
        UserResponseDto user = (UserResponseDto) req.getSession().getAttribute("user");
        model.addAttribute("user",user);
        System.out.println(user);
        return "/card/addCard";
    }
    @PostMapping("/create")
    public String addCard(
            @ModelAttribute CardRequestDto cardRequestDto,
            Model model,
            HttpServletRequest req
            ){
        HttpSession session = req.getSession();
        model.addAttribute("user",(UserResponseDto)session.getAttribute("user"));
        CardResponseDto cardResponseDto = cardService.create(cardRequestDto);
        List<CardResponseDto> userCards = cardService.getUserCards(cardRequestDto.getOwnerId());
        model.addAttribute("cards",userCards);
        return "main";
    }

}
