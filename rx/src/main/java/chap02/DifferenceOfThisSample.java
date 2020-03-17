package chap02;


import io.reactivex.functions.Action;

public class DifferenceOfThisSample {
    public static void main(String[] args) throws Exception {
        DifferenceOfThisSample sample = new DifferenceOfThisSample();
        sample.execute();
    }

    public void execute() throws Exception {
        // 익명클래스
        Action anonymous = new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("익명 클래스의 this = " + this); // 익명 클래스의 this 는 구현한 인터페이스의 인스턴스, 즉 anonymous 를 나타낸다
            }
        };
        // 람다식
        Action lambda = () -> System.out.println("람다식의 this = " + this); // 람다식의 this 는 람다식을 구현한 클래스의 인스턴스, 즉 DifferenceOfThisSample 을 나타냄

        // 각각 실행
        anonymous.run();
        lambda.run();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
