package io.github.wyparks2.rxjava.chapter02.subjects;

import io.reactivex.subjects.PublishSubject;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;

public class PublishSubjectExample {
	public void marbleDiagram() { 
		PublishSubject<String> subject = PublishSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext(BLUE);
		subject.onComplete();
	}
	
	public static void main(String[] args) { 
		PublishSubjectExample demo = new PublishSubjectExample();
		demo.marbleDiagram();
	}
}
