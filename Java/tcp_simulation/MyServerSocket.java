/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

// line 250 "../../model.ump"
public class MyServerSocket {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------

  // MyServerSocket Attributes
  private int port;
  private Tcp tcp;
  private MySocket clientsocket;
  private MyServerSocket serverSocket;
  private Queue1 q;

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public MyServerSocket(int aPort) {
    port = aPort;
    tcp = new Tcp();
    clientsocket = null;
    serverSocket = null;
    q = new Queue1();
  }

  // ------------------------
  // INTERFACE
  // ------------------------

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

  public boolean setClientsocket(MySocket aClientsocket) {
    boolean wasSet = false;
    clientsocket = aClientsocket;
    wasSet = true;
    return wasSet;
  }

  public boolean setServerSocket(MyServerSocket aServerSocket) {
    boolean wasSet = false;
    serverSocket = aServerSocket;
    wasSet = true;
    return wasSet;
  }

  public boolean setQ(Queue1 aQ) {
    boolean wasSet = false;
    q = aQ;
    wasSet = true;
    return wasSet;
  }

  public int getPort() {
    return port;
  }

  public Tcp getTcp() {
    return tcp;
  }

  public MySocket getClientsocket() {
    return clientsocket;
  }

  public MyServerSocket getServerSocket() {
    return serverSocket;
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

  /**
   * The MyServerSocket accept method's job is to start a new MySocket
   * which happens at L251. However, it seems to me that it should not have
   * its own Tcp, it should send passiveOpen to the tcp of MySocket.
   */
  // line 265 "../../model.ump"
  public void accept() {
    // Listens for a connection to be made to this socket and accepts it
    tcp.passiveOpen();
    clientsocket = new MySocket("localHost", 999);
    try {
      tcp.sync();
      q.acquireSemC();
    } catch (Exception e) {
      e.printStackTrace();
    }
    q.releaseSemC();
    tcp.acknowledge();
  }

  @Override
  public String toString() {
    String sep = System.getProperties().getProperty("line.separator");
    return super.toString() + "[" + "port" + ":" + getPort() + "]" + sep + "  " + "tcp" + "=" + getTcp() + sep + "  "
        + "clientsocket" + "=" + getClientsocket() + sep + "  " + "serverSocket" + "=" + getServerSocket() + sep + "  "
        + "q" + "=" + getQ();
  }
}
