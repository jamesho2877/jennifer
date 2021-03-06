package com.jennifer.service;

import com.jennifer.entity.CategoryInfo;

import java.util.List;

/**
 * Provided services for CategoryInfo
 */

public interface CategoryInfoService {

    List<CategoryInfo> findAllCategories();
    CategoryInfo findCategory(int id);
    CategoryInfo updateCategory(CategoryInfo categoryInfo);
    void deleteCategory(CategoryInfo categoryInfo);
    CategoryInfo addCategory(CategoryInfo categoryInfo);

}
