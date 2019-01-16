package datastructure.node;

public class TreeNode<T> {
	public T data;
	public TreeNode<T> lChild;
	public TreeNode<T> rChild;
	public int lBit,rBit; // 线索化标志， 取值为0或1，为0表示该指针为线索，为1表示指针指向孩子
}
