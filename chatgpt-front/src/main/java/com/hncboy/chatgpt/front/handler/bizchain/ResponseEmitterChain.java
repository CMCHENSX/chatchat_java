package com.hncboy.chatgpt.front.handler.bizchain;

import com.hncboy.chatgpt.base.handler.response.R;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.HandlerContext;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author hncboy
 * @date 2023-3-29
 * SseEmitter 链路
 * 责任链模式实现
 */
public interface ResponseEmitterChain {

    /**
     * 处理请求
     *
     * @param request 请求对象
     * @param emitter 响应对象
     */
    R doChain(HandlerContext context);

    /**
     * 设置下一个处理器
     *
     * @param next 下一个处理器
     */
    void setNext(ResponseEmitterChain next);

    /**
     * 获取下一个处理器
     *
     * @return 下一个处理器
     */
    ResponseEmitterChain getNext();

    /**
     * 获取前一个处理器
     *
     * @return 前一个处理器
     */
    ResponseEmitterChain getPrev();

    /**
     * 设置前一个处理器
     *
     * @param prev 前一个处理器
     */
    void setPrev(ResponseEmitterChain prev);
}
