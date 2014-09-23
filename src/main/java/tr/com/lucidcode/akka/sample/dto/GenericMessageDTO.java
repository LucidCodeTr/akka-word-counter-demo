package tr.com.lucidcode.akka.sample.dto;

import tr.com.lucidcode.akka.sample.type.MessageType;

public class GenericMessageDTO {
	
	private MessageType messageType;
	
	public GenericMessageDTO(MessageType messageType) {
		super();
		this.messageType = messageType;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

}
