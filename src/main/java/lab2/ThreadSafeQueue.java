package lab2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeQueue {

    private final Queue<WorkItem<?>> queue;

    private final int maxSize;

    private final Lock lock;

    private final Condition notEmptyCondition;

    private final Condition notFullCondition;

    private final AtomicBoolean isStopped;

    public ThreadSafeQueue(int maxSize, AtomicBoolean isStopped) {
        this.maxSize = maxSize;
        this.isStopped = isStopped;
        queue = new LinkedList<>();
        lock = new ReentrantLock();
        notEmptyCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    public void append(WorkItem<?> item) throws InterruptedException {
        try {
            lock.lock();
            while (queue.size() >= maxSize) {
                notFullCondition.await();
            }
            queue.add(item);
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public WorkItem<?> pop() throws InterruptedException {
        try {
            lock.lock();
            while (!isStopped.get() && queue.size() == 0) {
                notEmptyCondition.awaitNanos(30);
            }
            notFullCondition.signal();
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }

    public int size(){
        return queue.size();
    }

}
