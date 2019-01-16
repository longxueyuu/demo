package designpattern.iterator;

public interface Collection<T> {
	void add(T t);
	T get(int i);
	int size();
	Iterator<T> iterator();
}
