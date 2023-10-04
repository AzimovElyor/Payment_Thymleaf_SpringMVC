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
import uz.pdp.thmleaf.dto.*;
import uz.pdp.thmleaf.enums.UserRole;
import uz.pdp.thmleaf.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @GetMapping("/sign-up")
    public String getSignUpPage(){
        return "/auth/signUp";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "/auth/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto,  HttpServletRequest req,Model model){
    Object loginUser= userService.login(loginDto);
    if(loginUser instanceof UserResponseDto login ){
        HttpSession session = req.getSession();
        session.setAttribute("username",login.getUsername());
        session.setAttribute("userId",login.getId());
        session.setAttribute("user",login);
        if(login.getUserRole() == UserRole.USER) return "redirect:/user/menu";
    return "redirect:/admin/menu";
    }
    ExceptionDto exceptionDto = (ExceptionDto) loginUser;
        model.addAttribute("exception",exceptionDto);
        return "/auth/login";
      }

    @PostMapping("/sign-up")
    public String createUser(
            @ModelAttribute UserRequestDto userRequestDto,
            Model model,
            HttpServletRequest req
    ){
        List<ExceptionDto> exceptionDtos ;
        Object newUser = userService.create(userRequestDto);
        if(newUser instanceof UserResponseDto user ) {
            HttpSession session = req.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());
            session.setAttribute("user", user);
            if(user.getUserRole() == UserRole.USER) return "redirect:/user/menu";
           else return "redirect:/admin/menu";
        } else if (newUser instanceof ExceptionDto exception) {
            exceptionDtos =List.of(exception);
        }else {
            exceptionDtos =(List<ExceptionDto>) newUser;
        }
        model.addAttribute("exceptions" ,exceptionDtos);
        return "/auth/signUp";
    }


}
