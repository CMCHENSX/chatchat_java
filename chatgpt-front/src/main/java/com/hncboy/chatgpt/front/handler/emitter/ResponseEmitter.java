package com.hncboy.chatgpt.front.handler.emitter;

import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author hncboy
 * @date 2023-3-24
 * 响应内容
 */
public interface ResponseEmitter {

    /**
     * 消息请求转 Emitter
     *
     * @param ChatCompletion 消息处理请求
     * @param emitter            SseEmitter
     */
    void requestToResponseEmitter(ChatCompletion ChatCompletion, ResponseBodyEmitter emitter, HttpServletResponse response);
}
