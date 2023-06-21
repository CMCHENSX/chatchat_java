package com.hncboy.chatgpt.front.handler.bizchain.router;

import com.hncboy.chatgpt.base.handler.response.R;
import com.hncboy.chatgpt.front.handler.bizchain.ResponseEmitterChain;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.HandlerContext;
import com.hncboy.chatgpt.front.handler.bizchain.router.model.RequestType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * BizChainRouter
 */
public class BizChainRouter {

    private final Map<RequestType, BizChainRouterRule> rules = new HashMap<>();

    public R route(HandlerContext context) {
        BizChainRouterRule rule = rule(context.getRequestType());
        Iterator<ResponseEmitterChain> handlers = rule.handlers().iterator();
        R result = R.success();
        while (handlers.hasNext()) {
            try {
                result = handlers.next().doChain(context);
            } catch (RuntimeException e) {

            }
            if (!result.isSuccess()) {
                return result;
            }
            if (context.isInterrupt()) {
                break;
            }
        }

        return result;
    }

    Map<RequestType, BizChainRouterRule> getRules() {
        return this.rules;
    }

    public BizChainRouterRule rule(RequestType requestType) {
        if (rules.containsKey(requestType)) {
            return rules.get(requestType);
        }
        rules.put(requestType, new BizChainRouterRule(this));
        return rules.get(requestType);
    }
}
