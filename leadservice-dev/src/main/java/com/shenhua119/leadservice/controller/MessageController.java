package com.shenhua119.leadservice.controller;

import com.shenhua119.leadservice.entity.MessageEntity;
import com.shenhua119.leadservice.service.MessageService;
import com.shenhua119.leadservice.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2019-01-23 12:38
 */
@CrossOrigin // 决解跨域问题
@RestController
@RequestMapping("api/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 获取所有消息
     * @return
     */
    @GetMapping("all")
    public Msg getMessagelist(){
        // 获取所有消息
        List<MessageEntity> list = messageService.list(null);
        return Msg.success().add("list", list);
    }
}
