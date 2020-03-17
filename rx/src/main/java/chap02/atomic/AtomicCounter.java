package chap02.atomic;

import java.util.concurrent.atomic.AtomicInteger;

// 원자성을 보장하는 객체
public class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger(0);

    void increment() {
        count.incrementAndGet();
    }

    int get() {
        return count.get();
    }
}
