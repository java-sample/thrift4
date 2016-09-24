thrift --gen java       -out src/main/java calculator.thrift
thrift --gen cpp        -out cpp-client    calculator.thrift
thrift --gen js         -out js-client     calculator.thrift
thrift --gen php:server -out /home/javagame/thrift-0.9.2/lib/php/lib/Thrift/Packages calculator2.thrift
pause
