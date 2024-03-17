package com.bkool.port.usecase;

@FunctionalInterface
public interface UseCase<T, A> {

    T execute(A params);
}
