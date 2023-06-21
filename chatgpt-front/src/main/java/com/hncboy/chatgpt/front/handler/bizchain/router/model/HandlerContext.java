package com.hncboy.chatgpt.front.handler.bizchain.router.model;

import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.UUID;

/**
 *  HandlerContext model
 *
 */
public class HandlerContext<T> {

	private RequestType requestType;
	private T request;

	private ResponseBodyEmitter emitter;
	private boolean isInterrupt = false;

	private HttpServletResponse response;

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}

	public ResponseBodyEmitter getEmitter() {
		return emitter;
	}

	public void setEmitter(ResponseBodyEmitter emitter) {
		this.emitter = emitter;
	}

	public boolean isInterrupt() {
		return isInterrupt;
	}

	public void setInterrupt(boolean interrupt) {
		isInterrupt = interrupt;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public HandlerContext() {
	}

	public HandlerContext(T request, ResponseBodyEmitter emitter, HttpServletResponse response, RequestType requestType) {
		this.request = request;
		this.isInterrupt = false;
		this.emitter = emitter;
		this.response = response;
		this.requestType = requestType;
	}
}
