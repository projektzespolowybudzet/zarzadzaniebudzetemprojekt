package am.jsl.personalfinances.domain.event;

import am.jsl.personalfinances.domain.BaseEntity;

import java.io.Serializable;

/**
*Obiekt domeny zdarzeń.
*/
public class Event extends BaseEntity implements Serializable {

	/**
	 * @see EventType
	 */
	private byte eventType;
	private String message;
	private long performedBy;

/**
*Konstruuje nowe zdarzenie z podanymi polami.
*@param eventType typ zdarzenia
*@param wiadomość komunikat o zdarzeniu
*Wykonano @param Przez identyfikator użytkownika, który utworzył tę wiadomość
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
