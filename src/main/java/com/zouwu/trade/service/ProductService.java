package com.zouwu.trade.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zouwu.trade.dao.ProductMapper;
import com.zouwu.trade.dao.ProductOrderMapper;
import com.zouwu.trade.dto.ApiResponse;
import com.zouwu.trade.model.Product;
import com.zouwu.trade.model.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {


    @Resource
    private ProductMapper productMapper;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private ProductOrderMapper orderMapper;


    public ApiResponse<Void> insert(Product product) {
        product.setStatus(0);
        product.setUserId((Integer) request.getSession().getAttribute("id"));
        productMapper.insert(product);
        return ApiResponse.success();
    }

    public ApiResponse<Void> update(Product product) {
        if (product.getStatus() == 1) {
            return ApiResponse.fail("已卖出的商品无法更新");
        }
        productMapper.updateByPrimaryKey(product);
        return ApiResponse.success();
    }


    public ApiResponse<PageInfo<Map<String, Object>>> pagedQueryOnSaleProducts(String name, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        int userId = (int) request.getSession().getAttribute("id");
        List<Map<String, Object>> productList = productMapper.pagedQueryOnSaleProducts(userId, name);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(productList);
        return new ApiResponse<>(0, "success", pageInfo);
    }

    public ApiResponse<List<Product>> selectMyProducts() {
        int userId = (int) request.getSession().getAttribute("id");
        List<Product> list = productMapper.selectProductsByUserId(userId);
        return new ApiResponse<>(0, "success", list);
    }

    public ApiResponse<List<Map<String, Object>>> selectPurchasedProducts() {
        int userId = (int) request.getSession().getAttribute("id");
        List<Map<String, Object>> list = productMapper.selectProductsByPurchaseUserId(userId);
        return new ApiResponse<>(0, "success", list);
    }

    public ApiResponse<Void> addComment(int productId, String comment) {
        int userId = (int) request.getSession().getAttribute("id");
        ProductOrder order = orderMapper.selectByUserIdAndProductId(userId, productId);
        if (order == null) {
            return ApiResponse.fail("你没有购买此商品，无法评价");
        }
        productMapper.addComment(productId, comment);
        return ApiResponse.success();
    }

    public ApiResponse<Void> delete(int productId) {
        int userId = (int) request.getSession().getAttribute("id");
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product != null && product.getUserId() != userId) {
            return ApiResponse.fail("只能删除自己的商品");
        }
        if (product != null && product.getStatus() == 1) {
            return ApiResponse.fail("已卖出的商品不能删除");
        }
        productMapper.deleteByPrimaryKey(productId);
        return ApiResponse.success();
    }

}
