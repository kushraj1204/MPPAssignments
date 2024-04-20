package prob7b;

@FunctionalInterface
public interface QuadruFunction<S,T,U,V,R> {
	R apply(S s, T t, U u,V v);
}
