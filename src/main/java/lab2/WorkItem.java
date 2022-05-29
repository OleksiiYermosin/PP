package lab2;

import java.util.function.Function;

public class WorkItem<A> {

    private final Function<A, ?> function;

    private final A args;

    private final FutureResult<?> future;

    public WorkItem(Function<A, ?> function, A args, FutureResult<?> future) {
        this.function = function;
        this.args = args;
        this.future = future;
    }

    public Function<A, ?> getFunction() {
        return function;
    }

    public A getArgs() {
        return args;
    }

    public FutureResult<?> getFuture() {
        return future;
    }
}
