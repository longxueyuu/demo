namespace java com.lxy.service
namespace py com.lxy.service



service DemoSevice {
    string say(1: required string name)
    i64 add(1: required i64 a, 2: required i64 b)
}
