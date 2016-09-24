QT += core
QT -= gui

CONFIG += c++11

TARGET = thriftclient
CONFIG += console
CONFIG -= app_bundle

TEMPLATE = app

SOURCES += main.cpp \
    Calculator.cpp \
    calculator_constants.cpp \
    calculator_types.cpp

INCLUDEPATH += C:\local\boost_1_61_0
INCLUDEPATH += Z:\dev\openssl-1.0.1t-vs2015\include
INCLUDEPATH += Z:\dev\thrift-0.9.2\lib\cpp\src

LIBS += -LC:\local\boost_1_61_0\lib32-msvc-14.0
LIBS += Z:\dev\openssl-1.0.1t-vs2015\lib\libeay32MD.lib
LIBS += Z:\dev\openssl-1.0.1t-vs2015\lib\ssleay32MD.lib
LIBS += Z:\dev\thrift-0.9.2\lib\cpp\Release\libthrift.lib
LIBS += Z:\dev\openssl-1.0.1t-vs2015\lib\libeay32MD.lib
LIBS += Z:\dev\openssl-1.0.1t-vs2015\lib\ssleay32MD.lib
