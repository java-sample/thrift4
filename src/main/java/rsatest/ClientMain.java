package rsatest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;
import ns.Calculator;
import org.eclipse.jetty.server.Server;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransportException;
import org.eclipse.jetty.server.NetworkConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.gradle.sample.SimpleServlet;

public class ClientMain {

    ////private static PublicKey publicKey = null;

    public static void main(String[] args) throws Exception {

        //Server server = new Server(new InetSocketAddress("127.0.0.1", 0));
        Server server = new Server(new InetSocketAddress("127.0.0.1", 9090));

        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/api", true, false);
        servletContextHandler.addServlet(SimpleServlet.class, "/simple/*");
        server.setHandler(servletContextHandler);

        //server.setHandler(webapp);
        System.out.println("(1)");
        try {
            server.start();
            NetworkConnector networkConnector = (NetworkConnector) server.getConnectors()[0];
            int port = networkConnector.getLocalPort();
            runThrift(port);
            runThriftPHP(port);
            TimeUnit.DAYS.sleep(100);
        } finally {
            System.out.println("(100)");
            server.stop();
        }
    }

    // http://localhost/thrift.php
    private static void runThriftPHP(int port) throws TTransportException, TException {
        //接続先のURLを指定
        THttpClient transport = new THttpClient("https://jspcloud10.raservers.net/~javagame/docs/thrift.php");
        //THttpClient transport = new THttpClient("https://localhost/thrift.php");
        //TProtocol protocol = new TBinaryProtocol(transport);
        TProtocol protocol = new TJSONProtocol(transport);
        Calculator.Client client = new Calculator.Client(protocol);

        try {
            transport.open();
            long sum = client.add(2, 5);
            System.out.println("2+5=" + sum);
            sum = client.add(11, 22);
            System.out.println("11+25=" + sum);
            transport.close();
        } catch (TException te) {
            te.printStackTrace();
        }
    }

    private static void runThrift(int port) throws TTransportException, TException {
        //接続先のURLを指定
        THttpClient transport = new THttpClient(String.format("http://localhost:%d/api/simple", port));
        //TProtocol protocol = new TBinaryProtocol(transport);
        TProtocol protocol = new TJSONProtocol(transport);
        Calculator.Client client = new Calculator.Client(protocol);

        try {
            transport.open();
            System.out.println("count=" + client.getCount());
            System.out.println("count=" + client.getCount());
            client.setNum(1234);
            System.out.println(client.getNum());
            //server側のaddメソッドを呼び出す
            long sum = client.add(2, 5);
            System.out.println("2+5=" + sum);
            sum = client.add(11, 22);
            System.out.println("11+25=" + sum);
            transport.close();
        } catch (TException te) {
            te.printStackTrace();
        }
    }
}
