package org.huangzi.main.authority.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.huangzi.main.common.utils.APIResponse;
import org.huangzi.main.common.utils.ConstConfig;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: XGLLHZ
 * @date: 2019/8/23 11:08
 * @description: 授权失败处理-权限不足
 */
@Component
public class PermissionAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        APIResponse apiResponse = new APIResponse();
        apiResponse.setRecode(ConstConfig.RE_AUTHORITY_ERROR_CODE);
        apiResponse.setRemsg(ConstConfig.RE_AUTHORITY_ERROR_MESSAGE);
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(apiResponse));
        out.flush();
        out.close();
    }

}
