import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读操作可以并发完成，写操作必须加锁，保证数据的一致性
//通过ReadWriteLock来保证写操作的一致性，当一个线程在进行写操作时，其他线程不能进行读写操作
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        readWrite readWrite = new readWrite();
        for (int i = 1; i <= 5; i++) {
            int num = i;
            new Thread(()->{
                readWrite.write(String.valueOf(num), num);
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int num=i;
            new Thread(()->{
                readWrite.read(String.valueOf(num));
            },String.valueOf(i)).start();
        }
    }
}

class readWrite{
    private volatile Map<String,Object> map=new HashMap<>();
    ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void write(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入数据"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取数据");
            Object result=map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取数据完成"+result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
