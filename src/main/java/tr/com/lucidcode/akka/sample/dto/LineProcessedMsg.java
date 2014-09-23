package tr.com.lucidcode.akka.sample.dto;

import tr.com.lucidcode.akka.sample.type.MessageType;

public class LineProcessedMsg extends GenericMessageDTO {
	
	private Integer numberOfWords;

	public LineProcessedMsg() {
		super(MessageType.LINE_PROCESSED);
	}

	public Integer getNumberOfWords() {
		return numberOfWords;
	}

	public void setNumberOfWords(Integer numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

}
