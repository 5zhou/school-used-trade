package com.zouwu.trade.controller;

import com.github.pagehelper.PageInfo;
import com.zouwu.trade.dto.ApiResponse;
import com.zouwu.trade.model.Product;
import com.zouwu.trade.service.ProductOrderService;
import com.zouwu.trade.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @Autowired
    private ProductOrderService productOrderService;


    @PostMapping("buy")
    public ApiResponse<Void> buy(@RequestParam("productId") int productId) {
        return productOrderService.buy(productId);
    }

    @PostMapping("insert")
    public ApiResponse<Void> insert(@RequestBody Product product) {
        return productService.insert(product);
    }

    @PostMapping("update")
    public ApiResponse<Void> update(@RequestBody Product product) {
        return productService.update(product);
    }

    @GetMapping("pagedQueryOnSaleProducts")
    public ApiResponse<PageInfo<Map<String, Object>>> pagedQueryOnSaleProducts(@RequestParam(value = "name", required = false) String name,
                                                                               @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
        return productService.pagedQueryOnSaleProducts(name, pageIndex, pageSize);
    }

    @GetMapping("selectMyProducts")
    public ApiResponse<List<Product>> selectMyProducts() {
        return productService.selectMyProducts();
    }

    @GetMapping("selectPurchasedProducts")
    public ApiResponse<List<Map<String, Object>>> selectPurchasedProducts() {
        return productService.selectPurchasedProducts();
    }

    @PostMapping("addComment")
    public ApiResponse<Void> addComment(@RequestParam("productId") int productId, @RequestParam("comment") String comment) {
        return productService.addComment(productId, comment);
    }

    @PostMapping("delete")
    public ApiResponse<Void> delete(@RequestParam("productId") int productId) {
        return productService.delete(productId);
    }

}
