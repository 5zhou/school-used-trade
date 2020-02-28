package com.zouwu.trade.service;

import com.zouwu.trade.dao.MessageMapper;
import com.zouwu.trade.dao.ProductMapper;
import com.zouwu.trade.dao.ProductOrderMapper;
import com.zouwu.trade.dao.UserMapper;
import com.zouwu.trade.dto.ApiResponse;
import com.zouwu.trade.model.Message;
import com.zouwu.trade.model.Product;
import com.zouwu.trade.model.ProductOrder;
import com.zouwu.trade.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class ProductOrderService {

    @Resource
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public ApiResponse<String> buy(int productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null) {
            return ApiResponse.defaultFailResponse("商品不存在");
        }
        if (product.getStatus() != 0) {
            return ApiResponse.defaultFailResponse("商品已下架或者已卖完");
        }
        int userId = (int) request.getSession().getAttribute("id");
        if (product.getUserId() == userId) {
            return ApiResponse.defaultFailResponse("不能购买自己的商品");
        }
        ProductOrder order = new ProductOrder();
        order.setProductId(productId);
        order.setUserId(userId);
        productOrderMapper.insert(order);
        productMapper.updateStatus(productId, 1);
        User user = userMapper.selectByPrimaryKey(userId);
        Message message = new Message();
        message.setTitle(String.format("商品(%s)售出通知", product.getName()));
        message.setIsRead(0);
        message.setUserId(product.getUserId());
        message.setContent(String.format("您的商品(%s), 已被同学(%s/%s)购买，购买者的联系方式如下，手机(%s)，邮箱(%s)，请尽快联系对方并线下完成交易。", product.getName(), user.getSchoolNo(), user.getName(), user.getPhone(), user.getMail()));
        messageMapper.insert(message);
        return ApiResponse.defaultSuccessResponse();
    }
}
