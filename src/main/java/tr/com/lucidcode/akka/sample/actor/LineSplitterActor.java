package tr.com.lucidcode.akka.sample.actor;

import org.apache.log4j.Logger;

import tr.com.lucidcode.akka.sample.message.LineProcessedMsg;
import tr.com.lucidcode.akka.sample.message.ProcessLineMsg;
import akka.actor.UntypedActor;

public class LineSplitterActor extends UntypedActor {

	static final Logger	logger	= Logger.getLogger(LineSplitterActor.class);

	@Override
	public void onReceive(Object msg) {
		
		logger.info(self().path().name() + " started counting...");

		// validate message type
		if (!(msg instanceof ProcessLineMsg)) {
			unhandled(msg);
			return;
		}

		// cast message
		ProcessLineMsg processLineMessage = (ProcessLineMsg) msg;

		// get line to be processed
		String lineToProcess = processLineMessage.getLineToBeProcessed();

		// count words
		Integer nrOfWords = lineToProcess.split(" ").length;

		// initialize response message
		LineProcessedMsg lineProcessedMsg = new LineProcessedMsg();
		lineProcessedMsg.setNumberOfWords(nrOfWords);
		
		logger.info(self().path().name() + " finished counting (" + nrOfWords + ")");

		// tell parent that job is finished
		sender().tell(lineProcessedMsg, getSelf());

	}

}
