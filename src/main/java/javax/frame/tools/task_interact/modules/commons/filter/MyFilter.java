package javax.frame.tools.task_interact.modules.commons.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

@Order(1)
@Component
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig);
    }

    /**
     * 输出访问 ip
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //获取访问 ip 地址
        HttpServletRequest req = (HttpServletRequest) request;
        String visitIp = req.getRemoteAddr();
        visitIp = "0:0:0:0:0:0:0:1".equals(visitIp) ? "127.0.0.1" : visitIp;
        // 每次拦截到请求输出访问 ip
        System.out.println("访问 IP = " + visitIp);
        System.out.println("访问路径：" + req.getRequestURI());
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String header = req.getHeader(headerName);
            System.out.println(headerName + ": " + header);
        }
        /*String basepath = "D:/development_tools/httpsdir";
        File file = new File(basepath, req.getRequestURI());
        if (file.exists()){
            long length = file.length();
            response.setContentLengthLong(length);
            int len = -1;
            byte[] buffer = new byte[10240];
            try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                ServletOutputStream outputStream = response.getOutputStream();){
                while ( (len = bis.read(buffer, 0, buffer.length)) != -1){
                    outputStream.write(buffer, 0, len);
                    outputStream.flush();
                }
            }catch (Exception e){}
            return;
        } else if (!file.exists()){
            throw new RuntimeException(req.getRequestURI());
        }*/
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {
    }
}
