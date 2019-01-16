package datastructure.tree;

import datastructure.node.TreeNode;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class ThreadBinaryTree<T> extends BinaryTree<T> {
	private TreeNode<T> previous = null; //中序线索化二叉树用到的全局变量，该变量始终指向中序遍历当前访问结点的前驱节点
	
	/**
	 * 返回中序线索二叉树给定结点的前驱节点
	 * 
	 * @param x
	 * @return
	 */
	private TreeNode<T> inPrior(TreeNode<T> x)
	{
		TreeNode<T> s = null;
		s = x.lChild;
		if(x.lBit == 1)
		{
			while(s.rBit == 1)
			{
				s = s.rChild;
			}
		}
		return s;
	}
	
	/**
	 * 返回中序线索二叉树给定节点的后继节点
	 * 
	 * @param x
	 * @return
	 */
	private TreeNode<T> inSucc(TreeNode<T> x)
	{
		TreeNode<T> s = null;
		s = x.rChild;
		if(x.rBit == 1)
		{
			while(s.lBit == 1)
			{
				s = s.lChild;
			}
		}
		
		return s;
	}
	
	/**
	 * 中序线索化递归算法
	 * 
	 * @param root
	 */
	private void inThread(TreeNode<T> root)
	{
		TreeNode<T> p = root;
		if(p != null)
		{
			inThread(p.lChild);
			if(p.lChild == null) // 如果当前节点的左孩子为空，则当前节点的左指针标志置为0即线索，左指针域指向当前节点的前驱节点
			{
				p.lBit = 0;
				p.lChild = previous;
			}
			if(p.rChild == null) // 如果当前节点的右孩子为空，则当前节点的右指针标志置为0即线索
			{
				p.rBit = 0;
			}
			if(previous.rBit == 0) // 如果当前节点的前驱节点的右指针标志为0即线索，则前驱节点的右指针域指向当前节点
			{
				previous.rChild = p;
			}
			previous = p; // 将当前节点置为前驱节点，以进行下一个节点的线索化
			inThread(p.rChild);
			
		}
	}
	
	/**
	 * 中序线索化二叉树
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode<T> inorderThreadBTree(TreeNode<T> root)
	{
		previous = null;
		TreeNode<T> head = new TreeNode<T>();
		head.lBit = 1;
		head.rBit = 0;
		head.lChild = null;
		head.rChild = null;
		previous = head; // 前驱节点置为头结点
		inThread(root); // 线索化二叉树
		previous.rChild = head; // 将中序遍历的最后一个节点的后继节点置为头结点
		head.rChild = head; // 将头结点的右指针指向自己
		head.lChild = root; // 将头结点的左指针指向树的根节点
		head.rBit = 1; // 将头结点的右指针标志置为1
		return head;
	}
	
	/**
	 * 利用中序线索二叉树进行 中序遍历
	 * 
	 * @param head
	 */
	public void inorderByThreadBTree(TreeNode<T> head)
	{
		TreeNode<T> p = head;
		
		while(true)
		{
			p = inSucc(p);
			if(p == head)
			{
				break;
			}
			System.out.print(p.data);
		}
		
		System.out.print("\n");
		while(true) // 中序遍历的逆序输出
		{
			p = inPrior(p);
			if(p == head)
			{
				break;
			}
			System.out.print(p.data);
		}
	}
}
