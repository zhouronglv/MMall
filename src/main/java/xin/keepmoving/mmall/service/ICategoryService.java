package xin.keepmoving.mmall.service;

import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.pojo.Category;

import java.util.List;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/27
 **/
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(String categoryName, Integer categoryId);

    ServerResponse<List<Category>> getCategoryChildInfoByCategoryId(Integer categoryId);

    ServerResponse<List<Integer>> getCateforyAndDescendantIdByCategoryId(Integer categoryId);
}
