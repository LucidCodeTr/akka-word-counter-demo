package tr.com.lucidcode.akka.sample.main;

import tr.com.lucidcode.akka.sample.actor.WordCounterActor;

public class Main {

  public static void main(String[] args) {
    akka.Main.main(new String[] { WordCounterActor.class.getName() });
  }
}
