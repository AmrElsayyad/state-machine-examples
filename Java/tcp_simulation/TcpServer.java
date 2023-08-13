/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

import java.util.logging.Logger;

/**
 * Creat Server class to implement the MyServerSocket class
 */
// line 347 "../../model.ump"
public class TcpServer extends Thread {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // Tcp_Server Attributes
  private MyServerSocket serverSocket;
  private MySocket clientSocket;
  private boolean listening;
  private QueueTcp q;
  private Receiver receive;
  private int port;
  private boolean readyToStop;
  private Logger log;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public TcpServer() {
    super();
    serverSocket = null;
    clientSocket = null;
    listening = false;
    q = new QueueTcp();
    receive = new Receiver(q);
    port = 999;
    readyToStop = true;
    log = Logger.getLogger(TcpServer.class.getName());
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public boolean setServerSocket(MyServerSocket aServerSocket) {
    boolean wasSet = false;
    serverSocket = aServerSocket;
    wasSet = true;
    return wasSet;
  }

  public boolean setClientSocket(MySocket aClientSocket) {
    boolean wasSet = false;
    clientSocket = aClientSocket;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsListening(boolean aIsListening) {
    boolean wasSet = false;
    listening = aIsListening;
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

  public boolean setPort(int aPort) {
    boolean wasSet = false;
    port = aPort;
    wasSet = true;
    return wasSet;
  }

  public boolean setReadyToStop(boolean aReadyToStop) {
    boolean wasSet = false;
    readyToStop = aReadyToStop;
    wasSet = true;
    return wasSet;
  }

  public MyServerSocket getServerSocket() {
    return serverSocket;
  }

  public MySocket getClientSocket() {
    return clientSocket;
  }

  public boolean isListening() {
    return listening;
  }

  public QueueTcp getQ() {
    return q;
  }

  public Receiver getReceive() {
    return receive;
  }

  public int getPort() {
    return port;
  }

  public boolean isReadyToStop() {
    return readyToStop;
  }

  public void delete() {
    /* */
  }

  // line 364 "../../model.ump"
  @Override
  public void run() {
    if (!isListening() && (serverSocket == null)) {
      serverSocket = new MyServerSocket(getPort());
    }

    // listens to the client and accept the connection
    setReadyToStop(false);
    serverStarted();
    try {
      // waits for a new client connection, accepts it
      while (!isReadyToStop()) {
        receive = new Receiver(new QueueTcp());
        if (serverSocket != null) {
          serverSocket.accept();
        }
        while (Boolean.FALSE.equals(q.isEmptyMessage())) {
          receive.start();
        }

        // Sends a message to client connected to the server
        if (clientSocket == null) {
          log.info("socket does not exist");
        } else {
          clientSocket.send();
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      setReadyToStop(true);
      serverStopped();
      // Closes all connection to the server
      try {
        // Close the sSocket and the connection with client
        if (serverSocket == null) {
          stopListening();
        } else if (serverSocket != null || clientSocket != null) {
          clientSocket.close();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      } finally {
        clientSocket = null;
        serverSocket = null;
      }
    }
  }

  // line 431 "../../model.ump"
  protected void serverStarted() {
    log.info("Server: Started");
  }

  // line 435 "../../model.ump"
  protected void serverStopped() {
    log.info("Server: Stopped");
  }

  /**
   * Causes the server to stop accepting new connections
   */
  // line 440 "../../model.ump"
  public void stopListening() {
    setReadyToStop(true);
  }

  @Override
  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "isListening" + ":" + isListening() + "," + "port" + ":" + getPort() + ","
        + "readyToStop" + ":" + isReadyToStop() + "]" + sep + "  " + "serverSocket" + "=" + getServerSocket() + "  "
        + "clientSocket" + "=" + getClientSocket() + sep + "  " + "q" + "=" + getQ() + sep + "  " + "receive" + "="
        + getReceive();
  }
}