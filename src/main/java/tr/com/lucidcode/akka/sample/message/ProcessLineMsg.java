/**
 * Created by Omer Gurarslan on 23/09/14.
 * Copyright (C) 2103-2014 Lucid Bilg. Hiz. Ltd. Şti. 
 * All rights reserved. <http://dev.lucidcode.com.tr>
 */

package tr.com.lucidcode.akka.sample.message;

public class ProcessLineMsg  {
	
	private String lineToBeProcessed;

	public String getLineToBeProcessed() {
		return lineToBeProcessed;
	}

	public void setLineToBeProcessed(String lineToBeProcessed) {
		this.lineToBeProcessed = lineToBeProcessed;
	}

}
