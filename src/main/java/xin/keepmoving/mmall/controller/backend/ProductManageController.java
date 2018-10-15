package xin.keepmoving.mmall.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.pojo.Product;
import xin.keepmoving.mmall.service.IProductService;

import javax.servlet.http.HttpSession;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/27
 **/
@Controller
@RequestMapping(value = "/manager/product")
public class ProductManageController {
    @Autowired
    private IProductService productService;

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> productSave(HttpSession session, Product product) {
        // TODO 一个过滤器校验用户登陆

        return productService.saveOrUpdateProduct(product);
    }

    @RequestMapping(value = "/set_sale_status.do", method = RequestMethod.POST)
    @ResponseBody()
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        return productService.productService(productId, status);
    }
}
