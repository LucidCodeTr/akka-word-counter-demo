package tr.com.lucidcode.akka.sample.dto;

import tr.com.lucidcode.akka.sample.type.MessageType;

public class StartProcessingMsg extends GenericMessageDTO {

	public StartProcessingMsg() {
		super(MessageType.START_PROCESSING);
	}

}
