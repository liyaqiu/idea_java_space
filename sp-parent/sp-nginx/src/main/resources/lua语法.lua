#! /usr/local/bin/lua
--[[

lua语言

--]]

--打印
print("hello-world1")

str1="hello"
str2="world2"
str3=[[
hello
world2
hello
world2
]]


local str4="局部变量"

print(str1..str2)
print(str3)

print("hello".."world3")
print("字符串长度:"..#("hello".."world4"))

--数据类型 tupe()

arr = {"1","2","3"}
arr1 = {name="liyaqiu",age=31}
objarr2 = {"111",name="objname",arr,arr1}


print(type("hello"))
print(type(print))
print(type(nil))
print(type(arr))
print(type(arr1))

print(arr[1])
print(arr[2])
print(arr1["name"])
print(arr1.name)
print(arr1["age"])
print(arr1.age)

print(objarr2[1])
print(objarr2["name"])
print(objarr2.name)

--objarr2 = {"111",name="objname",arr,arr1}
--需要注意的是，如果不带key的那么不会占用index
-- 1 = 111
-- 2 = arr
-- 3 = arr1
print(objarr2[2][1])
print(objarr2[3].name)
print(objarr2[3]["name"])


--函数
function abc1(a,b,c)
    print(a,b,c)
    return a,b,c
end
function abc2(...)
    local a,b,c = ...;
    print(a,b,c)
    return a,b,c
end

abc1()
abc1(1)
abc1(1,2)
abc1(1,2,3)
abc1(1,2,3,4)

a,b,c = abc1(100,200,300)
print(a,b,c)
a,b,c = abc2(400,500,600)
print(a,b,c)


function test_if(a)
    if a == 1 then
        print("结果是1"..a)
    elseif a == 2 then
        print("结果是2"..a)
    else
        print("结果是其他"..a)
    end
end

test_if(1)
test_if(2)
test_if(3)

function test_while()
    local i = 1
    while i <=10 do
        print(i)
        i = i +1
    end
end
test_while()
print("-----------------------------")
function test_repeat()
    local i = 1
    repeat
        print(i)
        i = i +1
        print(i <=10)
    until not(i <=10)  --结果为真久会退出循环
end
test_repeat()

print("-----------------------------")
function test_for1() --第三个参数为步长
    for i = 1,10,2 do
    print(i)
    end
end
test_for1()


print("------------类似java增强for循环------ipairs -----------")

array = {"name1",name="liyaqiu","name2",3}
function test_for2()
    for index,value in ipairs(array) do
        print(index,value)
    end
end
test_for2()
print("------------类似java增强for循环------pairs -----------")
function test_for3()
    for key,value in pairs(array) do
        print(key,value)
    end
end
test_for3()

print("------------------------------")

function test_args(a,b)
    print("---------------a---------------")
    for key,value in pairs(a) do
            print(key,value)
    end
    print("--------------b----------------")
    for key,value in pairs(b) do
            print(key,value)
    end
end
test_args({
    name="liyaqiu",
    age = 123,
    hell = "world"
},
{
    name="liyaqiu1",
    age = 1234,
    hell = "world5"
}
)
