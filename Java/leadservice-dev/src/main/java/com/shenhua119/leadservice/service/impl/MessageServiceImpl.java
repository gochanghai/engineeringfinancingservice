package com.shenhua119.leadservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenhua119.leadservice.dao.MessageDao;
import com.shenhua119.leadservice.entity.MessageEntity;
import com.shenhua119.leadservice.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2019-01-19 22:12
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

    /**
     *
     * @param type 消息类型 0 系统公告 1 消息通知
     * @param title 消息标题
     * @param content 消息内容
     * @param userID  用户ID
     * @return
     */
    @Override
    public boolean productionMessage(Integer type, String title, String content, Long userID) {
        // 创建一个消息实例
        MessageEntity message = new MessageEntity();
        // 设置消息内容
        message.setType(type)
                .setTitle(title)
                .setContent(content)
                .setStatus(0)
                .setCreateDate(new Date())
                .setUserId(userID);
        // 保存数据并返回结果
        return save(message);
    }
}
