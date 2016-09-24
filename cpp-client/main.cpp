#include <QtCore>

#include <iostream>
//#include <thrift/protocol/TBinaryProtocol.h>
#include <thrift/protocol/TJSONProtocol.h>
#include <thrift/transport/TSSLSocket.h>
#include <thrift/transport/THttpClient.h>
#include <thrift/transport/TTransportUtils.h>
#include <thrift/protocol/TMultiplexedProtocol.h>

#include "Calculator.h"

#include <string>
#include <stdio.h>

using namespace std;

using namespace apache::thrift;
using namespace apache::thrift::protocol;
using namespace apache::thrift::transport;
using namespace ::example;

void sub()
{
#if 0x0
    boost::shared_ptr<TSSLSocketFactory> sslSocketFactory(new TSSLSocketFactory());
    boost::shared_ptr<TSSLSocket> sslSocket = sslSocketFactory->createSocket("jspcloud10.raservers.net", 443);
    boost::shared_ptr<TBufferedTransport> bufferedTransport(new TBufferedTransport(sslSocket));
    boost::shared_ptr<TTransport> transport(new THttpClient(bufferedTransport,"jspcloud10.raservers.net", "/~javagame/docs/thrift.php"));
    boost::shared_ptr<TProtocol> protocol(new TJSONProtocol(transport));
    CalculatorClient client(protocol);
#else
    boost::shared_ptr<TSSLSocketFactory> sslSocketFactory(new TSSLSocketFactory());
    boost::shared_ptr<TSSLSocket> sslSocket = sslSocketFactory->createSocket("localhost", 443);
    boost::shared_ptr<TBufferedTransport> bufferedTransport(new TBufferedTransport(sslSocket));
    boost::shared_ptr<TTransport> transport(new THttpClient(bufferedTransport,"localhost", "/thrift.php"));
    boost::shared_ptr<TProtocol> protocol(new TJSONProtocol(transport));
    CalculatorClient client(protocol);
#endif
    try {
        transport->open();
        cout << "1 + 1 = " << client.add(1, 1) << endl;
        transport->close();
    } catch (TException& tx) {
        cout << "ERROR: " << tx.what() << endl;
    }
}

int main(int argc, char** argv)
{
    boost::shared_ptr<TTransport> transport(new THttpClient("localhost", 9090, "/api/simple"));
    //boost::shared_ptr<TProtocol> protocol(new TBinaryProtocol(transport));
    boost::shared_ptr<TProtocol> protocol(new TJSONProtocol(transport));
    CalculatorClient client(protocol);

    printf("(3)\n");

    try {
        transport->open();

        client.setNum(1234);

        cout << "client.getNum() = " << client.getNum() << endl;
        cout << "1 + 1 = " << client.add(1, 1) << endl;

        string ret;
        client.hello2(ret, 123);

        QString ret2 = QString::fromUtf8(ret.c_str());
        QString ret3 = ret.c_str();

        qDebug() << u8"localローカル";
        qDebug() << ret2;
        qDebug() << ret3;

        client.binfun(ret);

        qDebug() << ret.size();

        qDebug() << ret.c_str();

        ExampleStructure st;
        client.structfun(st);
        qDebug() << st.message.c_str() << QString::fromUtf8(st.message.c_str());

        std::vector<int64_t> vec;
        client.listfun(vec);
        for(int i=0; i<vec.size(); i++)
        {
            qDebug() << vec[i];
        }

        transport->close();
    } catch (TException& tx) {
        cout << "ERROR: " << tx.what() << endl;
    }
    sub();
    return 0;
}
