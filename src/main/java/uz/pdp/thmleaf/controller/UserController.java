package uz.pdp.thmleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.thmleaf.dto.CardResponseDto;
import uz.pdp.thmleaf.dto.ExceptionDto;
import uz.pdp.thmleaf.dto.UserRequestDto;
import uz.pdp.thmleaf.dto.UserResponseDto;
import uz.pdp.thmleaf.service.CardService;
import uz.pdp.thmleaf.service.UserService;

import java.util.List;
import java.util.UUID;
;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CardService cardService;
    @GetMapping("/get-all")
    public String getAll(
            @RequestParam(required = false,defaultValue = "0") int page,
            @RequestParam(required = false,defaultValue = "15") int size,
            Model model

    ){
        List<UserResponseDto> all = userService.getAll(page, size);
        model.addAttribute("users",all);
        return "/user/allUser";
    }
    @GetMapping("/menu")
    public String getUserMenu(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        UserResponseDto user = (UserResponseDto)session.getAttribute("user");
        List<CardResponseDto> userCards = cardService.getUserCards(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("cards", userCards);
        return "main";
    }
    @GetMapping("/update")
    public String getUserUpdatePage(
            Model model,
            HttpServletRequest req
    ){
        HttpSession session = req.getSession();
        UserResponseDto user = (UserResponseDto)session.getAttribute("user");
        model.addAttribute("user",user);
      return "/user/edit";
    }
    @PostMapping("/update")
    public String updateUser(
            @ModelAttribute UserRequestDto userRequestDt,
            Model model,
            HttpServletRequest request
            ){
        List<ExceptionDto> exceptionDtos ;
        HttpSession session = request.getSession();
        UserResponseDto user = (UserResponseDto)session.getAttribute("user");
        UUID userId = (UUID) session.getAttribute("userId");
        Object update = userService.updateUser(userRequestDt, userId);
        List<CardResponseDto> userCards = cardService.getUserCards(userId);
        model.addAttribute("cards", userCards);
        if(update instanceof UserResponseDto updateUser){
            model.addAttribute("user",updateUser);
            session.setAttribute("user",updateUser);
            return "main";
        }else if(update instanceof ExceptionDto exceptionDto){
            exceptionDtos = List.of(exceptionDto);
        }else{
            exceptionDtos =(List<ExceptionDto>) update;
        }

        model.addAttribute("user",user);
        model.addAttribute("exceptions" ,exceptionDtos);
        return "/user/edit";
    }
    @GetMapping("/delete")
    public String getDeletePage(){
        return "/user/delete";
    }
    @PostMapping("/delete")
    public String deleteUser(
            Model model,
            HttpServletRequest req
    ){
        HttpSession session = req.getSession();
        UUID userId = (UUID) session.getAttribute("userId");
        Object delete = userService.deleteUser(userId);
        if(delete instanceof String deleteUser){
            session.invalidate();
            return "/auth/login";
        }
        else{
            ExceptionDto exceptionDto = (ExceptionDto) delete;
            model.addAttribute("exception",exceptionDto);
            return "/user/delete";
        }
    }
    @GetMapping("/logout")
    public String logOut( HttpServletRequest req){
        req.getSession().invalidate();
        return "redirect:/home";
    }



}
