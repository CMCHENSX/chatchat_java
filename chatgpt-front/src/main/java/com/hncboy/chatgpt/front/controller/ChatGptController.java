package com.hncboy.chatgpt.front.controller;

import com.hncboy.chatgpt.front.service.ChatMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.nio.charset.StandardCharsets;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;

/**
 * @author hncboy
 * @date 2023-3-22
 * 聊天相关接口
 */
@AllArgsConstructor
@Tag(name = "聊天相关接口")
@RestController("ChatGptController")
@RequestMapping("/api/chatgpt")
public class ChatGptController {

    private final ChatMessageService chatMessageService;

    @Operation(summary = "发送消息")
    @PostMapping("/v1/chat/completions")
    public ResponseBodyEmitter sendMessage(@RequestBody ChatCompletion ChatCompletion, HttpServletResponse response) {
        // TODO 后续调整
//        chatProcessRequest.setSystemMessage("You are ChatGPT, a large language model trained by OpenAI. Answer as concisely as possible.\\nKnowledge cutoff: 2021-09-01\\nCurrent date: ".concat(DateUtil.today()));
//        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        SseEmitter emitter = new SseEmitter(3 * 60 * 1000L);
        response.setContentType("text/event-stream");
//        ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
        return chatMessageService.sendMessage(ChatCompletion, emitter, response);
    }

}
