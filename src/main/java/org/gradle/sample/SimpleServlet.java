package org.gradle.sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.annotation.WebServlet;
import ns.Calculator;
import ns.ExampleStructure;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServlet;
import org.apache.thrift.transport.TIOStreamTransport;

class CalculatorHandler implements Calculator.Iface {

    // このクラスに唯一のインスタンス
    private static CalculatorHandler instance = new CalculatorHandler();

    public CalculatorHandler() {
    }

    // インスタンス取得メソッド
    public static CalculatorHandler getInstance() {
        return instance;
    }

    private int count = 0;

    @Override
    public int getCount() throws org.apache.thrift.TException {
        return ++this.count;
    }

    private int num = 0;

    @Override
    public void setNum(int num) throws org.apache.thrift.TException {
        this.num = num;
    }

    @Override
    public int getNum() throws org.apache.thrift.TException {
        return this.num;
    }

    @Override
    public long add(int num1, int num2) throws org.apache.thrift.TException {
        return num1 + num2;
    }

    @Override
    public String hello2(long num) throws org.apache.thrift.TException {
        return "hello(こんにちは): " + num;
    }

    @Override
    public ByteBuffer binfun() throws org.apache.thrift.TException {
        return ByteBuffer.wrap("abc\0".getBytes());
    }

    @Override
    public ExampleStructure structfun() throws org.apache.thrift.TException {
        ExampleStructure st = new ExampleStructure();
        st.id = 1234;
        st.message = "abc漢字";
        return st;
    }

    @Override
    public List<Long> listfun() throws org.apache.thrift.TException {
        List<Long> list = new ArrayList<>();
        list.add(11L);
        list.add(22L);
        return list;
    }

}
//@WebServlet(urlPatterns = {"/simple"})
public class SimpleServlet extends TServlet {

    public SimpleServlet() {
        //super(new Calculator.Processor(new CalculatorHandler()), new TBinaryProtocol.Factory());
        super(new Calculator.Processor(new CalculatorHandler()), new TJSONProtocol.Factory());
        addCustomHeader("Access-Control-Allow-Origin", "*");
    }

}

/*
public class SimpleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ContentTypeを設定
        response.setContentType("application/x-thrift");
        response.setHeader("Access-Control-Allow-Origin", "*");
        //response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS");
        //response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //response.setHeader("Access-Control-Max-Age", "-1");

        //TIOStreamTransportに設定するInputStreamとOutputStreamを
        //request、responseから取得する
        InputStream is = request.getInputStream();
        OutputStream out = response.getOutputStream();

        CalculatorHandler handler = CalculatorHandler.getInstance();
        Calculator.Processor processor = new Calculator.Processor(handler);
        TIOStreamTransport transport = new TIOStreamTransport(is, out);
        //TBinaryProtocol protocol = new TBinaryProtocol(transport, true, true);
        TProtocol protocol = new TJSONProtocol.Factory().getProtocol(transport);

        try {

            transport.open();
            processor.process(protocol, protocol);
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
*/