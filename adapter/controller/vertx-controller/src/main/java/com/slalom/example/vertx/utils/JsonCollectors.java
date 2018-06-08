package com.slalom.example.vertx.utils;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class JsonCollectors {

	public static Collector<JsonObject, List<JsonObject>, JsonArray> toJsonArray() {
		return new Collector<>() {

			@Override
			public Supplier<List<JsonObject>> supplier() {
				return ArrayList::new;
			}

			@Override
			public BiConsumer<List<JsonObject>, JsonObject> accumulator() {
				return List::add;
			}

			@Override
			public BinaryOperator<List<JsonObject>> combiner() {
				return (left, right) -> {
					left.addAll(right);
					return left;
				};
			}

			@Override
			public Function<List<JsonObject>, JsonArray> finisher() {
				return JsonArray::new;
			}

			@Override
			public Set<Characteristics> characteristics() {
				return EnumSet.of(Characteristics.UNORDERED);
			}
		};
	}

	private JsonCollectors() {
	}
}
