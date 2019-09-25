package com.example.apitest;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public interface ApiEvent {
    Class DATA = DataEvent.class;
    Class SUCCESS = SuccessEvent.class;

    Disposable on(Class event, Consumer<? extends Event> next);

    void onReceive(Event event);

}
