package am.jsl.personalfinances.domain.event;

import java.util.Arrays;

/**
*Wyliczenie zawierające możliwe typy zdarzeń.
*/
public enum EventType {
	LOGIN((byte)1, "Logowanie"),
	CREATE_ROLE((byte)2, "Nadanie dostpępu"),
	UPDATE_ROLE((byte)3, "Edycja dostępu"),
	DELETE_ROLE((byte)4, "Usunięcie dostępu"),
	CREATE_USER((byte)5, "Tworzenie użytkownika"),
	UPDATE_USER((byte)6, "Edycja użytkownika"),
	DELETE_USER((byte)7, "Usunięcie użytkownika"),
	CHANGE_PASSWORD((byte)8, "Zmiana hasła"),
	REGISTER_USER((byte)9, "Rejestracja użytkownika");

	private byte value;
	private String operation;
	
	EventType(byte value, String operation) {
		this.value = value;
		this.operation = operation;
	}
	
	public static EventType getByValue(byte value) {
		EventType[] values = values();

		return Arrays.stream(values).filter(eventType -> eventType.getValue() == value).findFirst().orElse(null);
	}
	
	public byte getValue() {
		return value;
	}
	
	public void setValue(byte value) {
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
