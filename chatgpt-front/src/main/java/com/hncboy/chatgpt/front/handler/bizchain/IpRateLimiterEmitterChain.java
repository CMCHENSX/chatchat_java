package com.hncboy.chatgpt.front.handler.bizchain;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import com.hncboy.chatgpt.base.handler.RateLimiterHandler;
import com.hncboy.chatgpt.base.handler.response.R;
import com.hncboy.chatgpt.base.util.WebUtil;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.hncboy.chatgpt.front.domain.vo.ChatReplyMessageVO;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.HandlerContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

/**
 * @author lizhongyuan
 * Ip 限流处理
 */
@Component
public class IpRateLimiterEmitterChain extends AbstractResponseEmitterChain {

    @Override
    public R doChain(HandlerContext context) {
        try {
            String ip = WebUtil.getIp();
            // 根据ip判断是够可放行
            Pair<Boolean, String> limitPair = RateLimiterHandler.allowRequest(ip);
            if (!limitPair.getKey()) {
                ChatCompletion request = (ChatCompletion) context.getRequest();
                ResponseBodyEmitter emitter = context.getEmitter();
                String msg = StrUtil.format("当前访问人数过多，请等到 {} 后再尝试下", limitPair.getValue());
                emitter.send(ChatReplyMessageVO.reply(request.getModel(), msg));
                emitter.complete();
                return R.fail(msg);
            }
            return R.success();
        } catch (IOException e) {
            return R.fail(e.getMessage());
        }
    }
}
