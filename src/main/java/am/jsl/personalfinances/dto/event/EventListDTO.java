package am.jsl.personalfinances.dto.event;

import java.io.Serializable;
import java.util.Date;

/**
*EventListDTO służy do przekształcania danych zdarzeń na strone
*/
public class EventListDTO implements Serializable {

	private long id;
	private String eventType;
	private String message;
	private String performedBy;
	private String performedOn;
	private Date createdAt = null;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPerformedBy() {
		return performedBy;
	}
	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public String getPerformedOn() {
		return performedOn;
	}
	public void setPerformedOn(String performedOn) {
		this.performedOn = performedOn;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
