/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#include "calculator_types.h"

#include <algorithm>
#include <ostream>

#include <thrift/TToString.h>

namespace example {


ExampleStructure::~ExampleStructure() throw() {
}


void ExampleStructure::__set_id(const int32_t val) {
  this->id = val;
}

void ExampleStructure::__set_message(const std::string& val) {
  this->message = val;
}

const char* ExampleStructure::ascii_fingerprint = "3F5FC93B338687BC7235B1AB103F47B3";
const uint8_t ExampleStructure::binary_fingerprint[16] = {0x3F,0x5F,0xC9,0x3B,0x33,0x86,0x87,0xBC,0x72,0x35,0xB1,0xAB,0x10,0x3F,0x47,0xB3};

uint32_t ExampleStructure::read(::apache::thrift::protocol::TProtocol* iprot) {

  uint32_t xfer = 0;
  std::string fname;
  ::apache::thrift::protocol::TType ftype;
  int16_t fid;

  xfer += iprot->readStructBegin(fname);

  using ::apache::thrift::protocol::TProtocolException;


  while (true)
  {
    xfer += iprot->readFieldBegin(fname, ftype, fid);
    if (ftype == ::apache::thrift::protocol::T_STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
        if (ftype == ::apache::thrift::protocol::T_I32) {
          xfer += iprot->readI32(this->id);
          this->__isset.id = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 2:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->message);
          this->__isset.message = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      default:
        xfer += iprot->skip(ftype);
        break;
    }
    xfer += iprot->readFieldEnd();
  }

  xfer += iprot->readStructEnd();

  return xfer;
}

uint32_t ExampleStructure::write(::apache::thrift::protocol::TProtocol* oprot) const {
  uint32_t xfer = 0;
  oprot->incrementRecursionDepth();
  xfer += oprot->writeStructBegin("ExampleStructure");

  xfer += oprot->writeFieldBegin("id", ::apache::thrift::protocol::T_I32, 1);
  xfer += oprot->writeI32(this->id);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("message", ::apache::thrift::protocol::T_STRING, 2);
  xfer += oprot->writeString(this->message);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldStop();
  xfer += oprot->writeStructEnd();
  oprot->decrementRecursionDepth();
  return xfer;
}

void swap(ExampleStructure &a, ExampleStructure &b) {
  using ::std::swap;
  swap(a.id, b.id);
  swap(a.message, b.message);
  swap(a.__isset, b.__isset);
}

ExampleStructure::ExampleStructure(const ExampleStructure& other0) {
  id = other0.id;
  message = other0.message;
  __isset = other0.__isset;
}
ExampleStructure& ExampleStructure::operator=(const ExampleStructure& other1) {
  id = other1.id;
  message = other1.message;
  __isset = other1.__isset;
  return *this;
}
std::ostream& operator<<(std::ostream& out, const ExampleStructure& obj) {
  using apache::thrift::to_string;
  out << "ExampleStructure(";
  out << "id=" << to_string(obj.id);
  out << ", " << "message=" << to_string(obj.message);
  out << ")";
  return out;
}

} // namespace
