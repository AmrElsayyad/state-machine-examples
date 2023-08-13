/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

// line 211 "../../model.ump"
public class MySocket {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // MySocket Attributes
  private String host;
  private int port;
  private Tcp tcp;
  private Queue1 q;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public MySocket(String aHost, int aPort) {
    host = aHost;
    port = aPort;
    tcp = new Tcp();
    q = new Queue1();
  }

  // ------------------------
  // INTERFACE
  // ------------------------

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

  public boolean setTcp(Tcp aTcp) {
    boolean wasSet = false;
    tcp = aTcp;
    wasSet = true;
    return wasSet;
  }

  public boolean setQ(Queue1 aQ) {
    boolean wasSet = false;
    q = aQ;
    wasSet = true;
    return wasSet;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  /**
   * MySocket needs a constructor to create the tcp
   */
  public Tcp getTcp() {
    return tcp;
  }

  /**
   * use a semaphore to synchronize sending signals between the threads
   */
  public Queue1 getQ() {
    return q;
  }

  public void delete() {
    /* */
  }

  // line 222 "../../model.ump"
  public void connect() {
    // Connects client to the server
    try {
      tcp.activeOpen();
      q.acquireSemS();
    } catch (Exception e) {
      e.printStackTrace();
    }
    q.releaseSemS();
    tcp.syncAck();
  }

  /**
   * void receive(){
   * //receive data from the server
   * tcp.Data_R();
   * }
   */
  // line 237 "../../model.ump"
  public void send() {
    // send data to client
    tcp.data();
  }

  // line 242 "../../model.ump"
  public void close() {
    // Closes the socket
    tcp.activeClose();
    // Closes the serverSocket
    tcp.passiveClose();
  }

  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "host" + ":" + getHost() + "," + "port" + ":" + getPort() + "]" + sep + "  " + "tcp"
        + "=" + getTcp() + sep + "  " + "q" + "=" + getQ();
  }
}
