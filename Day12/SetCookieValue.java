package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/set090201")
public class SetCookieValue extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                 throws ServletException, IOException {
       
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        PrintWriter out = response.getWriter();
        
        Date d = new Date();

        
        //쿠키 객체를 생성한 후 cookieTest 이름으로 한글 정보를 인코딩해서 쿠키에 저장합니다.
        //Cookie c = new Cookie("cookieTest", "서블릿프로그래밍입니다.");
        Cookie c = new Cookie("cookieTest", 
                               URLEncoder.encode("서블릿프로그래밍입니다.", "utf-8"));
        //쿠키 최대 유효시간 설정
//        c.setMaxAge(1 * 24 * 60 * 60);  //24시간을 초 단위로 설정
        
        //setMaxAge() 메소드로 -1 값을 설정하면 세션 큐키가 됩니다.
        c.setMaxAge(-1); //세션 쿠키를 생성합니다.
        
        response.addCookie(c);
        
        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset=\"utf-8\"></head><body>");
        out.println("현재시간 : " + d);
        out.println("<br> 문자열을 Cookie에 저장합니다.");
        out.println("</body></html>");
        out.close();
    }
    
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                                  throws ServletException, IOException {
        doGet(request, response);
    }
    
}