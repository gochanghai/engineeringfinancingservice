package com.shenhua119.leadservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenhua119.leadservice.entity.MessageEntity;

/**
 * @author liuchanghai
 * @create 2019-01-19 22:12
 */
public interface MessageService extends IService<MessageEntity> {

    /***
     *
     * @param type 消息类型 0 系统公告 1 消息通知
     * @param title 消息标题
     * @param content 消息内容
     * @return
     */
    boolean productionMessage (Integer type ,String title, String content, Long userID);
}
