/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * Queue_1 class that have acquire and release methods used to send signals
 * between the threads
 */
// line 125 "../../model.ump"
public class Queue1 {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Queue_1 Attributes
  private Semaphore semC;
  private Semaphore semS;
  private Logger log;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Queue1() {
    semC = new Semaphore(0);
    semS = new Semaphore(1);
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setSemC(Semaphore aSemC) {
    boolean wasSet = false;
    semC = aSemC;
    wasSet = true;
    return wasSet;
  }

  public boolean setSemS(Semaphore aSemS) {
    boolean wasSet = false;
    semS = aSemS;
    wasSet = true;
    return wasSet;
  }

  public Semaphore getSemC() {
    return semC;
  }

  public Semaphore getSemS() {
    return semS;
  }

  public void delete() {
    /* */
  }

  // line 133 "../../model.ump"
  public void acquireSemC() {
    try {
      semC.acquire();
    } catch (InterruptedException e) {
      log.warning("InterruptedException caught");
      Thread.currentThread().interrupt();
    }
  }

  // line 141 "../../model.ump"
  public void releaseSemC() {
    semC.release();
  }

  // line 145 "../../model.ump"
  public void acquireSemS() {
    try {
      semS.acquire();
    } catch (InterruptedException e) {
      log.warning("InterruptedException caught");
      Thread.currentThread().interrupt();
    }
  }

  // line 153 "../../model.ump"
  public void releaseSemS() {
    semS.release();
  }

  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "]" + sep + "  " + "semC" + "=" + getSemC() + sep + "  " + "semS" + "=" + getSemS();
  }
}
