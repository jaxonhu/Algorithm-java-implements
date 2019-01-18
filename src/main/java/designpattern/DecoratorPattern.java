package designpattern;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/7
 * @Time: 下午3:54
 * @Project: Algorithm-Java-implements
 */

interface ICar {
    public void move();
}

public class DecoratorPattern {

    /**
     * 装饰器模式
     */

    public static void main(String[] args) {
        Car acar = new Car();

        SuperCar supercar = new SuperCar(acar);

    }
}


class Car implements ICar {
    @Override
    public void move() {
        System.out.println("陆地上跑");
    }
}

class SuperCar implements ICar {

    //这里是不是要复习一下向上转型？？
    private ICar car;

    public SuperCar(ICar car) {
        this.car = car;
    }

    @Override
    public void move() {
        car.move();
    }
}

class FlyCar extends  SuperCar {

    public FlyCar(ICar car) {
        super(car);
    }

    @Override
    public void move() {
        super.move();
    }

    public void fly() {
        System.out.println("天上飞");
    }
}

class WaterCar extends SuperCar {

    public WaterCar(ICar car) {
        super(car);
    }

    @Override
    public void move() {
        super.move();
    }

    public void swim() {
        System.out.println("水里游");
    }
}

