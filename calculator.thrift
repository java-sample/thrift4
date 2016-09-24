namespace java ns
namespace cpp example
namespace js ns
namespace php ns

struct ExampleStructure
{
  1:i32 id;
  2:string message;
}

service Calculator
{
    i32 getCount();
    void setNum(1:i32 num);
    i32 getNum();
    i64 add(1:i32 num1, 2:i32 num2);
    string hello2(1:i64 num);
    binary binfun();
    ExampleStructure structfun();
    list<i64> listfun();
}
