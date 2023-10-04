package uz.pdp.thmleaf.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import uz.pdp.thmleaf.dto.UserResponseDto;
import uz.pdp.thmleaf.enums.UserRole;

import java.io.IOException;
@Slf4j
public class AdminFilter implements Filter {
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
        }else if(url.startsWith("/admin") && user.getUserRole() == UserRole.USER){
            resp.sendRedirect("/user/menu");
        }else {
            chain.doFilter(request, response);
        }
    }
}
