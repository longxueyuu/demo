package designpattern.iterator;

public class ConcreteIterator<T> implements Iterator<T> {
	private Collection<T> collection;
	private int size = 0;
	private int index = 0;
	ConcreteIterator(Collection<T> c)
	{
		this.collection = c;
		this.size = c.size();
		this.index = 0;
	}
	@Override
	public Boolean hastNext() {
		
		return (index) < size;
	}

	@Override
	public T next() {
		if(index >= size)
		{
			return null;
		}
		return collection.get(index++);
	}
}
