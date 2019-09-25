package com.example.apitest;

import com.example.apitest.exception.EthereumIdNotGeneratedException;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import kotlin.reflect.KFunction;

public interface ApiEvent {
    Class DATA = DataEvent.class;
    Class SUCCESS = SuccessEvent.class;

    Disposable on(Class event, Consumer<? extends Event> next) throws EthereumIdNotGeneratedException;

    void onReceive(Event event);
}
