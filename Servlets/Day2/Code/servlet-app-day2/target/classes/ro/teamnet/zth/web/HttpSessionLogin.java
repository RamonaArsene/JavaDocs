package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ramona.arsene on 7/19/2017.
 */
public class HttpSessionLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Cookie[] cookies = request.getCookies();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);



        if(userName.equals("admin") && password.equals("admin")){
            out.println("Welcome back, " + userName + "!");
            out.println("SessionID is: " + request.getSession(true).getId());



            for (Cookie cookie : cookies) {
                out.println(" Cookie name: " + cookie.getName() + " Cookie value: " + cookie.getValue());
            }
        }
        else {
            session.setAttribute("username", userName);
            session.setAttribute("session", request.getSession(true));
            for (Cookie cookie : cookies) {
                request.getSession().setAttribute("cookies", cookies);
            }

            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("/views/loginFail.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
