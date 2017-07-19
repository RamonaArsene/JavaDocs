package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ramona.arsene on 7/19/2017.
 */
@WebServlet(name = "HelloWorldServletForward")
public class HelloWorldServletForward extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.getWriter().write("Hello <b>" + request.getParameter("user") + "" + "</b> from the Forward Servlet!" + request.getAttribute("testAttribute"));
    }
}
