package xin.keepmoving.mmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.dao.ProductMapper;
import xin.keepmoving.mmall.pojo.Product;
import xin.keepmoving.mmall.service.IProductService;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/27
 **/
@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public ServerResponse<String>  saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (isNotBlank(product.getSubImages())) {
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0) {
                    product.setMainImage(subImageArray[0]);
                }
            }

            if (product.getId() != null) {
                int rowCount = productMapper.updateByPrimaryKey(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("更新产品成功");

                } else {
                    return ServerResponse.createByErrorMessage("更新产品失败");
                }
            } else {
                int rowCount = productMapper.insert(product);
                if (rowCount > 0) {
                    return ServerResponse.createBySuccess("新增产品成功");

                } else {
                    return ServerResponse.createByErrorMessage("新增产品失败");
                }
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }

    @Override
    public ServerResponse<String> productService(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createErrorWithIllegalArgument();
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);

        int rowCount = productMapper.updateByPrimaryKey(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("修改产品状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品状态失败");
    }
}
