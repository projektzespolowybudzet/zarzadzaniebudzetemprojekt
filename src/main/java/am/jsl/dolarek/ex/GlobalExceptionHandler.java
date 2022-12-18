package am.jsl.dolarek.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import am.jsl.dolarek.log.AppLogger;
import am.jsl.dolarek.service.ErrorTrackerService;

/**
 * The GlobalExceptionHandler handles uncaught exceptions and
 * emails exception details via {@link ErrorTrackerService}.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    protected static final AppLogger log = new AppLogger(GlobalExceptionHandler.class);

    /**
     * The ErrorTrackerService.
     */
    @Autowired
    private ErrorTrackerService errorTrackerService;

    @ExceptionHandler(Exception.class)
    public String handle(final Exception e) {
        log.error("Handled exception: " + e.getMessage(), e);
        errorTrackerService.sendError(e);
        return "/error";
    }
}
