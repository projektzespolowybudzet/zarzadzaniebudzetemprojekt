package am.jsl.dolarek.search;

import java.util.Date;

import am.jsl.dolarek.dto.event.EventListDTO;

/**
*Niestandardowe {@link Query} do wyszukiwania elementów {@link EventListDTO}.
*/
public class EventSearchQuery extends Query<EventListDTO> {
    private int eventType;
    private long performedBy;
    private Date createdAtStart = null;
    private Date createdAtEnd = null;
    private String message;

/**
*Tworzy nowe zapytanie wyszukiwania zdarzeń.
*
*@param page strona
*@param pageSize rozmiar strony
*/
    public EventSearchQuery(int page, int pageSize) {
        super(page, pageSize);
    }


    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public long getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(long performedBy) {
        this.performedBy = performedBy;
    }

    public Date getCreatedAtStart() {
        return createdAtStart;
    }

    public void setCreatedAtStart(Date createdAtStart) {
        this.createdAtStart = createdAtStart;
    }

    public Date getCreatedAtEnd() {
        return createdAtEnd;
    }

    public void setCreatedAtEnd(Date createdAtEnd) {
        this.createdAtEnd = createdAtEnd;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
