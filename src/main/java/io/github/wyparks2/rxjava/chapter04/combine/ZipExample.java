package io.github.wyparks2.rxjava.chapter04.combine;

import java.util.concurrent.TimeUnit;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;
import io.github.wyparks2.rxjava.common.Shape;

import io.reactivex.Observable;

import static io.github.wyparks2.rxjava.common.Shape.YELLOW;
import static io.github.wyparks2.rxjava.common.Shape.PUPPLE;
import static io.github.wyparks2.rxjava.common.Shape.SKY;
import static io.github.wyparks2.rxjava.common.Shape.BALL;
import static io.github.wyparks2.rxjava.common.Shape.PENTAGON;
import static io.github.wyparks2.rxjava.common.Shape.STAR;

import static io.github.wyparks2.rxjava.common.Shape.triangle;


public class ZipExample implements MarbleDiagram{
	@Override
	public void marbleDiagram(){
		String[] shapes = {BALL, PENTAGON, STAR};
		String[] coloredTriangles = {triangle(YELLOW), triangle(PUPPLE), triangle(SKY)};
		
		Observable<String> source = Observable.zip(
			Observable.fromArray(shapes)
					.map(Shape::getSuffix), 
			Observable.fromArray(coloredTriangles)
					.map(Shape::getColor),
			(suffix, color) -> color + suffix); 
		
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public void zipNumbers() {
		Observable<Integer> source = Observable.zip(
			Observable.just(100, 200, 300),
			Observable.just(10, 20, 30),
			Observable.just(1, 2, 3),
			(a, b, c) -> a + b + c );
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}

	public void zipInterval() { 
		Observable<String> source = Observable.zip(
				Observable.just("RED", "GREEN", "BLUE"),
				Observable.interval(200L, TimeUnit.MILLISECONDS),
				(value,i) -> value);		
		
		CommonUtils.exampleStart();
		source.subscribe(Log::it);			
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();		
	}	
	
	public void zipWithNumbers() {
		Observable<Integer> source = Observable.zip(
			Observable.just(100, 200, 300),
			Observable.just(10, 20, 30), 
			(a, b) -> a + b)
			.zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
		source.subscribe(Log::i);
		CommonUtils.exampleComplete();
	}
	
	public static void main(String[] args) { 
		ZipExample demo = new ZipExample();
		demo.marbleDiagram();
		demo.zipNumbers();
		demo.zipInterval();
		demo.zipWithNumbers();
	}
}
