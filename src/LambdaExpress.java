/**
 * 函数式编程
 */
public class LambdaExpress {
    public static void main(String[] args) {
        Abc abc = ()->{ System.out.println("爬爬爬"); };
        abc.efg();
        System.out.println(abc.mul(3, 5));
        System.out.println(Abc.mul1(3, 5));
        AAA aaa=(int i,int j)->{
            return i+j;
        };
        System.out.println(aaa.efg(10,20));
    }
}

//lambda实现的接口只能由一个方法,@functionInterface代表它是一个函数式接口
@FunctionalInterface
interface Abc{
   public void efg();
//    public void hef();
    //java8开始支持接口中定义添加default字段方法的实现，且可以定义多个(前提是函数式接口)
    default int mul(int x,int y){
        return x * y;
    }
    //java8开始支持接口定义静态方法并实现，且可以定义多个(前提是函数式接口)
    static int mul1(int x,int y){
        return x * y;
    }
}

interface AAA{
    public int efg(int i,int j);
}
