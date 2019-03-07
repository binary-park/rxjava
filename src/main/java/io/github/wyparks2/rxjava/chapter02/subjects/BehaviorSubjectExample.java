package io.github.wyparks2.rxjava.chapter02.subjects;

import io.github.wyparks2.rxjava.common.CommonUtils;

import io.reactivex.subjects.BehaviorSubject;

import static io.github.wyparks2.rxjava.common.Shape.PUPPLE;
import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;

public class BehaviorSubjectExample {
	public void marbleDiagram() { 
		BehaviorSubject<String> subject = BehaviorSubject.createDefault(PUPPLE);
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext(RED);
		subject.onNext(GREEN);
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext(BLUE);
		subject.onComplete();
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		BehaviorSubjectExample demo = new BehaviorSubjectExample();
		demo.marbleDiagram();
	}
}
