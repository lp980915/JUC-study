package main.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class User{
    private int id;
    private String userName;
    private int age;
}

/**
 * 使用stream流式计算代替了很多sql语句，对集合进行过滤，修改等操作
 * 题目：找出同时满足偶数ID，年龄大于24且用户名转为大写，且用户名字母倒叙，只输出一个用户名字
 */
public class StreamDemo {
    public static void main(String[] args) {

        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list= Arrays.asList(u1,u2,u3,u4,u5);

        //使用stream将list转为stream，再使用collectors.toList将stream转为list
        //filter方法需要传入一个predicate断定型接口，传入的为user对象，返回布尔值，过滤掉返回为false的值
        //使用map方法将用户名转换为大写，且由user对象变为了String类型
        //sorted方法默认升序，也可以传入自定义排序规则,使用comparator函数式接口进行排序
        //limit方法可以截取指定部分元素，类似SQL关键字
        //count可以进行计数，返回long
        //还有很多操作
                list.stream()
                .filter((user)->{return user.getId()%2==0;})
                .filter(user -> {return user.getAge()>24;})
                        .map(user -> {return user.getUserName().toUpperCase();})
                        .sorted((username1,username2)->{return username2.compareTo(username1);})
                        .limit(1)
                        .forEach(System.out::println);

       /* //map映射方法demo
        List<Integer> list1=Arrays.asList(1,2,3);

        //使用map方法需要传入一个function函数型接口，使用stream将list转为stream，再使用collectors.toList将stream转为list
        list1.stream().map(x -> {return x*2;} ).collect(Collectors.toList());

        for (Integer integer : list1) {
            System.out.println(integer);
        }*/

        //---------------------------------------------------------------------
        //------------------------四大函数式接口(java.util.function)--------------
        //---------------------------------------------------------------------
       /* //Function函数型接口，泛型中第一位是参数类型，第二位是返回值类型，默认有一个apply方法
        Function<String,Integer> function=(String str)->{
            return str.length();
        };
        //匿名内部类写法
       *//* Function<String,Integer> function=new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };*//*
        System.out.println(function.apply("abc"));

        //断定型接口，可自定义参数类型，返回值必须为布尔值
        Predicate<String> predicate=(String s)->{
            return s.length()>2;
        };
        //匿名内部类写法
       *//* Predicate<String> predicate=new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()>2;
            }
        };*//*
        System.out.println(predicate.test("abc"));

        //以下接口省略匿名内部类写法

        //消费型接口，泛型要与参数类型一致，只有参数类型，无返回值
        Consumer<String> consumer=(String str)->{
            System.out.println(str+"消费型接口生效");
        };
        consumer.accept("abc");

        //供给型接口，泛型要与返回值类型一致，只有返回值，无参数类型
        Supplier<String> supplier=()->{
            return "消费性接口生效";
        };

        System.out.println(supplier.get());*/
    }
}
