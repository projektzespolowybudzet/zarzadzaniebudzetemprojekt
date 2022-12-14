package am.jsl.dolarek.domain.event;

import am.jsl.dolarek.domain.BaseEntity;

/**
*Obiekt domeny zdarzeń.
*/
public class Event extends BaseEntity {

	/**
	 * @see EventType
	 */
	private byte eventType;
	private String message;
	private long performedBy;

/**
*Konstruuje nowe zdarzenie z podanymi polami.
*@param eventType typ zdarzenia
*@param message komunikat o zdarzeniu
*@param performedBy identyfikator użytkownika, który utworzył tę wiadomość
*/
	public Event(EventType eventType, String message, long performedBy) {
		super();
		this.eventType = eventType.getValue();
		this.message = message;
		this.performedBy = performedBy;
	}

    public Event() {
        super();
    }

	public byte getEventType() {
		return eventType;
	}
	public void setEventType(byte eventType) {
		this.eventType = eventType;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public long getPerformedBy() {
		return performedBy;
	}
	public void setPerformedBy(long performedBy) {
		this.performedBy = performedBy;
	}
}
