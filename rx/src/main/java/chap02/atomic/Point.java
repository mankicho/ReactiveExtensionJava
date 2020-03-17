package chap02.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Point {
    private final AtomicInteger x = new AtomicInteger(0);
    private final AtomicInteger y = new AtomicInteger(0);

//    만약 어떤 스레드가 이 메서드에 접근해 x.incrementAndGet() 만 호출됬는데 접근하면? 변경되지못한 y 값을 가져가게 됨
    void rightUp(){
        x.incrementAndGet();
        y.incrementAndGet();
    }


    public AtomicInteger getX() {
        return x;
    }

    public AtomicInteger getY() {
        return y;
    }
}
