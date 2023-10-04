package uz.pdp.thmleaf.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import uz.pdp.thmleaf.dto.UserResponseDto;
import uz.pdp.thmleaf.enums.UserRole;

import java.io.IOException;
import java.util.List;

@Slf4j
public class UserFilter implements Filter{
    private final List<String> ADMIN_URL = List.of(
            "/user/get-all"
    );
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req =(HttpServletRequest) request;
        HttpServletResponse resp =(HttpServletResponse) response;
        String url = req.getServletPath();

            HttpSession session = req.getSession();
            UserResponseDto user = ( UserResponseDto)session.getAttribute("user");
            if(user == null){
                log.info("Filterga tushdi url :  " + url );
                resp.sendRedirect("/auth/login");
            }else if(!ADMIN_URL.contains(url) && user.getUserRole() == UserRole.ADMIN){
               resp.sendRedirect("/admin/menu");
            }
            else if(ADMIN_URL.contains(url) && user.getUserRole() == UserRole.USER){
                resp.sendRedirect("/user/menu");
            }else {
                chain.doFilter(request, response);
            }
            // Foydalanuvchi login qilganmi tekshirish
            // Agar login bo'lmagan bo'lsa, /login sahifasiga yo'naltirish
            // Agar login qilgan bo'lsa, URL ni to'g'rilab ketish
            // Misol: response.sendRedirect("/login");


    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("User filter yaratildi");
    }

    @Override
    public void destroy() {
    }

    }



