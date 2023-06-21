package com.hncboy.chatgpt.front.handler.bizchain;

import cn.hutool.extra.spring.SpringUtil;
import com.hncboy.chatgpt.base.config.ChatConfig;
import com.hncboy.chatgpt.base.enums.ApiTypeEnum;
import com.hncboy.chatgpt.base.handler.response.R;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.HandlerContext;
import com.hncboy.chatgpt.front.handler.emitter.AccessTokenResponseEmitter;
import com.hncboy.chatgpt.front.handler.emitter.ApiKeyResponseEmitter;
import com.hncboy.chatgpt.front.handler.emitter.ResponseEmitter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author hncboy
 * @date 2023-3-29
 * 正常发送消息链路，最后一个节点
 */
@Component
public class ChatMessageEmitterChain extends AbstractResponseEmitterChain {

    @Override
    public R doChain(HandlerContext context) {
        ApiTypeEnum apiTypeEnum = SpringUtil.getBean(ChatConfig.class).getApiTypeEnum();
        ResponseEmitter responseEmitter;
        if (apiTypeEnum == ApiTypeEnum.API_KEY) {
            responseEmitter = SpringUtil.getBean(ApiKeyResponseEmitter.class);
        } else {
            responseEmitter = SpringUtil.getBean(AccessTokenResponseEmitter.class);
        }
        ChatCompletion request = (ChatCompletion) context.getRequest();
        ResponseBodyEmitter emitter = context.getEmitter();
        HttpServletResponse response = context.getResponse();
        responseEmitter.requestToResponseEmitter(request, emitter, response);
        return R.success();
    }
}
