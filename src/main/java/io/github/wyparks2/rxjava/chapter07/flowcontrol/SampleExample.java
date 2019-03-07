package io.github.wyparks2.rxjava.chapter07.flowcontrol;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.github.wyparks2.rxjava.common.Shape;
import io.reactivex.Observable;

public class SampleExample implements MarbleDiagram {
	@Override
	public void marbleDiagram() {
		String[] data = {Shape.RED, Shape.ORANGE, Shape.YELLOW, Shape.GREEN, Shape.PUPPLE};
		
		CommonUtils.exampleStart();
		//앞의 4개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(4)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 데이터는 300ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//두개의 옵저버블을 결합하고 300ms으로 샘플링 
		Observable<String> source = Observable.concat(
				earlySource, 
				lateSource)
				.sample(300L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public void emitLast() {
		String[] data = {Shape.RED, Shape.ORANGE, Shape.YELLOW, Shape.GREEN, Shape.PUPPLE};
		
		CommonUtils.exampleStart();
		//앞의 4개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(4)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 데이터는 300ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//두개의 옵저버블을 결합하고 300ms으로 샘플링 (emitLast = true)
		Observable<String> source = Observable.concat(
				earlySource, 
				lateSource)
				.sample(300L, TimeUnit.MILLISECONDS, true);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		SampleExample demo = new SampleExample();
		demo.marbleDiagram();
//		demo.emitLast();
	}
}
