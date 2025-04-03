package io.github.imecuadorian.calculator.model;

import lombok.*;

@Getter
@Setter
public class Generic<T, S> {
    private T t1;
    private T t2;
    private S s1;
    private S s2;
    private T[] array;

    public Generic() {
        this(null, null, null, null);
    }

    public Generic(T t1) {
        this(t1, null, null, null);
    }

    public Generic(T t1, S s1) {
        this(t1, null, s1, null);
    }

    public Generic(T t1, T t2, S s1) {
        this(t1, t2, s1, null);
    }

    public Generic(T t1, T t2, S s1, S s2) {
        super();
        this.t1 = t1;
        this.t2 = t2;
        this.s1 = s1;
        this.s2 = s2;
    }
}
