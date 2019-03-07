package io.github.wyparks2.rxjava.chapter08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.wyparks2.rxjava.common.Log;
import io.github.wyparks2.rxjava.common.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import io.reactivex.Observable;

@RunWith(JUnitPlatform.class)
public class JUnit5Basic {
	@DisplayName("JUnit5 First Example")
	@Test 
	void testFirst() { 
		int expected = 3; 
		int actual = 1 + 2;
		assertEquals(expected, actual);
	}
	
	@DisplayName("test getShape() Observable")
	@Test
	void testGetShapeObservable() { 
		String[] data = {Shape.RED, Shape.rectangle(Shape.YELLOW), Shape.triangle(Shape.YELLOW)};
		Observable<String> source = Observable.fromArray(data)
				.map(Shape::getShape);
		
		String[] expected = {Shape.BALL, Shape.RECTANGLE, Shape.TRIANGLE};
		List<String> actual = new ArrayList<>();
		source.doOnNext(Log::d)
		.subscribe(actual::add);
		
		assertEquals(Arrays.asList(expected), actual);
	}
}
