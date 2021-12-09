package com.kk.quantizationapi.intercepter;

import com.kk.quantizationapi.common.trace.TraceData;
import com.kk.quantizationapi.utils.DateUtil;
import com.kk.quantizationapi.utils.JsonUtil;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * @Author: kk
 * @Date: 2021/11/18 17:24
 * 参数拦截器
 */
public class ParameterIntercepter implements HandlerInterceptor {
    private String[] excludedPageArray;
    private Logger log = LogManager.getRootLogger();
    @Value("${test}")
    boolean test;
    /**
     * 进入controller方法之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (this.test) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Headers", "app_key,secret_key,timestamp,sid,source,version,contentType,Content-Type");
            this.log.debug("测试环境支持跨域");
        }

        String seqNo = request.getHeader("seqNo");
        if (!StringUtils.isEmpty(seqNo)) {
            //TraceData.traceId.set(Long.valueOf(seqNo));
        }

        TraceData.seqStart.set(System.currentTimeMillis());
       // this.preloadSession(request);
        String uri = request.getRequestURI();
        String json = "忽略(图片上传请求)";
        String body = "忽略(图片上传请求)";
        boolean ifUploadReq = false;
        String remoteAddr;
        if (this.excludedPageArray != null) {
            remoteAddr = request.getServletPath();
            String[] var10 = this.excludedPageArray;
            int var11 = var10.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                String excludedPage = var10[var12];
                if (remoteAddr.indexOf(excludedPage) > 0) {
                    ifUploadReq = true;
                }
            }
        }

        if (!ifUploadReq) {
            json = JsonUtil.getJSONString(request.getParameterMap());
            body = IOUtils.toString(request.getInputStream(), Charset.forName("UTF-8")).replace("\n", "");
        }

        remoteAddr = request.getRemoteAddr();
        if (request.getHeader("X-Real-IP") != null) {
            remoteAddr = request.getHeader("X-Real-IP");
        }

        String methodName = "";
        if (!StringUtils.isEmpty(uri)) {
            String[] uris = uri.split("/");
            methodName = uris[uris.length - 1];
        }

        this.log.info("{}| {} | 请求ip: {} | uri = {} | header = {} | params = {} | body = {}  | {}"
                , new Object[]{DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), seqNo, remoteAddr, uri, json, body, this.getHeader(request), methodName});
        return !request.getMethod().equals("OPTIONS");

        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public String getHeader(HttpServletRequest request) {
        StringBuilder str = new StringBuilder();
        Enumeration e = request.getHeaderNames();

        while(e.hasMoreElements()) {
            String key = (String)e.nextElement();
            str.append(key).append(":").append(request.getHeader(key)).append("||");
        }

        return str.toString();
    }
    /**
     * 调用完controller之后，视图渲染之前
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }
    /**
     * 整个完成之后，通常用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
