package redis;

import redis.manager.RedisConnectionManager;
import redis.manager.dao.RedisDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App 
{


    public static List<String> getMassiveList() {
        List<String> s = new ArrayList<>();
        for(int i=0;i < 10000;i++) {
            s.add("abc" + System.currentTimeMillis());
        }
        return s;
    }

    public static void main( String[] args )
    {

        Thread monitor,a,b,c,d;
        RedisConnectionManager.init("D:\\mygithub\\RedisUtil\\etc\\redis.properties");
        RedisConnectionManager redisConnectionManager = RedisConnectionManager.getInstance();

        RedisDao redisDao = new RedisDao();
        a = new Thread(()->{
            while(true) {
                System.out.println("before do a");
                redisDao.lPush("a",getMassiveList());
                System.out.println("after do a");
                try {
                    Thread.sleep(10000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        monitor = new Thread(()->{
            while(true) {
                redisConnectionManager.printDefaultPoolMetricInStringToConsole();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();
        monitor.start();
//        b = new Thread(()->{
//            while(true) {
//                redisDao.lPush("b","b " + System.currentTimeMillis());
//                try {
//                    Thread.sleep(1000l);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        c = new Thread(()->{
//            while(true) {
//                redisDao.lPush("c","c " + System.currentTimeMillis());
//                try {
//                    Thread.sleep(1000l);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }
}
