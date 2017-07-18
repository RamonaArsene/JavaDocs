package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by ramona.arsene on 7/18/2017.
 */
@WebServlet(name = "InfoHttpServlet")
public class InfoHttpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <th>Firstname</th>" +
                "    <th>Lastname</th> " +
                "  </tr>" +
                "  <tr>" +
                    "<center>"+
                "    <td>" + request.getParameter("firstName")+ "</td>" +
                "    <td>" + request.getParameter("lastName") + "</td>" +
                        "<center>"+
                "  </tr>" +
                "</table>");

        out.println("Method is: "+ request.getMethod());
        out.println("<br>");
        out.println("Query string is: " + request.getQueryString().split("&"));
        out.println("<br>");
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//
//            out.println(cookie.getName() + " " + cookie.getValue() + "<br>");
//            out.println("<br>");
//
//        }
        ArrayList<String> parameterNames = new ArrayList<String>();

        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            parameterNames.add(parameterName);
        }

        out.println(parameterNames);
        for (String parameterName : parameterNames) {
            out.println(request.getParameter(parameterName));
        }



    }
}
