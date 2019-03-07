package io.github.wyparks2.rxjava.chapter07.flowcontrol;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;

import io.github.wyparks2.rxjava.common.Shape;
import io.reactivex.Observable;

public class BufferExample implements MarbleDiagram {
	@Override
	public void marbleDiagram() {
		String[] data = {Shape.RED, Shape.YELLOW, Shape.GREEN, Shape.SKY, Shape.BLUE, Shape.PUPPLE};
		CommonUtils.exampleStart();
		
		//앞의 3개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(3)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//가운데 1개는 300ms 후에 발행 
		Observable<String> middleSource = Observable.just(data[3])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 2개는 100ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//3개씩 모아서 한번에 발행함  
		Observable<List<String>> source = Observable.concat(
				earlySource,
				middleSource,
				lateSource)
				.buffer(3);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public void bufferSkip() { 
		String[] data = {Shape.RED, Shape.YELLOW, Shape.GREEN, Shape.SKY, Shape.BLUE, Shape.PUPPLE};
		CommonUtils.exampleStart();
		
		//앞의 3개는 100ms 간격으로 발행 
		Observable<String> earlySource = Observable.fromArray(data)
				.take(3)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//가운데 1개는 300ms 후에 발행 
		Observable<String> middleSource = Observable.just(data[3])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//마지막 2개는 100ms 후에 발행 
		Observable<String> lateSource = Observable.just(data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);
		
		//2는 모으고 1개는 skip함   
		Observable<List<String>> source = Observable.concat(
				earlySource,
				middleSource,
				lateSource)
				.buffer(2,3);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		BufferExample demo = new BufferExample();
		demo.marbleDiagram();
//		demo.bufferSkip();
	}
}

