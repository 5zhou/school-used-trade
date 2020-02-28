package com.zouwu.trade.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zouwu.trade.dao.MessageMapper;
import com.zouwu.trade.dto.ApiResponse;
import com.zouwu.trade.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MessageService {


    @Resource
    private MessageMapper messageMapper;

    @Autowired
    private HttpServletRequest request;

    public ApiResponse<Message> select(int id) {
        Message message = messageMapper.selectByPrimaryKey(id);
        int userId = (int) request.getSession().getAttribute("id");
        if (message != null && message.getUserId() != userId) {
            return new ApiResponse<>(500, "不能阅读别人的消息", null);
        }
        if (message == null) {
            return new ApiResponse<>(0, "success", message);
        }
        message.setIsRead(1);
        messageMapper.updateByPrimaryKey(message);
        return new ApiResponse<>(0, "success", message);
    }

    public ApiResponse<PageInfo<Message>> pagedQueryMyMessage(int pageIndex, int pageSize) {
        int userId = (int) request.getSession().getAttribute("id");
        PageHelper.startPage(pageIndex, pageSize);
        List<Message> messageList = messageMapper.selectByUserId(userId);
        PageInfo<Message> pageInfo = new PageInfo<>(messageList);
        return new ApiResponse<>(0, "success", pageInfo);
    }


}
