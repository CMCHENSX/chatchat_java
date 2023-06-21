package com.hncboy.chatgpt.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hncboy.chatgpt.admin.domain.query.GiftCardPageQuery;
import com.hncboy.chatgpt.base.domain.entity.GiftCardDO;

/**
 * @author hncboy
 * @date 2023-3-27
 * 聊天室相关业务接口
 */
public interface GiftCardService extends IService<GiftCardDO> {


    IPage<GiftCardDO> pageGiftCard(GiftCardPageQuery giftCardPageQuery);
}
