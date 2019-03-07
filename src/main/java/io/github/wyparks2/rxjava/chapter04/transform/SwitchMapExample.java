package io.github.wyparks2.rxjava.chapter04.transform;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.reactivex.Observable;

import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.BLUE;

public class SwitchMapExample implements MarbleDiagram {
	public void marbleDiagram() { 
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출
		
		String[] balls = {RED, GREEN, BLUE};
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls[idx])
				.take(balls.length)
				.switchMap(
					ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
									.map(notUsed -> ball + "<>")
									.take(2));
		source.subscribe(Log::it);
		CommonUtils.sleep(2000);
		CommonUtils.exampleComplete();
	}
	
	public void usingDoOnNext() { 
		CommonUtils.exampleStart(); //시간을 측정하기 위해 호출
		
		String[] balls = {RED, GREEN, BLUE};
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls[idx])
				.take(balls.length)
				.doOnNext(Log::dt)  //중간결과 확인용 
				.switchMap(
					ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
									.map(noValue -> ball + "<>")
									.take(2));
		source.subscribe(Log::it);
		CommonUtils.sleep(2000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SwitchMapExample demo = new SwitchMapExample();
		demo.marbleDiagram();
		demo.usingDoOnNext();
	}
}
