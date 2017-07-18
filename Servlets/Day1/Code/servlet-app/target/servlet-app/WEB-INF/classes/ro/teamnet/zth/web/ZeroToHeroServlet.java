package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ramona.arsene on 7/18/2017.
 */
@WebServlet(name = "ZeroToHeroServlet")
public class ZeroToHeroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.getWriter().write(this.handleRequest(request));

    }


    protected String handleRequest(HttpServletRequest request){
        String response = "Hello <b>" + request.getParameter("firstName") + " " + request.getParameter("lastName") + "</b>! Enjoy ZeroToHero!";
        return response;
    }
}
