package datastructure.graph;

import java.util.Arrays;
import java.util.Scanner;

import datastructure.node.graphnode.EdgeNode;
import datastructure.node.graphnode.VertexNode;
import datastructure.queue.Queue;

public class Graph {
	
	
	
	/**
	 * 生成图的邻接矩阵
	 * 
	 * @param G 	the adjust matrix
	 * @param n 	the number of the graph vertexes
	 * @param e 	the number of the graph edges
	 */
	public void adjMatrix(int G[][], int n, int e)
	{
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(i == j)
				{
					G[i][j] = 0;
				}else{
					G[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int k = 0; k < e; k++)
		{
			Scanner sc = new Scanner(System.in);
			int i,j,weight;
			i = sc.nextInt();
			j = sc.nextInt();
			weight = sc.nextInt();
			G[i][j] = weight;
			G[j][i] = weight;
		}
	}
	
	/**
	 * 生成图的邻接矩阵
	 * 
	 * @param G 	the adjust matrix
	 * @param n 	the number of the graph vertexes
	 * @param e 	the number of the graph edges
	 */
	public void adjMatrix(int G[][], int[][] edge)
	{
		int n = G.length;
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(i == j)
				{
					G[i][j] = 0;
				}else{
					G[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for(int k = 0; k < edge.length; k++)
		{
			int i,j,weight;
			i = edge[k][0] - 1;
			j = edge[k][1] - 1;
			weight = edge[k][2];
			G[i][j] = weight;
			G[j][i] = weight;
		}
	}
	
	
	/**
	 * 创建图的邻接表结构
	 * 
	 * @param vertex
	 * @param array
	 * @param vertexIndegree
	 */
	public void adjList(VertexNode[] vertex, int[][] array, int[] vertexIndegree)
	{
		int n = vertex.length; // 顶点数
		int e = array.length; // 边数
		for(int i = 0; i < n; i++)
		{
			vertex[i] = new VertexNode();
			vertex[i].vertex = i + 1;
			vertex[i].indegree = vertexIndegree[i];
			vertex[i].link = null;
		}
		for(int j = 0; j < e; j++)
		{
			EdgeNode edge = new EdgeNode();
			edge.adjvex = array[j][1]-1; // 存储该边的终点在顶点结点中的位置
			edge.weight = array[j][2];
			edge.next = null;
			if(vertex[array[j][0]-1].link == null)
			{
				vertex[array[j][0]-1].link = edge;
			}else{
				EdgeNode q = vertex[array[j][0]-1].link;
				while(q.next != null)
				{
					q = q.next;
				}
				q.next = edge;
			}
		}
	}
	
	/**
	 * 打印图的邻接表结构
	 * 
	 * @param vertex
	 */
	public void printGraph(VertexNode[] vertex)
	{
		for(int i = 0; i < vertex.length; i++)
		{
			if(vertex[i] == null)
			{
				continue;
			}
			EdgeNode p = vertex[i].link;
			System.out.print("第" + i + "个结点：");
			System.out.print(vertex[i].vertex + "(入度:" + vertex[i].indegree + ")  ");
			while(p != null)
			{
				System.out.print(p.adjvex + "(" + p.weight + ")" + " ");
				p = p.next;
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * 删除图中数据信息为x的顶点
	 * 
	 * @param vertex 	the array of vertexes
	 * @param x the 	content of vertex node
	 * @return return 	the new array of vertexes
	 */
	public VertexNode[] deleteVertex(VertexNode[] vertex, int x){
		
		EdgeNode p = null, q = null;
		int k = -1;
		for(int i = 0; i < vertex.length; i++)
		{
			if(vertex[i].vertex == x)
			{
				k = i;
				break;
			}
		}
		if(k == -1)
		{
			return vertex;
		}
		for(int j = k; j < vertex.length - 1; j++)
		{
			vertex[j].vertex = vertex[j+1].vertex;
			vertex[j].indegree = vertex[j+1].indegree;
			vertex[j].link = vertex[j+1].link;
		}	
		vertex = Arrays.copyOf(vertex, vertex.length-1); //将数组的前n-1个元素复制到一个新的数组并返回
		//System.out.println("顶点数目：" + vertex.length);
		for(int m = 0; m < vertex.length; m++)
		{
			p = vertex[m].link;
			while(p != null)
			{
				if(p.adjvex == k)
				{
					if(p == vertex[m].link)
					{
						vertex[m].link = p.next;
					}else{
						q.next = p.next;
					}
					p = p.next;
				}else{
					if(p.adjvex > k)
					{
						p.adjvex--;
					}
					q = p;
					p = p.next;
				}
			}
		}
		return vertex;
	}
	
	/**
	 * 深度优先搜索递归算法
	 * 
	 * @param vertex
	 * @param v
	 */
	private void depthFirstSearch(VertexNode[] vertex, int v, int[] visited)
	{
		EdgeNode p = null;
		System.out.print(vertex[v].vertex + " "); // 访问第v个结点
		visited[v] = 1; // 置1表示已访问
		p = vertex[v].link;
		while(p != null)
		{
			if(visited[p.adjvex] == 0)
			{
				depthFirstSearch(vertex,p.adjvex,visited);
			}
			p = p.next;
		}
	}
	
	/**
	 * 深度优先遍历图
	 * 
	 * @param vertex
	 * @param visited 
	 */
	public void travelDFS(VertexNode[] vertex, int[] visited)
	{
		for(int i = 0; i < visited.length; i++)
		{
			visited[i] = 0;
		}
		for(int j = 0; j < vertex.length; j++)
		{
			if(visited[j] == 0)
			{
				depthFirstSearch(vertex, j, visited);
			}
		}
	}
	
	/**
	 * 广度优先搜索算法
	 * 
	 * @param vertex
	 * @param v
	 */
	private void breadthFirstSearch(VertexNode[] vertex, int v, int[] visited)
	{
		Queue<VertexNode> adjustNodeQueue = new Queue<VertexNode>();
		adjustNodeQueue.init();
		EdgeNode q = null;
		VertexNode p = null;
		if(visited[v] == 0)
		{
			System.out.print(vertex[v].vertex + " "); // 访问第v个结点
			visited[v] = 1; // 置1表示已访问
		}
		adjustNodeQueue.add(vertex[v]); // 顶点v进队
		while(!adjustNodeQueue.isEmpty())
		{
			p = adjustNodeQueue.deleteFront(); //退出队头元素
			q = p.link; // 取顶点p的第一个邻接点
			while(q != null)
			{
				if(visited[q.adjvex] == 0)
				{
					System.out.print(vertex[q.adjvex].vertex + " "); // 访问第q.adjvex个结点
					visited[q.adjvex] = 1; // 置1表示已访问
					adjustNodeQueue.add(vertex[q.adjvex]); // 当前被访问的顶点q.adjvex进队
				}
				q = q.next; // 取q的下一个邻接点
			}
			
		}
	}
	
	/**
	 * 广度优先遍历图
	 * 
	 * @param vertex
	 * @param visited
	 */
	public void travelBFS(VertexNode[] vertex, int[] visited)
	{
		for(int i = 0; i < visited.length; i++)
		{
			visited[i] = 0;
		}
		for(int j = 0; j < vertex.length; j++)
		{
			if(visited[j] == 0)
			{
				breadthFirstSearch(vertex, j, visited);
			}
		}
	}
	
	/**
	 * 求x点到各顶点的最短路径(Dijkstra算法)
	 * 
	 * @param dist		the array to store the shortest path
	 * @param cost  	the adjust matrix
	 * @param x   		the start vertex ,x ranges from 1 to n
	 * @param path  	the array to record the shortest path which vertexes has been gone through from x to every vertex of all
	 */
	public void shortestPath(int dist[], int[][] cost, int x, int[][] path)
	{
		int v = x - 1; // 将源点x的范围从1..n转换为0..n-1
		int n = dist.length; // 图的顶点的数目
		int count = 0;  // 选择 尚未确定最短路径且与源点v最近的顶点 的次数
		int[] s = new int[n]; // 标记顶点是否已经确定最短路径，其中1表示已确定，0表示未确定
		int[] pos = new int[n]; // 各个点最短路径已经经过的顶点在path中的位置
		
		for(int i = 0; i < n; i++)
		{
			dist[i] = cost[v][i]; // 初始v点到各点的距离为邻接矩阵中的第v行
			path[i][0] = v + 1; // 每条路径的起点为v
			s[i] = 0;  // 初始时所有点都未确定最短路径
			pos[i] = 0; //  初始各个点最短路径已经经过的顶点在path中的位置为0
		}
		 
		s[v] = 1;
		count = 1;
		while(count < n)
		{
			int u = minDist(dist, s); //利用dist 和 s 从尚未确定最短路径的顶点中确定一个与v点（源点）最近的顶点
			s[u] = 1;
			count++;
			path[u][++pos[u]] = u + 1;
			int startPos = 0; // 在cost[u][0..n-1]寻找u值可以直接到达的顶点W的起始寻找位置
			int w;
			while(-1 != (w = searchReachableVertex(cost, s, u, startPos))) // 找到一个通过u可以直接到达但尚未确定最短路径的顶点
			{
				if(dist[w] > dist[u] + cost[u][w]) //更新最短路径值
				{
					dist[w] = dist[u] + cost[u][w];
					for(int j = 0; j <n; j++) // 用源点v到u的路径替换v到w的路径
					{
						if(j <= pos[u])
						{
							path[w][j] = path[u][j];
						}else{
							path[w][j] = 0;
						}
					}
					pos[w] = pos[u]; // 替换路径后，将w点的位置标识置为u的位置值
				}
				startPos = w + 1; //更新寻找可达顶点w的起始位置
		   	}
		}
	}
	
	/**
	 * 从尚未确定最短路径的顶点中确定一个与v点（源点）最近的顶点
	 * 
	 * @param dist 		the array to store the shortest path
	 * @param s			the array to mark the vertexes whether they are visited	
	 * @return			the closest vertex of source vertex
	 */
	private int minDist(int[] dist, int[] s)
	{
		int k = 0;
		int x = 0;
		int flag = 0;
		for(int i = 0; i < dist.length; i++)
		{
			// 该步的目的是消除值为0对寻找非0最小值的影响，确保寻找到确定的最小值
			if(dist[i] == 0)// 先将距离为零的点距离置为最大，并置修改标志为1，记录更改的顶点
			{
				dist[i] = Integer.MAX_VALUE;
				x = i;
				flag = 1;
			}else{
				if(dist[i] < dist[k] && s[i] == 0)
				{
					k = i; // 记录最短路径对应的顶点
				}
			}
			
		}
		if(flag == 1) //若修改标志为1，则将修改的点的距离值恢复为原始值0
		{
			dist[x] = 0;
		}
		return k;
	}
	
	/**
	 * 找到一个通过u可以直接到达但尚未确定最短路径的顶点
	 * 
	 * @param cost  	the adjust matrix
	 * @param s  	 	the array to mark the vertexes whether they are visited
	 * @param u   		the vertex of the graph
	 * @param startPos  the position where to start to search the reachable vertex of u
	 * @return			if the reachable vertex has been found,return the vertex number; if not ,return -1;
	 */
	private int searchReachableVertex(int[][] cost, int[] s, int u, int startPos)
	{
		for(int i = startPos; i < s.length; i++)
		{
			if(cost[u][i] < Integer.MAX_VALUE && cost[u][i] > 0 && 0 == s[i])
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 拓扑排序
	 * 
	 * @param vertex  		the adjust list of the graph
	 * @param topoSequence  the array to store the result of topological sorting sequence 
	 */
	public void topologicalSort(VertexNode[] vertex, int[] topoSequence)
	{
		int n = vertex.length;
		int[] stack = new int[n]; // 存储入度为零的结点栈
		int top = -1; //栈顶指针
		for(int i = 0; i < n; i++) // 初始化栈
		{
			if(vertex[i].indegree == 0)
			{
				stack[++top] = i;
			}
		}
		for(int m = 0; m < n; m++)
		{
			if(top == -1)
			{
				System.out.println("\nAOV网中有回路！");
				break;
			}
			int j = stack[top--];
			EdgeNode p = null;
			System.out.print(vertex[j].vertex + " "); // 访问入度为零的结点
			topoSequence[m] = vertex[j].vertex;
			p = vertex[j].link; 
			//vertex[j] = null;  // 删除该点
			while(p != null) // 入度为零的所有邻接点对应的顶点入度减1
			{
				int k = p.adjvex;
				vertex[k].indegree--;
				if(vertex[k].indegree == 0)
				{
					stack[++top] = k;
				}
				p = p.next;
			}
			
		}
	}
}




















































