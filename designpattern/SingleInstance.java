package designpattern;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/7
 * @Time: 上午10:47
 * @Project: Algorithm-Java-implements
 */
public class SingleInstance {

    /**
     *  单例模式 ： 懒汉模式  饿汉模式
     */




}

/**
 *  先写懒汉模式，懒汉模式指单例仅在第一次被使用时创建
 */
class Singleton {

    private static volatile Singleton instance;
    private Singleton() {};
    //简单实现，这种方式线程不安全, 两个线程可能同时通过 instance == null 的条件判断，各自创建实例，破坏单例模式
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    //可以用过加锁解决,但是其余线程会阻塞在判断单例是否为空，造成性能低下
    public static Singleton getInstance2() {
        synchronized (Singleton.class) {
            if(instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }

    //双重判断，对加锁版本进行多线程下的优化
    public static Singleton getInstance3() {
        if(instance == null) {
            synchronized (Singleton.instance) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    //但是还是可能有问题，指令重排，给instance用volatile关键字声明
}


/**
 *  饿汉模式，单例在全局声明时就创建，缺点是在类加载阶段就创建对象实例可能造成资源浪费或者数据依赖没有准备好的问题。
 */

class Singleton2 {
    private static final Singleton2 instance;
    private Singleton2() {};

    static {
        instance = new Singleton2();
    }

    public Singleton2 getInstance() {
        return instance;
    }
}


/**
 * 还有一种静态内部类的写法，利用类加载机制保证同步，因为静态内部类在JVM中只会维持一份实例，而且内部是一个饿汉模式的单例实现
 * 类加载的时机：
 *
 */

class Singleton3 {

    private Singleton3() {};
    private static class SingletonHolder {
        private static Singleton3 instance = new Singleton3();
        public static Singleton3 getInstance() {
            return instance;
        }
    }
    public static Singleton3 getInstance() {
        return SingletonHolder.getInstance();
    }
}

/**
 * 还有更简单的写法，利用枚举类型构造器私有的特性，而且枚举类中的枚举变量在编译时会被声明为静态变量和静态方法
 */

enum Singleton4 {
    SINGLETON_4;
    public void dosomething() {

    }
}

