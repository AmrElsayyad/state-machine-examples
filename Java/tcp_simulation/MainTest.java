/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package tcp_simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * mainTest class
 */
// line 448 "../../model.ump"
public class MainTest {

  // ------------------------
  // MEMBER VARIABLES
  // ------------------------
  static String umpleFile = "model.ump";

  // ------------------------
  // CONSTRUCTOR
  // ------------------------

  public MainTest() {
    /* */
  }

  // ------------------------
  // INTERFACE
  // ------------------------

  public void delete() {
    /* */
  }

  // line 452 "../../model.ump"
  public static void main(String[] args) {
    Thread.currentThread().setUncaughtExceptionHandler(new UmpleExceptionHandler());
    Thread.setDefaultUncaughtExceptionHandler(new UmpleExceptionHandler());
    TcpServer server = new TcpServer();
    server.start();
    TcpClient client = new TcpClient();
    client.start();
  }

  public static class UmpleExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
      translate(e);
      if (e.getCause() != null) {
        translate(e.getCause());
      }
      e.printStackTrace();
    }

    public void translate(Throwable e) {
      List<StackTraceElement> result = new ArrayList<>();
      StackTraceElement[] elements = e.getStackTrace();

      try {
        Collections.addAll(result, elements);
      } catch (Exception e1) {
        e1.printStackTrace();
      }

      e.setStackTrace(result.toArray(new StackTraceElement[0]));
    }

    // The following methods Map Java lines back to their original Umple file / line
    public UmpleSourceData tcpSendData() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(104).setJavaLines(398).setLengths(8);
    }

    public UmpleSourceData tcpSendFin() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(115).setJavaLines(410).setLengths(3);
    }

    public UmpleSourceData tcpActiveOpen() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(32).setJavaLines(139).setLengths(1);
    }

    public UmpleSourceData tcpSendSyn() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(85).setJavaLines(377).setLengths(3);
    }

    public UmpleSourceData tcpPassiveClose() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(61).setJavaLines(319).setLengths(1);
    }

    public UmpleSourceData tcpSendAck() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(97).setJavaLines(391).setLengths(3);
    }

    public UmpleSourceData tcpData() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(57).setJavaLines(299).setLengths(1);
    }

    public UmpleSourceData tcpSendSynAck() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(91).setJavaLines(384).setLengths(3);
    }

    public UmpleSourceData tcpSyn() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(36).setJavaLines(159).setLengths(1);
    }

    public UmpleSourceData tcpFin() {
      return new UmpleSourceData().setFileNames(umpleFile, umpleFile, umpleFile).setUmpleLines(55, 69, 73)
          .setJavaLines(247, 253, 259).setLengths(1, 1, 1);
    }

    public UmpleSourceData tcpActiveClose() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(56).setJavaLines(279).setLengths(1);
    }

    public UmpleSourceData tcpFinAck() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(70).setJavaLines(339).setLengths(1);
    }

    public UmpleSourceData tcpSynAck() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(49).setJavaLines(227).setLengths(1);
    }

    public UmpleSourceData queue1releaseSemC() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(140).setJavaLines(75).setLengths(1);
    }

    public UmpleSourceData queue1releaseSemS() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(152).setJavaLines(89).setLengths(1);
    }

    public UmpleSourceData queue1acquireSemC() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(132).setJavaLines(66).setLengths(5);
    }

    public UmpleSourceData queue1acquireSemS() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(144).setJavaLines(80).setLengths(5);
    }

    public UmpleSourceData queueTcpGetMessage() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(175).setJavaLines(65).setLengths(4);
    }

    public UmpleSourceData queueTcpPutMessage() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(166).setJavaLines(52).setLengths(5);
    }

    public UmpleSourceData queueTcpIsEmptyMessage() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(182).setJavaLines(73).setLengths(3);
    }

    public UmpleSourceData receiverRun() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(199).setJavaLines(49).setLengths(7);
    }

    public UmpleSourceData mySocketSend() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(236).setJavaLines(115).setLengths(2);
    }

    public UmpleSourceData mySocketClose() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(241).setJavaLines(121).setLengths(4);
    }

    public UmpleSourceData mySocketConnect() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(221).setJavaLines(97).setLengths(7);
    }

    public UmpleSourceData myServerSocketAccept() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(264).setJavaLines(115).setLengths(11);
    }

    public UmpleSourceData tcpClientRun() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(299).setJavaLines(129).setLengths(40);
    }

    public UmpleSourceData tcpServerServerStarted() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(430).setJavaLines(221).setLengths(1);
    }

    public UmpleSourceData tcpServerRun() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(363).setJavaLines(154).setLengths(63);
    }

    public UmpleSourceData tcpServerStopListening() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(439).setJavaLines(235).setLengths(1);
    }

    public UmpleSourceData tcpServerServerStopped() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(434).setJavaLines(226).setLengths(1);
    }

    public UmpleSourceData mainTestMain() {
      return new UmpleSourceData().setFileNames(umpleFile).setUmpleLines(451).setJavaLines(32).setLengths(4);
    }

  }

  public static class UmpleSourceData {
    String[] umpleFileNames;
    Integer[] umpleLines;
    Integer[] umpleJavaLines;
    Integer[] umpleLengths;

    public UmpleSourceData() {
      /* */
    }

    public String getFileName(int i) {
      return umpleFileNames[i];
    }

    public Integer getUmpleLine(int i) {
      return umpleLines[i];
    }

    public Integer getJavaLine(int i) {
      return umpleJavaLines[i];
    }

    public Integer getLength(int i) {
      return umpleLengths[i];
    }

    public UmpleSourceData setFileNames(String... filenames) {
      umpleFileNames = filenames;
      return this;
    }

    public UmpleSourceData setUmpleLines(Integer... umplelines) {
      umpleLines = umplelines;
      return this;
    }

    public UmpleSourceData setJavaLines(Integer... javalines) {
      umpleJavaLines = javalines;
      return this;
    }

    public UmpleSourceData setLengths(Integer... lengths) {
      umpleLengths = lengths;
      return this;
    }

    public int size() {
      return umpleFileNames.length;
    }
  }
}