/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

// line 158 "../../model.ump"
public class QueueTcp {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Queue_Tcp Attributes
  private LinkedList<String> messages;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public QueueTcp() {
    messages = new LinkedList<>();
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setMessages(List<String> aMessages) {
    boolean wasSet = false;
    messages = (LinkedList<String>) aMessages;
    wasSet = true;
    return wasSet;
  }

  /**
   * LinkedList messages;
   */
  public List<String> getMessages() {
    return messages;
  }

  public void delete() {
    /* */
  }

  // line 167 "../../model.ump"
  public synchronized void putMessage(String msgString) {
    Logger log = Logger.getLogger(QueueTcp.class.getName());

    messages.add(msgString);
    notifyAll();
    String msg = messages.element();

    String logMessage = "send message: " + msg;
    log.info(logMessage);
  }

  /**
   * Called by Receiver thread
   */
  // line 176 "../../model.ump"
  public synchronized String getMessage() throws InterruptedException {
    while (messages.isEmpty())
      wait();
    String message = messages.remove();
    return (message);
  }

  // line 183 "../../model.ump"
  public Boolean isEmptyMessage() {
    boolean emptyMessage = false;
    if (messages.isEmpty())
      emptyMessage = true;
    return emptyMessage;
  }

  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "]" + sep + "  " + "messages" + "=" + getMessages();
  }
}
