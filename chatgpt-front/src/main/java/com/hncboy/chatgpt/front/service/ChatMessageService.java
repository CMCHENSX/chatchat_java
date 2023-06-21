package com.hncboy.chatgpt.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hncboy.chatgpt.base.domain.entity.ChatMessageDO;
import com.hncboy.chatgpt.base.enums.ApiTypeEnum;

import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author hncboy
 * @date 2023-3-25
 * 聊天记录相关业务接口
 */
public interface ChatMessageService extends IService<ChatMessageDO> {

    /**
     * 消息处理
     *
     * @param ChatCompletion 消息处理请求参数
     * @return emitter
     */
    ResponseBodyEmitter sendMessage(ChatCompletion ChatCompletion, ResponseBodyEmitter emitter, HttpServletResponse response);

    /**
     * 初始化聊天消息
     *
     * @param ChatCompletion 消息处理请求参数
     * @param apiTypeEnum        API 类型
     * @return 聊天消息
     */
    ChatMessageDO initChatMessage(ChatCompletion ChatCompletion, ApiTypeEnum apiTypeEnum);
}
