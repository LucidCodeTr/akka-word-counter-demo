package tr.com.lucidcode.akka.sample.actor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import tr.com.lucidcode.akka.sample.dto.GenericMessageDTO;
import tr.com.lucidcode.akka.sample.dto.LineProcessedMsg;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class WordCounterActor extends UntypedActor {

	static final Logger logger = Logger.getLogger(WordCounterActor.class);

	private static Boolean running = false;
	private static Integer totalLines = 0;
	private static Integer totalWords = 0;
	private static Integer linesProcessed = 0;

	@Override
	public void preStart() {

		// create the line splitter actor
		final ActorRef splitter = getContext().actorOf(
				Props.create(LineSplitterActor.class), "lineSplitter");

		// tell it to perform the greeting
		splitter.tell(LineSplitterActor.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object msg) {

		// validate message type
		if (!(msg instanceof GenericMessageDTO)) {
			return;
		}

		switch (((GenericMessageDTO) msg).getMessageType()) {
		case START_PROCESSING: {

			if (running) {
				logger.warn("Warning: duplicate start message received");
				return;
			}

			// turn on switch so that this part will execute
			// only once
			running = true;

			//open up the hipster_ipsum.txt file located
			//under resources folder
			FileInputStream fileStream;
			try {
				fileStream = new FileInputStream("hipster_ipsum.txt");
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
				return;
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
			try {
				while (reader.readLine() != null) {
				    //process each line in some way
				   
				  }
			} catch (IOException e) {
				logger.error(e.getMessage());
				return;
			} 

			break;
		}
		case LINE_PROCESSED: {

			// get the number of words read from the incoming line
			// and add that number to totalWords.
			totalWords += ((LineProcessedMsg) msg).getNumberOfWords();

			// increase by one the counter of lines read so far.
			linesProcessed += 1;

			// stop execution if all lines have been processed.
			if (totalLines == linesProcessed) {
				getContext().stop(getSelf());
			}
			break;
		}
		default: {
			unhandled(msg);
			break;
		}
		}
	}
}
