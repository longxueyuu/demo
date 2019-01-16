package datastructure.main;

public class EntryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Entry[] table = new Entry[10];
		for(Entry entry : table)
		{
			System.out.println(entry);
		}
		
	}

	public class Entry<K,V>{
		int hash;
		K key;
		V value;
		Entry<K,V> e;
	}
}
