package io.github.wyparks2.rxjava.chapter04.create;

import static io.github.wyparks2.rxjava.common.Shape.BLUE;
import static io.github.wyparks2.rxjava.common.Shape.GREEN;
import static io.github.wyparks2.rxjava.common.Shape.RED;
import static io.github.wyparks2.rxjava.common.Shape.PUPPLE;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

import io.github.wyparks2.rxjava.common.CommonUtils;
import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.MarbleDiagram;
import io.github.wyparks2.rxjava.common.Shape;

import io.reactivex.Observable;


public class DeferExample implements MarbleDiagram{
	Iterator<String> colors = Arrays.asList(RED, GREEN, BLUE, PUPPLE).iterator();
	
	@Override
	public void marbleDiagram() { 
		Callable<Observable<String>> supplier = () -> getObservable();		
		Observable<String> source = Observable.defer(supplier);
		
		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		CommonUtils.exampleComplete();
	}

	//번호가 적인 도형을 발행하는 Observable을 생성합니다.
	private Observable<String> getObservable() { 
		if (colors.hasNext()) { 
			String color = colors.next();
			return Observable.just(
				Shape.getString(color, Shape.BALL), 
				Shape.getString(color, Shape.RECTANGLE), 
				Shape.getString(color, Shape.PENTAGON)); 			
		}
		
		return Observable.empty();		
	}
	
	public void notDeferred() { 
		Observable<String> source = getObservable();

		source.subscribe(val -> Log.i("Subscriber #1:" + val));
		source.subscribe(val -> Log.i("Subscriber #2:" + val));
		CommonUtils.exampleComplete();		
	}
	
	public static void main(String[] args) { 
		DeferExample demo = new DeferExample();
		demo.marbleDiagram();
		demo.notDeferred();
	}
}
