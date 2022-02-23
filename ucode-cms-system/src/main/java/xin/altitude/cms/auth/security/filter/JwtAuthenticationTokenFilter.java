package xin.altitude.cms.auth.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import xin.altitude.cms.auth.model.LoginUser;
import xin.altitude.cms.auth.util.SecurityUtils;
import xin.altitude.cms.auth.web.service.CmsTokenService;
import xin.altitude.cms.common.util.StringUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 *
 * @author ucode
 */
// @Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private CmsTokenService cmsTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        LoginUser loginUser = cmsTokenService.getLoginUser(request);
        if (StringUtil.isNotNull(loginUser) && StringUtil.isNull(SecurityUtils.getAuthentication())) {
            cmsTokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
