package com.hncboy.chatgpt.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hncboy.chatgpt.admin.domain.query.GiftCardPageQuery;
import com.hncboy.chatgpt.admin.service.GiftCardService;
import com.hncboy.chatgpt.base.domain.entity.GiftCardDO;
import com.hncboy.chatgpt.base.mapper.GiftCardMapper;
import com.hncboy.chatgpt.base.util.PageUtil;
import org.springframework.stereotype.Service;

/**
 * @author hncboy
 * @date 2023-3-27
 * 聊天室业务实现类
 */
@Service
public class GiftCardServiceImpl extends ServiceImpl<GiftCardMapper, GiftCardDO> implements GiftCardService {

    @Override
    public IPage<GiftCardDO> pageGiftCard(GiftCardPageQuery cardPageQuery) {
        Page<GiftCardDO> cardDOPage = page(new Page<>(cardPageQuery.getPageNum(), cardPageQuery.getPageSize()), new LambdaQueryWrapper<GiftCardDO>()
                .orderByDesc(GiftCardDO::getCreateTime));

        return PageUtil.toPage(cardDOPage, cardDOPage.getRecords());
    }
}
