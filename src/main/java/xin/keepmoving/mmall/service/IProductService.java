package xin.keepmoving.mmall.service;

import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.pojo.Product;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/27
 **/
public interface IProductService {
    ServerResponse<String> saveOrUpdateProduct(Product product);

    ServerResponse<String> productService(Integer productId, Integer status);
}
