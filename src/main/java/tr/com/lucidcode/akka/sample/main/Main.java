/**
 * Created by Omer Gurarslan on 23/09/14.
 * Copyright (C) 2103-2014 Lucid Bilg. Hiz. Ltd. Şti. 
 * All rights reserved. <http://dev.lucidcode.com.tr>
 */

package tr.com.lucidcode.akka.sample.main;

import tr.com.lucidcode.akka.sample.actor.WordCounterActor;

public class Main {

  public static void main(String[] args) {
    akka.Main.main(new String[] { WordCounterActor.class.getName() });
  }
}
