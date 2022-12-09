package am.jsl.personalfinances.web.controller.event;

import am.jsl.personalfinances.dto.event.EventListDTO;
import am.jsl.personalfinances.search.EventSearchQuery;
import am.jsl.personalfinances.search.ListPaginatedResult;
import am.jsl.personalfinances.search.PageWrapper;
import am.jsl.personalfinances.service.event.EventService;
import am.jsl.personalfinances.util.Constants;
import am.jsl.personalfinances.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

import static am.jsl.personalfinances.web.util.WebUtils.USERS;

/**
 * Definiuje metody zdarzeń  dla roli administratora.
 */
@Controller
@RequestMapping(value = "/event")
@PreAuthorize("hasAuthority('ADMIN')")
@Lazy
public class EventController extends BaseController {
    /**
     * Ścieżki szablonu zdarzenia
     */
    public static final String FORWARD_EVENT_LIST = "system/event/event-list";
    public static final String FORWARD_EVENT_RESULT_LIST = "system/event/event-list-result :: eventResultsList";

    @Autowired
    private transient EventService eventService;

    /**
     * Rejestruje niestandardowy edytor daty.
     * @param binder {@link WebDataBinder}
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * Zadzwonił, gdy admin otwiera stronę listy zdarzeń.
     * @param model Model
     * @return strona zszablonu FORWARD_EVENT_LIST
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String eventList(Model model) {
        model.addAttribute(USERS, userService.list(0));
        return FORWARD_EVENT_LIST;
    }

    /**
     * Nazywany metodą AJAX do wyszukiwania i stronicowania zdarzeń.
     * @param model Model
     * @param page obecna strona
     * @param eventType typ zdarzenia
     * @param performedBy id uzytkownika wywołującego tą metodę
     * @param createdAtStart data początku
     * @param createdAtEnd data końca
     * @param message wiadomość
     * @return strona z szablonu FORWARD_EVENT_RESULT_LIST
     */
    @RequestMapping(value = {"/loadEvents"}, method = RequestMethod.GET)
    public String loadEvents(Model model, @RequestParam int page,
                             @RequestParam int eventType,
                             @RequestParam long performedBy,
                             @RequestParam @DateTimeFormat(pattern= Constants.DATE_FORMAT) Date createdAtStart,
                             @RequestParam @DateTimeFormat(pattern="dd/mm/yyyy") Date createdAtEnd,
                             @RequestParam String message) {

        EventSearchQuery searchQuery = new EventSearchQuery(page, Constants.PAGE_SIZE);
        searchQuery.setEventType(eventType);
        searchQuery.setPerformedBy(performedBy);
        searchQuery.setCreatedAtStart(createdAtStart);
        searchQuery.setCreatedAtEnd(createdAtEnd);
        searchQuery.setMessage(message);
        ListPaginatedResult<EventListDTO> result = eventService.search(searchQuery);

        PageWrapper<EventListDTO> pageWrapper = new PageWrapper<>(result, page, Constants.PAGE_SIZE);
        model.addAttribute(Constants.PAGE, pageWrapper);

        return FORWARD_EVENT_RESULT_LIST;
    }
}
