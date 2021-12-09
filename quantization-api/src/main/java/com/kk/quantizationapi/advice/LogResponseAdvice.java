package com.kk.quantizationapi.advice;

import com.kk.quantizationapi.common.trace.TraceData;
import com.kk.quantizationapi.utils.DateUtil;
import com.kk.quantizationapi.utils.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: kk
 * @Date: 2021/11/19 16:56
 */
@ControllerAdvice(
        basePackages = {"com.kk"}
)
public class LogResponseAdvice implements ResponseBodyAdvice<Object> {
    private Logger log = LogManager.getRootLogger();
    private static int MAX_MSG_LENGTH = 4096;

    public LogResponseAdvice() {
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getURI().toString();
        String bodyJson = JsonUtil.getJSONString(body);
        long elapsed = System.currentTimeMillis() - (Long) TraceData.seqStart.get();
        String seqNo = null;

       /* try {
            //seqNo = String.valueOf(TraceData.traceId.get());
            //response.getHeaders().add("seqNo", seqNo);
            if (bodyJson != null && bodyJson.length() > MAX_MSG_LENGTH) {
                bodyJson = Lz4Compress.compress(bodyJson.substring(0, MAX_MSG_LENGTH));
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }*/

        String methodName = "";
        if (!StringUtils.isEmpty(uri)) {
            String[] uris = uri.split("/");
            methodName = uris[uris.length - 1];
        }

        this.log.info("{}| {} | 响应ip: {} | uri = {} | elapsed = {}ms | body = {} ", new Object[]{DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"), seqNo, request.getRemoteAddress().getHostString(), uri, elapsed, bodyJson, methodName});
        return body;
    }
}

