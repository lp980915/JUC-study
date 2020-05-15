import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StreamDemo {
    public static void main(String[] args) {
        //Function函数型接口，泛型中第一位是参数类型，第二位是返回值类型，默认有一个apply方法
        Function<String,Integer> function=(String str)->{
            return str.length();
        };
        //匿名内部类写法
       /* Function<String,Integer> function=new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };*/
        System.out.println(function.apply("abc"));

        //断定型接口，可自定义参数类型，返回值必须为布尔值
        Predicate<String> predicate=(String s)->{
            return s.length()>2;
        };
        //匿名内部类写法
       /* Predicate<String> predicate=new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()>2;
            }
        };*/
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

        System.out.println(supplier.get());
    }
}
