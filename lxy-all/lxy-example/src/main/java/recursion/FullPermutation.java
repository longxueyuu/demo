package recursion;

/**
 * This class is used to print the full permutation of an array.
 * 
 * This class includes two methods, one is private, and another is public, <br>
 * the private method named swap() is invoked by the public method named getFullPermutation(),<br>
 * and to swap position of two element of the array, the method getFullPermutation()is to get the <br>
 * full permutation and print them on the console.
 * 
 * @author lxy
 * @version 1.0, 08/28/2014
 * @since JDK1.6
 */
public class FullPermutation {
	/**
	 * used to swap the position of two element of the given array.
	 * 
	 * this method is invoked by the getFullPermutation(), you can not use it.
	 * 
	 * @param array the target array need to swap the element
	 * @param position1 the position of the first element need to swap
	 * @param position2 the position of the second element need to swap
	 */
	private void  swap(Object[] array,int position1, int position2){
	    Object temp = array[position1];
	    array[position1] =array[position2] ;
	    array[position2] = temp;
	}
	/**
	 * used to get the full permutation and print them on the console.
	 * 
	 * this method is used to get the full permutation and print them on the console.
	 * 
	 * @param array the target array that need to get full permutation
	 * @param k the numbers of elements that the current layer of recursion  <br>
	 * of the full permutation
	 * @param n the array length
	 */
	public void getFullPermutation(Object[] array, int k, int n){
		int i;
	    if(k == 1){
	        String str = "";
	       for(i = 0; i < n; i++){
	            str += array[i];
	       }
	       System.out.println(str);
	     }else{
	         for(i = n - k; i < n; i++){
	        	 // ������Ԫ�أ��Կ�ʼ��һ��ݹ�
	            swap(array,i, n-k);
	            getFullPermutation(array, k-1, n);
	            // �ָ��ϴν������Կ�ʼ��һ�ν��� i+1 �� n-k ��λ�õ�Ԫ��
	            swap(array, i, n-k);
	         }
	     }
	}

}
