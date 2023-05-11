# Dependency Injection

## DI(Dependency Injection)

- 개념
  - 외부에서 객체를 만들어 따로 주입하는 것
- 목적
  - 유연성 있는 프로그램을 만들기 위함
- 예제 코드

~~~ java

/* 생성자에 객체 주입*/
public class ElectronicCarToy {

    private Battery battery;

    public ElectronicCarToy() {
        battery = new NormalBattery();
    }
}

/* setter를 이용해 주입 */
public class ElectronicRobotToy {

    private Battery battery;

    public ElectronicRobotToy(Battery battery) {
        this.battery = battery;
    }


    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}

/* 생성자와 setter를 이용해 주입 */
public class ElectronicRadioToy {

    private Battery battery;

    public ElectronicRadioToy(Battery battery) {
        this.battery = battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

}


~~~

## 스프링 DI 설정 방법


