package com.hncboy.chatgpt.front.api.listener;


import com.hncboy.chatgpt.base.exception.ServiceException;
import com.hncboy.chatgpt.front.domain.vo.ChatReplyMessageVO;
import jakarta.servlet.ServletOutputStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.jetbrains.annotations.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.TimerTask;

import jakarta.servlet.http.HttpServletResponse;
/**
 * @author hncboy
 * @date 2023-3-24
 * SseEmitter 消息流监听
 */
@Slf4j
//@AllArgsConstructor
public class ResponseBodyEmitterStreamListener extends AbstractStreamListener {

    private ResponseBodyEmitter emitter;

    private ServletOutputStream servletOutputStream;

//    private HttpServletResponse response;

    public ResponseBodyEmitterStreamListener(){

    }

    public ResponseBodyEmitterStreamListener(ResponseBodyEmitter emitter) {

        this.emitter = emitter;

    }
    public ResponseBodyEmitterStreamListener(HttpServletResponse response, ResponseBodyEmitter emitter) {
        this.emitter = emitter;
        try {
            this.servletOutputStream = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Async
    public void onMessage(String message, String receivedMessage, ChatReplyMessageVO chatReplyMessageVO, int messageCount) {
        if (Objects.isNull(chatReplyMessageVO)) {
            return;
        }
//        AsyncManager.me().execute(new TimerTask() {
//            @Override
//            public void run() {
                try {
                    emitter.send(message);
                } catch (Exception e) {
                    log.warn("消息发送异常，第{}条消息，消息内容：{}", messageCount, receivedMessage, e);
                    throw new ServiceException("消息发送异常");
                }
//            }
//        });

    }

    @Override
    public void onComplete(String receivedMessage) {
        emitter.complete();
        this.close();
    }

    @Override
    public void onError(String error, Throwable t, @Nullable Response response) {
        try {
            emitter.send(ChatReplyMessageVO.reply("", error.replaceAll("\\s*", "")));
        } catch (Exception e) {
            log.warn("消息发送异常，处理异常发送消息时出错", e);
        } finally {
            this.close();
            try {
                emitter.complete();
            } catch (Exception ignored) {

            }
        }
    }

    private void close() {
        try {
            if (servletOutputStream != null) {
                servletOutputStream.close();
            }
        } catch (Exception e) {
        }
    }

    private void emitter(String data) {
        try {
            servletOutputStream.write("event: event\n".getBytes());
            servletOutputStream.write(("data: "+ data +"\n").getBytes(StandardCharsets.UTF_8));
            servletOutputStream.write("\n".getBytes());
            servletOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
