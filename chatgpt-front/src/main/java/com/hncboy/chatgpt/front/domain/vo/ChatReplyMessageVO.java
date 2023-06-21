package com.hncboy.chatgpt.front.domain.vo;

import cn.hutool.json.JSONObject;
import com.hncboy.chatgpt.base.util.ObjectMapperUtil;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author hncboy
 * @date 2023-3-23
 * 聊天回复的消息
 */
@Data
@Schema(title = "聊天回复的消息")
public class ChatReplyMessageVO extends ChatCompletionResponse {

    private static final String ERROR_MESSAGE =
            "{\"id\":\"chatcmpl-\",\"object\":\"chat.completion.chunk\",\"created\":1686146578,\"model\":\"\",\"choices\":[{\"delta\":{\"content\":\"\"},\"index\":0,\"finish_reason\":null}]}";
    private static final ChatCompletionResponse errorResponse = ObjectMapperUtil.fromJson(ERROR_MESSAGE, ChatCompletionResponse.class);
//    /**
//     * 对于前端有什么用？
//     */
//    @Schema(title = "角色")
//    private String role;
//
//    @Schema(title = "当前消息 id")
//    private String id;
//
//    @Schema(title = "父级消息 id")
//    private String parentMessageId;
//
//    @Schema(title = "对话 id")
//    private String conversationId;
//
//    @Schema(title = "回复的消息")
//    private String text;



    public static String reply(String model, String message) {
//        if (StringUtils.isEmpty(model)) {
//            model = "gpt3";
//        }
//        if (StringUtils.isEmpty(message)) {
//            message = "【接收消息处理异常，响应中断】";
//        }
        errorResponse.setModel(model);
        errorResponse.getChoices().get(0).getDelta().setContent(message);
        return ObjectMapperUtil.toJson(errorResponse);
    }
}
