package am.jsl.personalfinances.service.category;

import am.jsl.personalfinances.dao.category.CategoryDao;
import am.jsl.personalfinances.domain.Category;
import am.jsl.personalfinances.dto.CategoryDTO;
import am.jsl.personalfinances.ex.CannotDeleteException;
import am.jsl.personalfinances.service.BaseServiceImpl;
import am.jsl.personalfinances.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*Implementacja usługi {@link CategoryService}.
*/
@Service("categoryService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

/**
*Plik szablonu, w którym przechowywana jest reprezentacja kategorii w formacie HTML.
*/
    private static final String CATEGORY_LOOKUP_HTML = "category-lookup.html";

/**
*Kategoria dao.
*/
    private CategoryDao categoryDao;

    @Override
    public List<CategoryDTO> getCategories(long userId) {
        List<CategoryDTO> categories = categoryDao.getCategories(userId);
        List<CategoryDTO> result = new ArrayList<>();
        Map<Long, CategoryDTO> parentMap = new HashMap<>();

        categories.forEach(category -> {
            long parentId = category.getParentId();
            CategoryDTO parent = parentMap.get(parentId);
            if (parent != null) {
                parent.addChild(category);
            } else {
                parentMap.put(category.getId(), category);
                result.add(category);
            }
        });

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(long id, long userId) throws CannotDeleteException {
        super.delete(id, userId);
        publish(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void create(Category category) throws Exception {
        if (TextUtils.isEmpty(category.getColor())) {
            category.setColor(Category.DEFAULT_COLOR);
        }
        super.create(category);
        publish(category.getUserId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Category category) throws Exception {
        if (TextUtils.isEmpty(category.getColor())) {
            category.setColor(Category.DEFAULT_COLOR);
        }
        super.update(category);
        publish(category.getUserId());
    }

/**
*Generuje reprezentację HTML kategorii dla podanego identyfikatora użytkownika.
*@param userId identyfikator użytkownika
*/
    private void publish(long userId) {
        if (!publishHtml) {
            log.info("Publish html is disabled");
            return;
        }

        List<CategoryDTO> categories = getCategories(userId);

        StringBuilder html = new StringBuilder();
        html.append("<option value='0'></option>");
        long id;


        for (CategoryDTO category : categories) {
            id = category.getId();

            html.append("<option value='").append(id).append("'>");
            html.append("<span>").append(category.getName());
            html.append("</span></option>");

            List<CategoryDTO> childs = category.getChilds();

            if (childs == null) {
                continue;
            }

            for (CategoryDTO child : childs) {
                id = child.getId();

                html.append("<option value='").append(id).append("'>").append("&nbsp;&nbsp;&nbsp;");
                html.append("<span>").append(child.getName());
                html.append("</span></option>");
            }
        }

        publish(userId, CATEGORY_LOOKUP_HTML, html.toString());
    }

    @Override
    public List<CategoryDTO> lookupParentCategories(long userId) {
        return categoryDao.lookupParentCategories(userId);
    }

    @Autowired
    public void setCategoryDao(@Qualifier("categoryDao") CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
        setBaseDao(categoryDao);
    }
}
