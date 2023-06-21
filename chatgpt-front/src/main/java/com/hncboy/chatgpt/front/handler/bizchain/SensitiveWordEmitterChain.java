package com.hncboy.chatgpt.front.handler.bizchain;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.hncboy.chatgpt.base.handler.SensitiveWordHandler;
import com.hncboy.chatgpt.base.handler.response.R;

import com.hncboy.chatgpt.front.domain.vo.ChatReplyMessageVO;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.HandlerContext;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.List;

/**
 * @author hncboy
 * @date 2023-3-29
 * 敏感词检测
 */
@Component
public class SensitiveWordEmitterChain extends AbstractResponseEmitterChain {

    @Override
    public R doChain(HandlerContext context) {
        ChatCompletion request = (ChatCompletion) context.getRequest();
        try {
            List<String> prompts = SensitiveWordHandler.checkWord(getPrompt(request));
            if (CollectionUtil.isNotEmpty(prompts)) {
                ResponseBodyEmitter emitter = context.getEmitter();
                String msg = StrUtil.format("发送失败，包含敏感词{}", prompts);
                emitter.send(ChatReplyMessageVO.reply(request.getModel(), msg));
                emitter.complete();
                return R.fail(msg);
            }
//            List<String> prompts = SensitiveWordHandler.checkWord(request.getPrompt());
//            if (CollectionUtil.isNotEmpty(systemMessages)) {
//                chatReplyMessageVO.setText(StrUtil.format("发送失败，系统消息包含敏感词{}", prompts));
//                emitter.send(ObjectMapperUtil.toJson(chatReplyMessageVO));
//                emitter.complete();
//                return;
//            }
            return R.success();
        } catch (IOException e) {
            return R.fail(e.getMessage());
        }
    }

    private String getPrompt(ChatCompletion request) {
        if(request.getMessages() == null || request.getMessages().size() == 0){
            return "";
        }

        if(request.getMessages().size() == 1){
            return request.getMessages().get(0).getContent();
        }

        return request.getMessages().get(request.getMessages().size() - 1).getContent();
    }
}
