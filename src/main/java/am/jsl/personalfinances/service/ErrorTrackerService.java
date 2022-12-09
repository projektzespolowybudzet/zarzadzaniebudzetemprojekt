package am.jsl.personalfinances.service;

/**
 *Interfejs usługi, który definiuje metodę wysyłania e-mailem szczegółowych informacji o wyjątkach.
 */
public interface ErrorTrackerService {

    /**
     *Wysyła e-mailem szczegóły wyjątku.
     *@param e Wyjątek
     */
    void sendError(final Exception e);
}
