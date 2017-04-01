package com.sx.map.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodeFilter implements Filter{

	String encoding = null;
    FilterConfig filterConfig = null;

    
    public void destroy() {
     this.encoding = null;
     this.filterConfig = null;
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
      FilterChain arg2) throws IOException, ServletException {
     
     HttpServletRequest request = (HttpServletRequest) arg0;
           HttpServletResponse response = (HttpServletResponse) arg1;
           response.setHeader("Pragma", "No-Cache");
   		   response.setHeader("Cache-Control", "No-Cache");
   		   response.setDateHeader("Expires", 0);
           request.setCharacterEncoding(encoding);
           response.setContentType("text/html; charset="+encoding);
          
           arg2.doFilter(arg0,arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
     this.filterConfig = arg0;
     this.encoding = arg0.getInitParameter("encoding");
    }


}
