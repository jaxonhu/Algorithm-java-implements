package designpattern;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/7
 * @Time: 上午11:51
 * @Project: Algorithm-Java-implements
 */

class Apple {

}

class Banana {

}

public class Factory {

    /**
     * 设计模式之：工厂模式
     * - 简单工厂
     * - 工厂方法
     * - 抽象工厂
     */


    /**
     * 简单工厂：
     */

    class SimpleFactor {

        public Apple createApple(int para1, int para2) {
            return new Apple();
        }

        public Banana createBanana(int para1, int para2) {
            return new Banana();
        }
    }

    /**
     * 抽象工厂
     */


}
