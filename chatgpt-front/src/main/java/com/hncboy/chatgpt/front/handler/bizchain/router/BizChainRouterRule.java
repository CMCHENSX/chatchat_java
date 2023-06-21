package com.hncboy.chatgpt.front.handler.bizchain.router;

import com.hncboy.chatgpt.front.handler.bizchain.ResponseEmitterChain;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.HandlerContext;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.RequestType;

import java.util.ArrayList;
import java.util.List;

/**
 * BizChainRouterRule
 */
public class BizChainRouterRule {

    private final BizChainRouter routerBuilder;

    private List<ResponseEmitterChain> handlers = new ArrayList<>();

    private RequestType requestKey;

    public BizChainRouterRule(BizChainRouter routerBuilder) {
        this.routerBuilder = routerBuilder;
    }

    public BizChainRouterRule requestKey(RequestType requestKey) {
        this.requestKey = requestKey;
        return this;
    }

    public BizChainRouterRule handler(ResponseEmitterChain handler) {
        this.handlers.add(handler);
        return this;
    }
    public BizChainRouterRule handler(List<ResponseEmitterChain> handlers){
        this.handlers.addAll(handlers);
        return this;
    }

    public List<ResponseEmitterChain> handlers() {
        return this.handlers;
    }

    public BizChainRouter end() {
        this.routerBuilder.getRules().put(requestKey, this);
        return this.routerBuilder;
    }

    protected boolean test(HandlerContext context) {
        return this.requestKey.equals(context.getRequestType());
    }

}
