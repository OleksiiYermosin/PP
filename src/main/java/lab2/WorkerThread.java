package lab2;

@SuppressWarnings("unchecked")
public class WorkerThread extends Thread {

    private final ThreadSafeQueue queue;

    public WorkerThread(ThreadSafeQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            WorkItem item = null;
            try {
                item = queue.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (item == null) {
                return;
            }
            item.getFuture().setResult(item.getFunction().apply(item.getArgs()));
        }
    }
}
