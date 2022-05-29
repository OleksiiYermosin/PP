package lab2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FutureResult<R> {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public boolean hasResult;

    private R result;

    public void setResult(R result) {
        try {
            lock.lock();
            this.result = result;
            condition.signal();
            hasResult = true;
        } finally {
            lock.unlock();
        }
    }

    public R getResult() throws InterruptedException {
        try {
            lock.lock();
            if (!hasResult) {
                condition.await();
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

}
