package tr.com.lucidcode.akka.sample.actor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.apache.log4j.Logger;

import tr.com.lucidcode.akka.sample.dto.LineProcessedMsg;
import tr.com.lucidcode.akka.sample.dto.ProcessLineMsg;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class WordCounterActor extends UntypedActor {

	static final Logger			logger			= Logger.getLogger(WordCounterActor.class);

	private static final String	RESOURCES_DIR	= "src/main/resources/";

	private static Integer		totalLines		= 0;
	private static Integer		totalWords		= 0;
	private static Integer		linesProcessed	= 0;

	@Override
	public void preStart() throws IOException {

		//path to file to read
		File fileToRead = new File(RESOURCES_DIR + "hipster_ipsum.txt");

		// open up the hipster_ipsum.txt file located
		// under resources folder
		FileInputStream fileStream = new FileInputStream(fileToRead);

		// create buffered reader
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));

		//set total number of lines
		totalLines = getNrOfLines(fileToRead);

		// read through lines
		String line;
		Integer actorCounter = 0;
		while ((line = reader.readLine()) != null) {

			// create the line splitter actor
			final ActorRef splitter = getContext().actorOf(Props.create(LineSplitterActor.class), "lineSplitter" + actorCounter++);

			// initialize line splitting request
			ProcessLineMsg processLineMsg = new ProcessLineMsg();
			processLineMsg.setLineToBeProcessed(line);

			// tell it to perform the line splitting
			splitter.tell(processLineMsg, getSelf());

		}

		// close file stream
		reader.close();

	}

	@Override
	public void onReceive(Object msg) {

		// validate message type
		if (!(msg instanceof LineProcessedMsg)) {
			unhandled(msg);
			return;
		}

		// cast message
		LineProcessedMsg lineProcessedMsg = (LineProcessedMsg) msg;

		// get the number of words read from the incoming line
		// and add that number to totalWords.
		totalWords += lineProcessedMsg.getNumberOfWords();

		// stop execution if all lines have been processed.
		if (totalLines == linesProcessed) {
			logger.info("Total Words: " + totalWords);
			getContext().stop(getSelf());
		}
		
		// increase by one the counter of lines read so far.
		linesProcessed++;

	}
	
	private Integer getNrOfLines(File fileToRead) throws FileNotFoundException, IOException {
		LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(fileToRead));
		lineNumberReader.skip(Long.MAX_VALUE);
		Integer totalLines = lineNumberReader.getLineNumber();
		lineNumberReader.close();
		return totalLines;
	}
}
