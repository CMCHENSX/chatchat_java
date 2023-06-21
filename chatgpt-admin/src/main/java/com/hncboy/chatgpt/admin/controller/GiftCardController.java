package com.hncboy.chatgpt.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hncboy.chatgpt.admin.domain.query.GiftCardPageQuery;
import com.hncboy.chatgpt.admin.service.GiftCardService;
import com.hncboy.chatgpt.base.annotation.ApiAdminRestController;
import com.hncboy.chatgpt.base.domain.entity.GiftCardDO;
import com.hncboy.chatgpt.base.handler.response.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hncboy
 * @date 2023-3-27
 * 聊天室相关接口
 */
@AllArgsConstructor
@Tag(name = "管理端-聊天室相关接口")
@ApiAdminRestController("/giftcard")
public class GiftCardController {

    private final GiftCardService giftCardService;

    @Operation(summary = "聊天室分页列表")
    @PostMapping("/page")
    public R<IPage<GiftCardDO>> page(@Validated @RequestBody GiftCardPageQuery giftCardPageQuery) {
        return R.data(giftCardService.pageGiftCard(giftCardPageQuery));
    }
}
