package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;
import sun.rmi.log.ReliableLog;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ramona.arsene on 7/19/2017.
 */
public class HeadersLogFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);

        Map<String, String> map = new HashMap<String, String>();
        HttpServletRequest request = (HttpServletRequest) req;

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        for(String key : map.keySet()){
            LogFileWriter.logHeader(key, map.get(key));
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
