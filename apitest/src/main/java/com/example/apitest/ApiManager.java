package com.example.apitest;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class ApiManager implements ApiEvent {

    private static ApiManager apiManager;
    private PublishSubject<Event> subjects;

    private ApiManager() {
        subjects = PublishSubject.create();
    }

    public static ApiManager on() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }


    @Override
    public Disposable on(Class event, Consumer<? extends Event> next) {
        return subjects.ofType(event).subscribe(next);
    }

    @Override
    public void onReceive(Event event) {
        new Thread(() -> subjects.onNext(event)).start();

    }

    public void sendData(int data) {
        DataEvent event = new DataEvent();
        event.value = data;
        onReceive(event);
    }
}
