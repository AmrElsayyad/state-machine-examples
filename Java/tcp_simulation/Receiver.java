/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

import java.util.logging.Logger;

// line 192 "../../model.ump"
public class Receiver extends Thread {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Receiver Attributes
  private QueueTcp q;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Receiver(QueueTcp aQ) {
    super();
    q = aQ;
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setQ(QueueTcp aQ) {
    boolean wasSet = false;
    q = aQ;
    wasSet = true;
    return wasSet;
  }

  public QueueTcp getQ() {
    return q;
  }

  public void delete() {
    /* */
  }

  // line 200 "../../model.ump"
  @Override
  public void run() {
    Logger log = Logger.getLogger(Receiver.class.getName());
    try {
      while (Boolean.FALSE.equals(q.isEmptyMessage())) {
        String message = q.getMessage();
        String logMessage = "Got message: " + message;
        log.info(logMessage);
        sleep(2000);
      }
    } catch (InterruptedException e) {
      log.warning("Thread interrupted: " + e.getMessage());
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "]" + sep + "  " + "q" + "=" + getQ();
  }
}
