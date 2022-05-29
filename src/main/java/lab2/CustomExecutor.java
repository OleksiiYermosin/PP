package lab2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomExecutor {

    private final ThreadSafeQueue queue;

    private final AtomicBoolean isStopped = new AtomicBoolean();

    public CustomExecutor(int maxThreadSize) {
        WorkerThread[] workers = new WorkerThread[maxThreadSize];
        queue = new ThreadSafeQueue(maxThreadSize, isStopped);
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new WorkerThread(queue);
            workers[i].start();
        }
    }

    public <A> FutureResult<?> execute(Function<A, ?> function, A arg){
        if (isStopped.get()) {
            throw new RejectedExecutionException();
        }
        FutureResult<?> futureResult = new FutureResult<>();
        try {
            queue.append(new WorkItem<>(function, arg, futureResult));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return futureResult;
    }

    public <A> List<FutureResult<?>> map(Function<A, ?> function, A[] args) {
        return Arrays.stream(args)
                .map(arg -> execute(function, arg))
                .collect(Collectors.toList());
    }

    public void shutdown() {
        isStopped.set(true);
    }
}
