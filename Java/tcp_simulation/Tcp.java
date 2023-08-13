/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

import java.io.*;
import java.io.IOException;

// line 6 "../../model.ump"
public class Tcp {

  // ------------------------
  // STATIC VARIABLES
  // ------------------------

  /**
   * Tcp flags
   */
  public static final String SYN = "SYN";
  public static final String ACK = "ACK";
  public static final String FIN = "FIN";
  public static final String RST = "RST";
  public static final String SYNACK = "SYNAck";

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Tcp Attributes
  private QueueTcp q;
  private BufferedReader in;
  private String sentence;

  // Tcp State Machines
  public enum Connection {
    CLOSED, LISTEN, SYN_RECEIVED, SYN_SENT, ESTABLISHED, CLOSE_WAIT, LAST_ACK, FIN_WAIT_1, FIN_WAIT_2, CLOSING,
    TIMED_WAIT
  }

  private Connection connection;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public Tcp() {
    q = new QueueTcp();
    in = null;
    sentence = null;
    setConnection(Connection.CLOSED);
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

  public boolean setIn(BufferedReader aIn) {
    boolean wasSet = false;
    in = aIn;
    wasSet = true;
    return wasSet;
  }

  public boolean setSentence(String aSentence) {
    boolean wasSet = false;
    sentence = aSentence;
    wasSet = true;
    return wasSet;
  }

  /**
   * Queues for adding and removing Tcp flags
   * LinkedList messages= new LinkedList();
   */
  public QueueTcp getQ() {
    return q;
  }

  public BufferedReader getIn() {
    return in;
  }

  public String getSentence() {
    return sentence;
  }

  public String getConnectionFullName() {
    String answer = connection.toString();
    return answer;
  }

  public Connection getConnection() {
    return connection;
  }

  public boolean passiveOpen() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case CLOSED:
        setConnection(Connection.LISTEN);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean activeOpen() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case CLOSED:
        // line 33 "../../model.ump"
        sendSyn();
        setConnection(Connection.SYN_SENT);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean sync() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    if (aConnection == Connection.LISTEN) {
      sendSynAck();
      setConnection(Connection.SYN_RECEIVED);
      wasEventProcessed = true;
    }

    return wasEventProcessed;
  }

  public boolean close() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case LISTEN:
        setConnection(Connection.CLOSED);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean acknowledge() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case SYN_RECEIVED:
        setConnection(Connection.ESTABLISHED);
        wasEventProcessed = true;
        break;
      case LAST_ACK:
        setConnection(Connection.CLOSED);
        wasEventProcessed = true;
        break;
      case FIN_WAIT_1:
        setConnection(Connection.FIN_WAIT_2);
        wasEventProcessed = true;
        break;
      case CLOSING:
        setConnection(Connection.TIMED_WAIT);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean syncAck() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case SYN_SENT:
        // line 50 "../../model.ump"
        sendAck();
        setConnection(Connection.ESTABLISHED);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finish() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case ESTABLISHED:
        // line 56 "../../model.ump"
        sendAck();
        setConnection(Connection.CLOSE_WAIT);
        wasEventProcessed = true;
        break;
      case FIN_WAIT_1:
        // line 70 "../../model.ump"
        sendAck();
        setConnection(Connection.CLOSING);
        wasEventProcessed = true;
        break;
      case FIN_WAIT_2:
        // line 74 "../../model.ump"
        sendAck();
        setConnection(Connection.TIMED_WAIT);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean activeClose() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case ESTABLISHED:
        // line 57 "../../model.ump"
        sendFin();
        setConnection(Connection.FIN_WAIT_1);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean data() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case ESTABLISHED:
        // line 58 "../../model.ump"
        sendData();
        setConnection(Connection.ESTABLISHED);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean passiveClose() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case CLOSE_WAIT:
        // line 62 "../../model.ump"
        sendFin();
        setConnection(Connection.LAST_ACK);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finAck() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case FIN_WAIT_1:
        // line 71 "../../model.ump"
        sendAck();
        setConnection(Connection.TIMED_WAIT);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean timeOut() {
    boolean wasEventProcessed = false;

    Connection aConnection = connection;
    switch (aConnection) {
      case TIMED_WAIT:
        setConnection(Connection.CLOSED);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setConnection(Connection aConnection) {
    connection = aConnection;
  }

  public void delete() {
    /* */
  }

  // line 86 "../../model.ump"
  public synchronized void sendSyn() {
    q.putMessage(SYN);
  }

  // line 92 "../../model.ump"
  public synchronized void sendSynAck() {
    q.putMessage(SYNACK);
  }

  // line 98 "../../model.ump"
  public synchronized void sendAck() {
    q.putMessage(ACK);
  }

  // line 105 "../../model.ump"
  public synchronized void sendData() {
    in = new BufferedReader(new InputStreamReader(System.in));
    String sn;
    try {
      sn = in.readLine();
      q.putMessage(sn);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  // line 116 "../../model.ump"
  public synchronized void sendFin() {
    q.putMessage(FIN);
  }

  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "sentence" + ":" + getSentence() + "]" + sep + "  " + "q" + "=" + getQ() + sep
        + "  " + "in" + "=" + getIn();
  }
}
