package uz.pdp.thmleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.thmleaf.dto.StatisticsDto;
import uz.pdp.thmleaf.dto.TransactionResponseDto;
import uz.pdp.thmleaf.dto.UserResponseDto;
import uz.pdp.thmleaf.dto.UserTransactions;
import uz.pdp.thmleaf.service.TransactionService;
import uz.pdp.thmleaf.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TransactionService transactionService;
    private final UserService userService;

    @GetMapping("/menu")
    public String getAdminPage(
            @ModelAttribute UserTransactions userTransactions,
            Model model,
            HttpServletRequest req
    ){
        HttpSession session = req.getSession();
        UserResponseDto admin =(UserResponseDto) session.getAttribute("user");
        List<TransactionResponseDto> allTransaction = transactionService.getAllTransaction(userTransactions);
        model.addAttribute("admin",admin);
        model.addAttribute("transactions",allTransaction);
        return "/admin/menu";
    }
    @GetMapping("/statistics")
    public String statisticsPage(Model model){
       StatisticsDto statisticsDto = userService.getStatistics();
       model.addAttribute("staticstics", statisticsDto);
        return "/admin/statistics";
    }
}
