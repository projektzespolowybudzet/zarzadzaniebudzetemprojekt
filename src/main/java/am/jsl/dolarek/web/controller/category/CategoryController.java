package am.jsl.dolarek.web.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import am.jsl.dolarek.domain.Category;
import am.jsl.dolarek.domain.user.User;
import am.jsl.dolarek.dto.CategoryDTO;
import am.jsl.dolarek.ex.CannotDeleteException;
import am.jsl.dolarek.service.category.CategoryService;
import am.jsl.dolarek.web.controller.BaseController;
import am.jsl.dolarek.web.util.I18n;
import am.jsl.dolarek.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

import static am.jsl.dolarek.web.util.WebUtils.CATEGORIES;
import static am.jsl.dolarek.web.util.WebUtils.CATEGORY;

import java.util.List;

/**
 * CategoryController określa metody funkcjonalności stron kategorii
 * takie jak wyszukiwanie, widok, dodaj, edytuj kategorie.
 */
@Controller
@RequestMapping(value = "/category")
@Lazy
public class CategoryController extends BaseController {
    /**
     * Ścieżki szablonów kategorii
     */
    public static final String REDIRECT_CATEGORY_LIST = "redirect:list";
    public static final String FORWARD_CATEGORY_LIST = "category/category-list";
    public static final String FORWARD_CATEGORY_MANAGE = "category/category-manage :: categoryFormDiv";
    public static final String FORWARD_CATEGORY_LOOKUP_LIST = "category/category-lookup-result :: categoryLookupList";

    /**
     * {@link CategoryService}
     */
    @Autowired
    private transient CategoryService categoryService;

    /**
     * Wywołana po załadowaniu strony listy kategorii.
     *
     * @param model  Model
     * @return szablon FORWARD_CATEGORY_LIST
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String list(Model model) {
        User user = getUser();
        List<CategoryDTO> categories = categoryService.getCategories(user.getId());
        model.addAttribute(CATEGORIES, categories);
        return FORWARD_CATEGORY_LIST;
    }

    /**
     * Wywołany metodami AJAX do pobierania opcji wybierz katgorie.
     *
     * @param model Model
     * @return szablon FORWARD_CATEGORY_LOOKUP_LIST
     */
    @RequestMapping(value = {"/lookup"}, method = RequestMethod.GET)
    public String lookup(Model model) {
        User user = getUser();
        model.addAttribute(CATEGORIES, categoryService.getCategories(user.getId()));
        return FORWARD_CATEGORY_LOOKUP_LIST;
    }

    /**
     * Wywołane, gdy użytkownik kliknie dodaj ze strony listy kategorii.
     *
     * @param model Model
     * @return szablon FORWARD_CATEGORY_MANAGE
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        if (!model.containsAttribute(CATEGORY)) {
            User user = getUser();
            List<CategoryDTO> categories = categoryService.lookupParentCategories(user.getId());
            model.addAttribute(CATEGORY, new Category());
            model.addAttribute(CATEGORIES, categories);
        }
        return FORWARD_CATEGORY_MANAGE;
    }

    /**
     * Wywołane, gdy użytkownik kliknie na edytuj ze strony listy kategorii.
     *
     * @param id    category id
     * @param model Model
     * @return szablon FORWARD_CATEGORY_MANAGE
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPage(@RequestParam(value = "id", required = true)
                                   long id, Model model) {
        if (!model.containsAttribute(CATEGORY)) {
            User user = getUser();
            List<CategoryDTO> categories = categoryService.lookupParentCategories(user.getId());
            Category category = categoryService.get(id, user.getId());
            model.addAttribute(CATEGORY, category);
            model.addAttribute(CATEGORIES, categories);
        }
        return FORWARD_CATEGORY_MANAGE;
    }

    /**
     * Wywołane, gdy użytkownik kliknie przycisk Zapisz ze strony zarzadzania kategoriami.
     *
     * @param request       HttpServletRequest
     * @param redirectAttrs RedirectAttributes
     * @param category      Category
     * @return szablon REDIRECT_CATEGORY_LIST
     * @throws Exception jeżeli wyjatek wystąpi
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, RedirectAttributes redirectAttrs,
                       @ModelAttribute Category category) throws Exception {
        User user = getUser();
        category.setUserId(user.getId());

        if (category.getParentId() == category.getId()) {
            category.setParentId(0);
        }
        String message = "";

        if (category.getId() == 0) { // create
            categoryService.create(category);
            message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_ADD,
                    new Object[]{WebUtils.CATEGORY, category.getName()});
        } else { // update
            message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_UPDATE,
                    new Object[]{WebUtils.CATEGORY, category.getName()});

            categoryService.update(category);
        }
        redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);

        return REDIRECT_CATEGORY_LIST;
    }

    /**
     * Wywołąny, gdy użytkownik kliknie Usuń na stronie kategorii .
     *
     * @param request       HttpServletRequest
     * @param id            category id
     * @param redirectAttrs RedirectAttributes
     * @return szablon REDIRECT_CATEGORY_LIST
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, @RequestParam(value = "id", required = true) long id,
                         RedirectAttributes redirectAttrs) {
        try {
            if (id != 0) {
                User user = getUser();
                Category category = categoryService.get(id, user.getId());
                categoryService.delete(id, user.getId());

                String message = i18n.msg(request, I18n.KEY_MESSAGE_SUCCESS_DELETE,
                        new Object[]{WebUtils.CATEGORY, category.getName()});
                redirectAttrs.addFlashAttribute(I18n.MESSAGE, message);
            }
        } catch (CannotDeleteException e) {
            String message = i18n.msg(request, I18n.KEY_ERROR_CATEGORY_DELETE);
            redirectAttrs.addFlashAttribute(I18n.ERROR, message);
        }

        return REDIRECT_CATEGORY_LIST;
    }
}