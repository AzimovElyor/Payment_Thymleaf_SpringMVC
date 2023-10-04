package uz.pdp.thmleaf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.thmleaf.dto.*;
import uz.pdp.thmleaf.service.CardService;
import uz.pdp.thmleaf.service.TransactionService;
import uz.pdp.thmleaf.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    private final UserService userService;
    private final CardService cardService;
    private final TransactionService transactionService;

    @GetMapping("/create")
    public String getTransactionPage(
            Model model,
            HttpServletRequest req
            ){
        UserResponseDto user = (UserResponseDto)req.getSession().getAttribute("user");
        List<CardResponseDto> userCards = cardService.getUserCards(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("cards", userCards);
        return "transaction/createTransaction";
    }
    @PostMapping("/create")
    public String createTransaction(
            @ModelAttribute TransactionRequestDto requestDto,
            Model model,
            HttpServletRequest req
            ){
        HttpSession session = req.getSession();
        Object create = transactionService.create(requestDto);
        UserResponseDto user = (UserResponseDto)session.getAttribute("user");
        List<CardResponseDto> userCards = cardService.getUserCards(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("cards", userCards);
      if(create instanceof String){
          return "redirect:/transaction/user-transactions";
      }else {
          List<ExceptionDto> exceptionDtos = (List<ExceptionDto>) create;
          return "transaction/createTransaction";
      }

    }
    @GetMapping("/user-transactions")
    public String userTransactions(
            @ModelAttribute UserTransactions transactions,
            Model model,
            HttpServletRequest req
            ){
        System.out.println(transactions);
        HttpSession session = req.getSession();
        UserResponseDto user = (UserResponseDto)session.getAttribute("user");
            List<TransactionResponseDto> userTransactions = transactionService.getUserTransactions(user.getId(), transactions);
        model.addAttribute("transactions",userTransactions);
        model.addAttribute("user", user);
        return "/transaction/userTransactions";
    }



}
