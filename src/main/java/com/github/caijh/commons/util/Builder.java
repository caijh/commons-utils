package com.github.caijh.commons.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Builder<T> {

    private final Supplier<T> supplier;
    private final List<Consumer<T>> modifiers = new ArrayList<>();

    private Builder(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Builder<T> of(Supplier<T> supplier) {
        return new Builder<>(supplier);
    }

    public <V> Builder<T> with(BuilderConsumer<T, V> consumer, V v) {
        Consumer<T> c = t -> consumer.accept(t, v);
        modifiers.add(c);
        return this;
    }

    public T build() {
        T value = supplier.get();
        modifiers.forEach(modifier -> modifier.accept(value));
        modifiers.clear();
        return value;
    }

    @FunctionalInterface
    public interface BuilderConsumer<T, V> {

        void accept(T t, V v);

    }

}
