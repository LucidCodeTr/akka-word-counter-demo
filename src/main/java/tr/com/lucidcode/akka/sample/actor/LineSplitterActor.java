package tr.com.lucidcode.akka.sample.actor;

import akka.actor.UntypedActor;

public class LineSplitterActor extends UntypedActor {

  public static enum Msg {
    GREET, DONE;
  }

  @Override
  public void onReceive(Object msg) {
    if (msg == Msg.GREET) {
      System.out.println("Hello World!");
      getSender().tell(Msg.DONE, getSelf());
    } else
      unhandled(msg);
  }

}
