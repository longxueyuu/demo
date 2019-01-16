package datastructure.tree;

import java.util.Scanner;

import datastructure.node.TreeNode;
import datastructure.queue.Queue;
import datastructure.stack.Stack;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 */
public class BinaryTree<T> {
	
	
	private static final int STACK_LENGTH = 1000;
	/**
	 * 创建二叉树（by myself）
	 * 该创建算法仅适用于创建结点数据类型为char的树
	 * 
	 * @param str
	 * @return
	 */
	public TreeNode<Character> createTree(String str)
	{
		TreeNode<Character> root = null;
		TreeNode<Character> p = null;
		char[] nodeSquence = str.toCharArray();
		Object[] nodeStack = new Object[STACK_LENGTH];
		int flag = -1;
		int k = 0;
		
		if(nodeSquence.length < 1)
		{
			return null;
		}else{
			for(int i = 0; i < nodeSquence.length; i++)
			{
				switch(nodeSquence[i])
				{
				case '@': return root;
				case '(': 
					p = ((TreeNode<Character>)nodeStack[k++]);
					flag = 1;
					break;
				case ')':	
					break;
				case ',':		
					p = ((TreeNode<Character>)nodeStack[--k]);
					flag = 0;
					break;
				default:
					nodeStack[k] = new TreeNode<T>();
					((TreeNode<Character>)nodeStack[k]).data = nodeSquence[i];
					((TreeNode<Character>)nodeStack[k]).lBit = 1;
					((TreeNode<Character>)nodeStack[k]).rBit = 1;
					((TreeNode<Character>)nodeStack[k]).lChild = null;
					((TreeNode<Character>)nodeStack[k]).rChild = null;
					if(root == null)
					{
						root = ((TreeNode<Character>)nodeStack[k]);
						
					}
					if(flag == 1)
					{
						p.lChild = ((TreeNode<Character>)nodeStack[k]);
					}
					if(flag == 0)
					{
						p.rChild = ((TreeNode<Character>)nodeStack[k]);
					}	
				}			
			}
			return root;
		}
		
	}
	
	/**
	 * 创建二叉树（from book）
	 * 该创建算法仅适用于创建结点数据类型为char的树
	 * @param str
	 * @return
	 */
	public TreeNode<Character> createTree2(String str)
	{
		TreeNode<Character> root = null;
		TreeNode<Character> p = null;
		char[] nodeSquence = str.toCharArray();
		Object[] nodeStack = new Object[STACK_LENGTH];
		int flag = -1;
		int top = -1;
		if(nodeSquence.length < 1)
		{
			return null;
		}else{
			for(int i = 0; i < nodeSquence.length; i++)
			{
				switch(nodeSquence[i])
				{
				case '@': return root;
				case '(': 
					nodeStack[++top] = p;
					flag = 1;
					break;
				case ')':		
					top--;
					break;
				case ',':
					flag = 0;
					break;
				default:
					p = new TreeNode<Character>();
					p.data = nodeSquence[i];
					p.lBit = 1;
					p.rBit = 1;
					p.lChild = null;
					p.rChild = null;
					if(root == null)
					{
						root = p;
					}
					if(flag == 1)
					{
						((TreeNode<Character>)nodeStack[top]).lChild = p;
					}
					if(flag == 0)
					{
						((TreeNode<Character>)nodeStack[top]).rChild = p;
					}	
				}			
			}
			return root;
		}
		
	}
	
	/**
	 *  每次输入单个字符方式 创建二叉树
	 * 该创建算法仅适用于创建结点数据类型为char的树
	 * 该算法与 二叉树的copy算法copyBTree(TreeNode<T> root)基本一致
	 * 
	 * @param root
	 * @return
	 */
	protected TreeNode<Character> createTreeByPreorder(TreeNode root)
	{
		char ch;
		Scanner in = new Scanner(System.in); // 从键盘读入字符
		ch = (char)in.next().charAt(0);
		//System.out.print("字符：" + ch);
		if(ch == '*')// 若输入的字符为 * ，则该节点为空节点
		{
			root = null;
		}
		else{
			root = new TreeNode<Character>();
			root.data = ch;
			root.lChild = createTreeByPreorder(root.lChild);
			root.rChild = createTreeByPreorder(root.rChild);
		}
		return root;
	}
	
	/**
	 * 前序遍历递归算法
	 * 
	 * @param root
	 */
	public void preorder(TreeNode<T> root)
	{
		if(root == null)
		{
			return;
		}
		System.out.print(root.data);
		preorder(root.lChild);
		preorder(root.rChild);
	}
	
	/**
	 * 前序遍历非递归算法
	 * 
	 * @param root
	 */
	public void preorderNonRecursion(TreeNode<T> root)
	{
		if(root == null)
		{
			return;
		}
		TreeNode<T> p = root;
		Stack<TreeNode<T>> nodeStack = new Stack<TreeNode<T>>();
		while(p != null)
		{
			System.out.print(p.data); // 访问当前节点
			if(p.rChild != null) // 当前节点右孩子不为空，则右孩子进栈
			{
				nodeStack.push(p.rChild);		
			}
			if(p.lChild != null) // 当前节点左孩子不为空，将左孩子置为当前节点，否则栈内节点出栈
			{
				p = p.lChild;
			}else{
				p = nodeStack.pop();
			}
		}	
		
	}
	
	/**
	 * 后序遍历递归算法
	 * 
	 * @param root
	 */
	public void postorder(TreeNode<T> root)
	{
		if(root == null)
		{
			return;
		}
		postorder(root.lChild);
		postorder(root.rChild);
		System.out.print(root.data);
	}
	
	/**
	 * 后序遍历非递归算法
	 * 
	 * @param root
	 */
	public void postorderNonRecursion(TreeNode<T> root)
	{
		if(root == null)
		{
			return;
		}
		TreeNode<T> p = root;
		Stack<TreeNode<T>> nodeStack = new Stack<TreeNode<T>>();
		Stack<Integer> nodeFlagStack = new Stack<Integer>(); // 标志nodeStack中节点为第几次进栈
		while(p != null)
		{
			if(p.lChild != null)
			{
				nodeStack.push(p);
				nodeFlagStack.push(1);
				p = p.lChild;
			}
			else{
				if(p.rChild != null)
				{
					nodeStack.push(p); // 若当前节点的左孩子为空且右孩子不为空，那么当前节点进栈，并将进栈标志设为第二次进栈
					nodeFlagStack.push(2); 
					p = p.rChild;
				}
				else{
					System.out.print(p.data); // 当前节点左右孩子都为空，则访问该节点
					int flag = -1;
					p = nodeStack.pop();
					if(p != null)
					{
						flag = nodeFlagStack.pop().intValue();
						// 出栈循环
						while(p != null)
						{
							if(flag == 2 || (flag == 1 && p.rChild == null))
							{	
								System.out.print(p.data);
								p = nodeStack.pop();
								if(p == null)
								{
									break;
								}
								flag = nodeFlagStack.pop().intValue();
							}
							if(flag ==1 && p != null && p.rChild != null) // 当弹出节点的右孩子不为空时，弹出节点第二次进栈，并将右孩子置为当前节点，并跳出当前循环
							{
								nodeStack.push(p);
								nodeFlagStack.push(2);
								p = p.rChild;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 中序遍历递归算法
	 * 
	 * @param root
	 */
	public void inorder(TreeNode<T> root)
	{
		if(root == null)
		{
			return;
		}
		inorder(root.lChild);
		System.out.print(" " + root.data + " ");
		inorder(root.rChild);
		
	}
	
	/**
	 * 中序遍历非递归算法
	 * 
	 * @param root
	 */
	public void inorderNonRecursion(TreeNode<T> root)
	{
		TreeNode<T> p = root;
		Stack<TreeNode<T>> nodeStack = new Stack<TreeNode<T>>();
		
		while(p != null)
		{
			if(p.lChild != null) // 如果当前节点左孩子不为空， 则当前节点进栈，否则访问当前节点，并将左孩子置为当前节点
			{
				nodeStack.push(p);
				p = p.lChild;
			}else{
				System.out.print(p.data);
				if(p.rChild != null)
				{
					p = p.rChild;
				}else{
					p = nodeStack.pop();
					if(p != null)
					{
						System.out.print(p.data);
						// 出栈循环
						while(p.rChild == null) // 如果该节点右孩子为空 则继续弹出 直到栈为空或者 该节点右孩子不为空
						{
							p = nodeStack.pop();
							if(p == null)
							{
								break;
							}	
							System.out.print(p.data);
						}
						if(p != null && p.rChild != null) // 出栈的节点不为空且右孩子不为空 则将右孩子置为 当前节点
						{		
							p = p.rChild;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 层次遍历算法（非递归）
	 * 
	 * @param root
	 */
	public void layerOrder(TreeNode<T> root)
	{
		if(root == null)
		{
			return;
		}
		Queue<TreeNode<T>> nodeQueue = new Queue<TreeNode<T>>();
		nodeQueue.init();
		TreeNode<T> p = root;
		while(p != null)
		{
			System.out.print(" " + p.data + " ");
			if(p.lChild != null)
			{	
				nodeQueue.add(p.lChild);
				//System.out.print(" :" + p.lChild.data + ": ");
			}
			if(p.rChild != null)
			{
				nodeQueue.add(p.rChild);
				//System.out.print(" :" + p.rChild.data + ": ");
			}
			p = nodeQueue.deleteFront();
		}	
	}
	
	/**
	 * 交换二叉树每个节点的左右孩子
	 * 
	 * @param root
	 */
	public void exchangeBTreeLRChild(TreeNode<T> root)
	{
		if(root == null)
		{
			return;
		}
		Queue<TreeNode<T>> nodeQueue = new Queue<TreeNode<T>>();
		nodeQueue.init();
		TreeNode<T> p = root;
		while(p != null)
		{
			TreeNode<T> temp = null;
			temp = p.lChild;
			p.lChild = p.rChild;
			p.rChild = temp;
			if(p.lChild != null)
			{
				nodeQueue.add(p.lChild);
			}
			if(p.rChild != null)
			{
				nodeQueue.add(p.rChild);
			}
			p = nodeQueue.deleteFront();

		}
	}
	
	/**
	 * 树深度的递归算法
	 * 
	 * @param root
	 * @return
	 */
	public int depth(TreeNode<T> root)
	{
		int lDepth = 0, rDepth = 0;
		if(root == null)
		{
			return 0;
		}
		lDepth = depth(root.lChild) + 1;
		rDepth = depth(root.rChild) + 1;
		
		return lDepth > rDepth ? lDepth : rDepth ;
	}
	
	/**
	 *  复制二叉树
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode<T> copyBTree(TreeNode<T> root)
	{
		TreeNode<T> newRoot = null;
		if(root == null)
		{
			return null;
		}
		else{
			newRoot = new TreeNode<T>();
			newRoot.data = root.data;
			newRoot.lChild = copyBTree(root.lChild);
			newRoot.rChild = copyBTree(root.rChild);
			return newRoot;
		}
	}
	
	/**
	 * 返回数据域为 给定数据 的节点在树中的层次
	 * 
	 * @param root
	 * @param item
	 * @return
	 */
	public int getNodeLayer(TreeNode<T> root, T item)
	{
		if(root == null)
		{
			return -1;
		}
		TreeNode<T> p = root;
		Stack<TreeNode<T>> nodeStack = new Stack<TreeNode<T>>();
		Stack<Integer> nodeFlagStack = new Stack<Integer>(); // 标志nodeStack中节点为第几次进栈
		while(p != null)
		{
			if(p.lChild != null)
			{
				nodeStack.push(p);
				nodeFlagStack.push(1);
				p = p.lChild;
			}
			else{
				if(p.rChild != null)
				{
					nodeStack.push(p); // 若当前节点的左孩子为空且右孩子不为空，那么当前节点进栈，并将进栈标志设为第二次进栈
					nodeFlagStack.push(2); 
					p = p.rChild;
				}
				else{
					System.out.print(p.data); // 当前节点左右孩子都为空，则访问该节点
					if(p.data == item)
					{
						return nodeStack.size() + 1;
					}
					int flag = -1;
					p = nodeStack.pop();
					if(p != null)
					{
						flag = nodeFlagStack.pop().intValue();
						// 出栈循环
						while(p != null)
						{
							if(flag == 2 || (flag == 1 && p.rChild == null))
							{	
								System.out.print(p.data);
								if(p.data == item)
								{
									return nodeStack.size() + 1;
								}
								p = nodeStack.pop();
								if(p == null)
								{
									break;
								}
								flag = nodeFlagStack.pop().intValue();
							}
							if(flag ==1 && p != null && p.rChild != null) // 当弹出节点的右孩子不为空时，弹出节点第二次进栈，并将右孩子置为当前节点，并跳出当前循环
							{
								nodeStack.push(p);
								nodeFlagStack.push(2);
								p = p.rChild;
								break;
							}
						}
					}
				}
			}
		}
		return -1;
	}
	
	/**
	 * 获取叶节点数量
	 * 
	 * @param root
	 * @return
	 */
	public int getLeafNodesNumber(TreeNode<T> root)
	{
		int num = 0;
		if(root == null)
		{
			return 0;
		}
		if(root.rChild == null && root.lChild == null)
		{
			return 1;
		}
		num = getLeafNodesNumber(root.rChild) + getLeafNodesNumber(root.lChild);
		
		return num;
	}
	
	/**
	 * 测试两个二叉树是否等价
	 * 
	 * @param root1
	 * @param root2
	 * @return
	 */
	public Boolean equalBTree(TreeNode<T> root1, TreeNode<T> root2)
	{
		if(root1 == null && root2 == null)
		{
			return true;
		}
		if(root1 != null && root2 != null && root1.data == root2.data && equalBTree(root1.lChild,root2.lChild) && equalBTree(root1.rChild,root2.rChild))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 删除数据域为 给定数据 的节点及其子树，如果根节点数据域中的数据为给定的数据，那么该方法只能删除除根节点以外的节点
	 * 
	 * @param root
	 * @param item
	 */
	public void deleteNodeAndItsSubtree(TreeNode<T> root, T item)
	{
		if(root == null)
		{
			return;
		}
		if(root.data == item)
		{
			root.lChild = null;
			root.rChild = null;
			root = null;
			return;
		}
		TreeNode<T> p = root, q = null;
		Stack<TreeNode<T>> nodeStack = new Stack<TreeNode<T>>();
		while(p != null)
		{
			//System.out.print(p.data); // 访问当前节点
			if(p.data == item)
			{
				if(q.lChild == p)
				{
					q.lChild = null;
				}
				if(q.rChild == p)
				{
					q.rChild = null;
				}
				//delete(p);// 目的是释放p节点及其子树节点所占用的空间，不过java做不到这一点，即该句无效
				return;
			}
			if(p.rChild != null) // 当前节点右孩子不为空，则右孩子进栈,右孩子父节点进栈
			{
				
				nodeStack.push(p.rChild);
				nodeStack.push(p);
			}
			if(p.lChild != null) // 当前节点左孩子不为空，将左孩子置为当前节点，否则栈内节点出栈
			{
				q = p;
				p = p.lChild;
			}else{
				q = nodeStack.pop();
				p = nodeStack.pop();
			}
		}	
	}
	
	
	
	
	/**
	 * 删除整个二叉树，对于java语言，删除二叉树只要把根节点置空即可
	 * 下面的语法为c语言的实现
	 * 
	 */
//	public void delete(TreeNode &root)
//	{
//		if(root == null)
//		{
//			return;
//		}
//		else{
//			delete(root.lChild);
//			delete(root.rChild);
//   		free(root);
//			root  = null;
//		}
//	}
	
}












