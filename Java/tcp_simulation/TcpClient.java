/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

import java.util.logging.Logger;

/**
 * plement MySocket class
 */
// line 282 "../../model.ump"
public class TcpClient extends Thread {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Tcp_Client Attributes
  private MySocket clientSocket;
  private boolean readyToStop;
  private QueueTcp q;
  private Receiver receive;
  private String host;
  private int port;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public TcpClient() {
    super();
    clientSocket = null;
    readyToStop = false;
    q = new QueueTcp();
    receive = new Receiver(q);
    host = "localhost";
    port = 999;
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setClientSocket(MySocket aClientSocket) {
    boolean wasSet = false;
    clientSocket = aClientSocket;
    wasSet = true;
    return wasSet;
  }

  public boolean setReadyToStop(boolean aReadyToStop) {
    boolean wasSet = false;
    readyToStop = aReadyToStop;
    wasSet = true;
    return wasSet;
  }

  public boolean setQ(QueueTcp aQ) {
    boolean wasSet = false;
    q = aQ;
    wasSet = true;
    return wasSet;
  }

  public boolean setReceive(Receiver aReceive) {
    boolean wasSet = false;
    receive = aReceive;
    wasSet = true;
    return wasSet;
  }

  public boolean setHost(String aHost) {
    boolean wasSet = false;
    host = aHost;
    wasSet = true;
    return wasSet;
  }

  public boolean setPort(int aPort) {
    boolean wasSet = false;
    port = aPort;
    wasSet = true;
    return wasSet;
  }

  public MySocket getClientSocket() {
    return clientSocket;
  }

  public boolean getReadyToStop() {
    return readyToStop;
  }

  public QueueTcp getQ() {
    return q;
  }

  public Receiver getReceive() {
    return receive;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public void delete() {
    /* */
  }

  // line 300 "../../model.ump"
  @Override
  public void run() {
    Logger log = Logger.getLogger(QueueTcp.class.getName());

    try {
      // connects client with the server
      clientSocket = new MySocket(getHost(), getPort());
      clientSocket.connect();
      while (Boolean.FALSE.equals(q.isEmptyMessage())) {
        receive.start();
      }
      log.info("Client: Connected");
    } catch (Exception ex) {
      try {
        // Close the socket
        if (clientSocket != null)
          clientSocket.close();
      } finally {
        clientSocket = null;
      }
      log.info("Client: Closed");
    }
    if (clientSocket == null) {
      // closes the socket
      setReadyToStop(true);
      try {
        // Close the socket
        if (clientSocket != null)
          clientSocket.close();
      } finally {
        clientSocket = null;
      }
    }
  }

  @Override
  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "readyToStop" + ":" + getReadyToStop() + "," + "host" + ":" + getHost() + ","
        + "port" + ":" + getPort() + "]" + sep + "  " + "clientSocket" + "=" + getClientSocket() + sep + "  " + "q"
        + "=" + getQ() + sep + "  " + "receive" + "=" + getReceive();
  }
}
