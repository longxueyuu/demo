package datastructure.main;

import datastructure.list.LinkedList;
import datastructure.node.TreeNode;
import datastructure.queue.Queue;
import datastructure.stack.Stack;
import datastructure.tree.BinarySortTree;
import datastructure.tree.BinaryTree;
import datastructure.tree.ThreadBinaryTree;

public class Main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// LinkedList 测试
		LinkedList<String> lst = new LinkedList<String>();
		lst.add("a");
		lst.add("ab");
		lst.add("abc");
		lst.add("abcd");
		System.out.println(lst.size());
		for(int i = 0; i < lst.size(); i++)
		{
			System.out.println(lst.get(i));
		}
		lst.remove();
		System.out.println(lst.size());
		
		//Stack 测试
		Stack<String> stk = new Stack<String>();
		stk.init();
		stk.push("x");
		stk.push("y");
		stk.push("z");
		stk.push("m");
		stk.push("n");
		System.out.println("栈中元素个数：" + stk.size());
		System.out.println(stk.getTop());
		System.out.println(stk.getTop());
		System.out.println(stk.pop());
		System.out.println(stk.pop());
		System.out.println(stk.pop());
		System.out.println(stk.pop());
		System.out.println(stk.isEmpty());
		System.out.println(stk.pop());
		System.out.println(stk.isEmpty() + "\n");
		
		// Queue 测试
		Queue<String> queue = new Queue<String>();
		queue.init();
		System.out.println(queue.isEmpty() + " : " + queue.size());
		queue.add("aa");
		queue.add("bb");
		queue.add("cc");
		queue.add("dd");
		System.out.println(queue.getFront() + " size: " + queue.size());
		System.out.println(queue.getFront() + " size: " + queue.size());
		System.out.println(queue.deleteFront());
		System.out.println(queue.deleteFront());
		System.out.println(queue.deleteFront());
		System.out.println(queue.deleteFront() + " \nsize: " + queue.size());
		
		
		// Binary 测试
		BinaryTree<Character> bTree = new BinaryTree<Character>();
		TreeNode<Character> root = bTree.createTree("A(B(D,E(F(H(I,J),),G(,K(L(N,),M(,P))))),C(Q(R(T,),S(U,V(,W(,X(Y,Z))))),))@");
		//TreeNode root = bTree.createTree("A(B(C(,F(,M(N,L))),D),G(E,I(J,K))))@");
		System.out.println("\n---------pre-------------");
		bTree.preorder(root);
		System.out.println("\n---------pre---nonrecursion----------");
		bTree.preorderNonRecursion(root);
		
		System.out.println("\n\n---------post-------------");
		bTree.postorder(root);
		System.out.println("\n---------post---nonrecursion----------");
		bTree.postorderNonRecursion(root);
		
		System.out.println("\n\n----------in------------");
		bTree.inorder(root);
		System.out.println("\n----------in---nonrecursion---------");
		bTree.inorderNonRecursion(root);
		
		System.out.println("\n\n----------layer------------");
		bTree.layerOrder(root);
		
		System.out.println("\n\n----------------------\n");
		TreeNode<Character> root2 = bTree.createTree("A(,B(,C(E,D)))@");
		//TreeNode root2 = bTree.createTree("A(B(C(D,E),),)@");
		System.out.println("\n---------pre-------------");
		bTree.preorder(root2);
		System.out.println("\n---------pre---nonrecursion----------");
		bTree.preorderNonRecursion(root2);
		
		System.out.println("\n\n---------post-------------");
		bTree.postorder(root2);
		System.out.println("\n---------post---nonrecursion----------");
		bTree.postorderNonRecursion(root2);
		
		System.out.println("\n\n----------in------------");
		bTree.inorder(root2);
		System.out.println("\n----------in---nonrecursion---------");
		bTree.inorderNonRecursion(root2);
		
		System.out.println("\n\n----------layer------------");
		bTree.layerOrder(root2);
		
		// 测试二叉树的复制 等价 深度 某一节点所在层次
		TreeNode<Character> root3 = bTree.copyBTree(root);
		Boolean equal = false;
		equal = bTree.equalBTree(root, root2);
		System.out.println("\n 是否等价:" + equal);
		equal = bTree.equalBTree(root, root3);
		System.out.println("\n 是否等价:" + equal);
		equal = bTree.equalBTree(root2, root3);
		System.out.println("\n 是否等价:" + equal);
		
		int treeDepth = 0;
		treeDepth = bTree.depth(root);
		System.out.println("\n 二叉树深度:" + treeDepth);
		treeDepth = bTree.depth(root2);
		System.out.println("\n 二叉树深度:" + treeDepth);
		treeDepth = bTree.depth(root3);
		System.out.println("\n 二叉树深度:" + treeDepth);
		
		int nodeLayer = 0;
		nodeLayer = bTree.getNodeLayer(root2, 'A');
		System.out.println("\n 节点所在层次:" + nodeLayer);
		nodeLayer = bTree.getNodeLayer(root2, 'B');
		System.out.println("\n 节点所在层次:" + nodeLayer);
		nodeLayer = bTree.getNodeLayer(root2, 'C');
		System.out.println("\n 节点所在层次:" + nodeLayer);
		nodeLayer = bTree.getNodeLayer(root2, 'E');
		System.out.println("\n 节点所在层次:" + nodeLayer);
		
		// 测试二叉树左右孩子交换 删除数据域为给定数据的节点及其子树  获取叶节点的数量
		System.out.println("\n\n----------交换前层次遍历结果：------------");
		bTree.layerOrder(root);
		bTree.exchangeBTreeLRChild(root);
		System.out.println("\n----------交换后层次遍历结果：------------");
		bTree.layerOrder(root);
		
		int leafNodesNum = 0;
		System.out.println("\n\n----------叶节点数量：------------");
		leafNodesNum = bTree.getLeafNodesNumber(root);
		System.out.println(leafNodesNum);
		
		bTree.exchangeBTreeLRChild(root);
		System.out.println("\n\n----------删除前层次遍历结果：------------");
		bTree.layerOrder(root);
		bTree.deleteNodeAndItsSubtree(root, 'C');
		System.out.println("\n\n----------删除后层次遍历结果：------------");
		bTree.layerOrder(root);
		System.out.println("\n\n----------叶节点数量：------------");
		leafNodesNum = bTree.getLeafNodesNumber(root);
		System.out.println(leafNodesNum);
		System.out.println("\n\n----------叶节点数量：------------");
		leafNodesNum = bTree.getLeafNodesNumber(root2);
		System.out.println(leafNodesNum);
		
		// 测试中序线索二叉树生成、遍历
		ThreadBinaryTree<Character> threadTree = new ThreadBinaryTree<Character>();
		TreeNode<Character> threadRoot = threadTree.createTree2("A(B(D,E(F(H(I,J),),G(,K(L(N,),M(,P))))),C(Q(R(T,),S(U,V(,W(,X(Y,Z))))),))@");
		System.out.println("\n\n----------in------------");
		bTree.inorder(threadRoot);
		System.out.println("\n----------in---nonrecursion---------");
		bTree.inorderNonRecursion(threadRoot);
		TreeNode<Character> threadhead = threadTree.inorderThreadBTree(threadRoot);
		//System.out.println("\n" + threadhead.lBit  + " : " + threadhead.rBit + " : " + threadhead.rChild.lBit + " : " + threadhead);
		
		
		System.out.println("\n\n----------中序线索化二叉树：------------");
		threadTree.inorderByThreadBTree(threadhead);
		
//		// 测试每次输入单个字符 生成二叉树
//		BinaryTree<Character> cTree = new BinaryTree<Character>();
//		TreeNode<Character> cRoot = null;
//		cRoot = cTree.createTreeByPreorder(cRoot);
//		System.out.println("\n\n----------前序遍历：------------");
//		cTree.inorder(cRoot);
		
		// 测试 二叉排序树
		BinarySortTree<Integer> sortTree = new BinarySortTree<Integer>();
		TreeNode<Integer> sortTreeRoot = null, sortTreeNode = null;
		Integer[] nodeSequence = new Integer[]{1,0,4,5,3,6,2,39,15,1000,500,3000,10,8,9,7};
		//sortTreeRoot = sortTree.createBinarySortTree(nodeSequence);  // 递归生成二叉排序树
		sortTreeRoot = sortTree.createBinarySortTreeNonRecursion(nodeSequence); // 非递归生成二叉排序树
		System.out.println("\n\n----------中序遍历排序二叉树：------------");
		sortTree.inorder(sortTreeRoot);
		sortTreeNode = sortTree.search(sortTreeRoot, 1000);
		sortTreeNode = sortTree.searchNonRecursion(sortTreeRoot, 15);
		if(sortTreeNode != null)
			System.out.println("\n\n二叉排序树查找返回结点的值：" + sortTreeNode.data.intValue());
		
		sortTreeRoot = sortTree.deleteBinarySortTreeNode(sortTreeRoot, sortTree.search(sortTreeRoot, 1), sortTree.searchParentNode(sortTreeRoot, 1));
		System.out.println("\n\n----------删除节点后中序遍历排序二叉树：------------");
		sortTree.inorder(sortTreeRoot);
		System.out.println("\n\n----------删除节点后层次遍历排序二叉树：------------");
		sortTree.layerOrder(sortTreeRoot);
		
		//sortTree.cr
		
	}

}























