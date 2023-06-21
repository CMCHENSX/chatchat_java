package com.hncboy.chatgpt.base.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 前端用户 基础表实体类
 *
 * @author CoDeleven
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "gift_card")
@Data
public class GiftCardDO {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 用户
     */
    private String userId;

    /**
     * 模型
     */
    private String model;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * token数量
     */
    private Integer tokens;

    private Integer chatCount;

    private Integer advancedChatCount;

    private Integer drawCount;

    private Integer days;

    private Integer state;
}