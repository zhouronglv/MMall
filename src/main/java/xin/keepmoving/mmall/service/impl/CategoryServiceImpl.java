package xin.keepmoving.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.keepmoving.mmall.common.ServerResponse;
import xin.keepmoving.mmall.dao.CategoryMapper;
import xin.keepmoving.mmall.pojo.Category;
import xin.keepmoving.mmall.service.ICategoryService;

import java.util.List;
import java.util.Set;


@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true); //这个分类有效
        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    public ServerResponse updateCategoryName(String categoryName, Integer categoryId) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("修改品类的参数错误");
        }
        Category updateCategory = new Category();
        updateCategory.setId(categoryId);
        updateCategory.setName(categoryName);
        int updateCount = categoryMapper.updateByPrimaryKeySelective(updateCategory);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    public ServerResponse<List<Category>> getCategoryChildInfoByCategoryId(Integer categoryId) {
        if (categoryId == null) {
            return ServerResponse.createByErrorMessage("查询品类的参数错误");
        }
        List<Category> categoryList = categoryMapper.selectCategoryClidByCategoryId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未查询到当前分类的子节点");
            return ServerResponse.createByErrorMessage("未查询到该品类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse<List<Integer>> getCateforyAndDescendantIdByCategoryId(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    //递归算法，算出子节点
    private void findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }        //递归算法一点要有退出条件
        List<Category> categoryList = categoryMapper.selectCategoryClidByCategoryId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
    }
}
