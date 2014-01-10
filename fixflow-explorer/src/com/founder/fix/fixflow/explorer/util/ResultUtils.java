/**
 *  Copyright 1996-2013 Founder International Co.,Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author 徐海洋
 */
package com.founder.fix.fixflow.explorer.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 工具类.
 * 
 * 实现绕过jsp/freemaker直接输出文本的简化函数.
 * 
 * @author 徐海洋
 */
public class ResultUtils {

    // -- header 常量定义 --//
    private static final String HEADER_ENCODING = "encoding";
    private static final String HEADER_NOCACHE = "no-cache";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final boolean DEFAULT_NOCACHE = true;

    // -- Content Type 定义 --//
    public static final String TEXT_TYPE = "text/plain";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    public static final String EXCEL_TYPE = "application/vnd.ms-excel";
    public static HttpServletResponse response;
    private static ResultUtils instance;  
    
    
    public static ResultUtils getInstance(HttpServletResponse responseParm) {  
    	 response = responseParm;
         if (instance == null) {  
             instance = new ResultUtils();  
         }  
         return instance;  
    }  
    
    private  ObjectMapper mapper = new ObjectMapper();

    /**
     * <P>
     * 直接输出内容的简便函数.
     * </p>
     * 
     * @param headers
     *            可变的header数组,目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
     */
    public  void render(final String contentType, final String content, final String... headers) {
        HttpServletResponse response = initResponseHeader(contentType, headers);
        try {
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 直接输出文本.
     * 
     * @see #render(String, String, String...)
     */
    public  void renderText(final String text, final String... headers) {
        render(ResultUtils.TEXT_TYPE, text, headers);
    }

    /**
     * 直接输出HTML.
     * 
     * @see #render(String, String, String...)
     */
    public  void renderHtml(final String html, final String... headers) {
        render(ResultUtils.HTML_TYPE, html, headers);
    }

    /**
     * 直接输出XML.
     * 
     * @see #render(String, String, String...)
     */
    public  void renderXml(final String xml, final String... headers) {
        render(ResultUtils.XML_TYPE, xml, headers);
    }

    /**
     * 直接输出JSON.
     * 
     * @param jsonString
     *            json字符串.
     * @see #render(String, String, String...)
     */
    public  void renderJson(final String jsonString, final String... headers) {
        render(ResultUtils.JSON_TYPE, jsonString, headers);
    }

    /**
     * 直接输出JSON,使用Jackson转换Java对象.
     * 
     * @param data
     *            可以是List<POJO>, POJO[], POJO, 也可以Map名值对.
     * @see #render(String, String, String...)
     */
    public  void renderJson(final Object data, final String... headers) {
        HttpServletResponse response = initResponseHeader(ResultUtils.JSON_TYPE, headers);
        try {
            mapper.writeValue(response.getWriter(), data);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 直接输出支持跨域Mashup的JSONP.
     * 
     * @param callbackName
     *            callback函数名.
     * @param object
     *            Java对象,可以是List<POJO>, POJO[], POJO ,也可以Map名值对, 将被转化为json字符串.
     */
    public  void renderJsonp(final String callbackName, final Object object, final String... headers) {
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        String result = new StringBuilder().append(callbackName).append("(").append(jsonString).append(");").toString();

        // 渲染Content-Type为javascript的返回内容,输出结果为javascript语句,
        // 如callback197("{html:'Hello World!!!'}");
        render(ResultUtils.JS_TYPE, result, headers);
    }

    /**
     * 设置禁止客户端缓存的Header.
     */
    public  void setDisableCacheHeader(HttpServletResponse response) {
        // Http 1.0 header
        response.setDateHeader("Expires", 1L);
        response.addHeader("Pragma", "no-cache");
        // Http 1.1 header
        response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
    }

    /**
     * 分析并设置contentType与headers.
     */
    private  HttpServletResponse initResponseHeader(final String contentType, final String... headers) {
        // 分析headers参数
        String encoding = DEFAULT_ENCODING;
        boolean noCache = DEFAULT_NOCACHE;
        for (String header : headers) {
            String headerName = StringUtils.substringBefore(header, ":");
            String headerValue = StringUtils.substringAfter(header, ":");

            if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
                encoding = headerValue;
            } else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
                noCache = Boolean.parseBoolean(headerValue);
            } else {
                throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
            }
        }

        // 设置headers参数
        String fullContentType = contentType + ";charset=" + encoding;
        response.setContentType(fullContentType);
        if (noCache) {
            setDisableCacheHeader(response);
        }

        return response;
    }

}
