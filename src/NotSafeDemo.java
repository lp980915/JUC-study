import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

//ArrayList线程不安全案例

/**
 * 故障现象：
 * java.util.ConcurrentModificationException并发修改异常
 *
 * 导致原因：
 * 多个线程同时争抢统一资源类，且没有加锁
 *
 * 解决方法(Set同理,Map同理，但是Map的安全集合为concurrentHashMap):
 * 1.new Vector<>(); 加了synchronized锁
 * 2.Collections.synchronizedList(new ArrayList<>());  Collections工具类提供的加锁方法
 * 3.new CopyOnWriteArrayList<>();  JUC中的解决方式,写时复制，类似读写分离
 *
 * 优化建议：
 *
 */
public class NotSafeDemo {
    public static void main(String[] args) {
//        NotSafeDemo.notSafeList();
//        NotSafeDemo.notSafeSet();
//        NotSafeDemo.notSafeMap();
    }

    public static void notSafeList(){
        List<String> list=new CopyOnWriteArrayList<>(); //Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();
        for (int i=0;i<=30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    public static void notSafeSet(){
        Set<String> set=new CopyOnWriteArraySet<>();//new HashSet<>();
        for (int i=0;i<=30;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    public static void notSafeMap(){
        Map<String,String> map=new ConcurrentHashMap<>();//new HashMap<>();
        for (int i=0;i<=30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
