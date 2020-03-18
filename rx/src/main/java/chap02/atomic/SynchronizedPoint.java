package chap02.atomic;

import java.util.ArrayList;

public class SynchronizedPoint {
    private final Object lock = new Object();

    private int x;
    private int y;

    void rightUp() {
        // synchronized 구문으로 감싼 메서드는 여러 스레드에서 동시에 접근 불가능하다.
        // synchronized 구문이 빠져나가면 값이 메모리에 업데이트 되기때문에 AtomicInteger 또는 volatile 등의 구문이 필요없다.
        // synchronized 구문은 무거운 처리이기 때문에 과도한 사용은 성능에 영향을준다.
        synchronized (lock) {
            x++;
            y++;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
