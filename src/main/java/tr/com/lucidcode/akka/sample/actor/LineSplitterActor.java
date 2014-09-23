package tr.com.lucidcode.akka.sample.actor;

import tr.com.lucidcode.akka.sample.dto.LineProcessedMsg;
import tr.com.lucidcode.akka.sample.dto.ProcessLineMsg;
import akka.actor.UntypedActor;

public class LineSplitterActor extends UntypedActor {

  @Override
  public void onReceive(Object msg) {
	  
		// validate message type
		if (!(msg instanceof ProcessLineMsg)) {
			unhandled(msg);
			return;
		}
		
		//cast message
		ProcessLineMsg processLineMessage = (ProcessLineMsg) msg;
		
		//get line to be processed
		String lineToProcess = processLineMessage.getLineToBeProcessed();
		
		//count words
		Integer nrOfWords = lineToProcess.split(" ").length;
		
		//initialize response message
		LineProcessedMsg lineProcessedMsg = new LineProcessedMsg();
		lineProcessedMsg.setNumberOfWords(nrOfWords);
		
		// tell parent that job is finished
		sender().tell(lineProcessedMsg, getSelf());
		      
  }

}
