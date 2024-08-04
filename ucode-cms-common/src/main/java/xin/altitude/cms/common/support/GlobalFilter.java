package xin.altitude.cms.common.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GlobalFilter implements Filter {
    private final static Logger log = LoggerFactory.getLogger(GlobalFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        spendTime(request, response, chain);
    }

    /**
     * 统计API接口的执行耗时
     */
    private static void spendTime(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.debug("请求[{}]执行开始", req.getRequestURI());
        // 在请求之前执行的操作
        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response); // 继续执行下一个过滤器或目标资源
        // 在请求之后执行的操作
        long endTime = System.currentTimeMillis();
        long timeSpent = endTime - startTime;
        log.debug("请求[{}]执行结束，请求耗时：{}毫秒", req.getRequestURI(), timeSpent);
    }
}
