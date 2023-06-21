package com.hncboy.chatgpt.front.handler.bizchain;

import com.hncboy.chatgpt.front.handler.bizchain.router.BizChainRouter;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.RequestType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class BizChainRouterBuilder {

    private final ChatMessageEmitterChain chatMessageEmitterChain;
    private final IpRateLimiterEmitterChain ipRateLimiterEmitterChain;
    private final SensitiveWordEmitterChain sealsWordEmitterChain;

    @Bean
    public BizChainRouter bizChainRouter() {
        BizChainRouter router = new BizChainRouter();
        router.rule(RequestType.CHAT_GPT).handler(ipRateLimiterEmitterChain)
                .handler(sealsWordEmitterChain).handler(chatMessageEmitterChain).end();
        return router;
    }
}