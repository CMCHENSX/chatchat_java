package com.hncboy.chatgpt.front.domain.request;

import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author hncboy
 * @date 2023-3-23
 * 消息处理请求
 */
@Data
@Schema(title = "消息处理请求")
public class ChatCompletionRequest extends ChatCompletion {

//    private String model;
//
//    private int presencePenalty;
//
//    private boolean stream;
//
//    private int temperature;
//
//    private List<Message> messages;

    public String getPrompt() {
        if(getMessages() == null || getMessages().size() == 0){
            return "";
        }

        if(getMessages().size() == 1){
            return getMessages().get(0).getContent();
        }

        return getMessages().get(getMessages().size() - 1).getContent();
    }
//    @Size(min = 1, max = 2000, message = "问题字数范围[1, 2000]")
//    @Schema(title = "问题")
//    private String prompt;
//
//    @Schema(title = "配置")
//    private Options options;
//
//    @Schema(title = "系统消息")
//    private String systemMessage;
//
//    @Data
//    @Schema(title = "消息配置")
//    public static class Options {
//
//        @Schema(title = "对话 id")
//        private String conversationId;
//
//        /**
//         * 这里的父级消息指的是回答的父级消息 id
//         * 前端发送问题，需要上下文的话传回答的父级消息 id
//         */
//        @Schema(title = "父级消息 id")
//        private String parentMessageId;
//    }
}
