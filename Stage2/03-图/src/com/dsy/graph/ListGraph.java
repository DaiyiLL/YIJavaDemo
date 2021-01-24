package com.dsy.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.dsy.MinHeap;
import com.dsy.UnionFind;

@SuppressWarnings("unused")
public class ListGraph<V, E> extends Graph<V, E> {

	public ListGraph(WeightManager<E> weightManager) {
		super(weightManager);
		// TODO Auto-generated constructor stub
	}
	
	public ListGraph() {
		super();
	}

	private Map<V, Vertex<V, E>> vertices = new HashMap<>();
	private Set<Edge<V, E>> edges = new HashSet<>();
	private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
		return weightManager.compare(e1.weight, e2.weight);
	};
	
	public void print() {
		System.out.println("【定点】-----------------------");
		vertices.forEach((key, vertex) -> {
			System.out.println(key);
			System.out.println("out = " + vertex.outEdges);
			System.out.println("in = " + vertex.inEdges);
		});
		System.out.println("【边】------------------------");
		edges.forEach((edge) -> {
			System.out.println(edge);
		});
	}
	
	@Override
	public int edgetsSize() {
		// TODO Auto-generated method stub
		return edges.size();
	}

	@Override
	public int verticesSize() {
		// TODO Auto-generated method stub
		return vertices.size();
	}

	@Override
	public void addVertex(V v) {
		// TODO Auto-generated method stub
		if (vertices.containsKey(v)) return;
		vertices.put(v, new Vertex<>(v));
	}

	@Override
	public void addEdge(V from, V to) {
		// TODO Auto-generated method stub
		addEdge(from, to, null);
	}

	@Override
	public void addEdge(V from, V to, E weight) {
		// 判断from，to定点是否存在
		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) {
			fromVertex = new Vertex<>(from);
			vertices.put(from, fromVertex);
		}
		
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) {
			toVertex = new Vertex<>(to);
			vertices.put(to, toVertex);
		}
		
		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		edge.weight = weight;
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
		
		fromVertex.outEdges.add(edge);
		toVertex.inEdges.add(edge);
		edges.add(edge);
	}

	@Override
	public void removeVertex(V v) {
		// TODO Auto-generated method stub
		Vertex<V, E> vertex = vertices.remove(v);
		if (vertex == null) return;
		
		// 迭代器, 删除出去的边
		for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
			Edge<V, E> edge = (Edge<V, E>) iterator.next();
			edge.to.inEdges.remove(edge);
			// 通过迭代器删除元素
			iterator.remove();
			edges.remove(edge);
		}
		
		// 迭代器，删除进来的边
		for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
			Edge<V, E> edge = (Edge<V, E>) iterator.next();
			edge.from.outEdges.remove(edge);
			// 通过迭代器删除元素
			iterator.remove();
			edges.remove(edge);
		}
	}

	@Override
	public void removeEdge(V from, V to) {
		// TODO Auto-generated method stub
		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) return;
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) return;
		Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
	}
	
//	@Override
//	public void bfs(V begin) {
//		// TODO Auto-generated method stub
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//		Queue<Vertex<V, E>> queue = new LinkedList<>();
//		queue.offer(beginVertex);
//		visitedVertices.add(beginVertex);
//		
//		while (!queue.isEmpty()) {
//			Vertex<V, E> vertex = queue.poll();
//			
//			System.out.println(vertex.value);
//			
//			for (Edge<V, E> edge: vertex.outEdges) {
//				if (visitedVertices.contains(edge.to)) {
//					continue;
//				}
//				queue.offer(edge.to);
//				visitedVertices.add(edge.to);
//			}
//		}
//	}
	
	@Override
	public void bfs(V begin, VertexVisitor visitor) {
		// TODO Auto-generated method stub
		if (visitor == null) return;
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return;
		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
		Queue<Vertex<V, E>> queue = new LinkedList<>();
		queue.offer(beginVertex);
		visitedVertices.add(beginVertex);
		
		while (!queue.isEmpty()) {
			Vertex<V, E> vertex = queue.poll();
			
			if (visitor.visit(vertex.value)) return;
			
			for (Edge<V, E> edge: vertex.outEdges) {
				if (visitedVertices.contains(edge.to)) {
					continue;
				}
				queue.offer(edge.to);
				visitedVertices.add(edge.to);
			}
		}
	}
	
	@Override
	public void dfs(V begin, VertexVisitor visitor) {
		// TODO Auto-generated method stub
		if (visitor == null) return;
		
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return;
		
		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
		Stack<Vertex<V, E>> stack = new Stack<>();
		
		// 先访问七点
		stack.push(beginVertex);
		visitedVertices.add(beginVertex);
//		System.out.println(beginVertex.value);
		if (visitor.visit(beginVertex.value)) return;
		
		while(!stack.isEmpty()) {
			Vertex<V, E> vertex = stack.pop();
			for (Edge<V, E> edge: vertex.outEdges) {
				if (visitedVertices.contains(edge.to)) continue;
				stack.push(edge.from);
				stack.push(edge.to);
				visitedVertices.add(edge.to);
				if (visitor.visit(edge.to.value)) return;
				break;
			}
		}
	}
	
//	@Override
//	public void dfs(V begin) {
//		// TODO Auto-generated method stub
//		Vertex<V, E> beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
//		
//		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//		Stack<Vertex<V, E>> stack = new Stack<>();
//		
//		// 先访问七点
//		stack.push(beginVertex);
//		visitedVertices.add(beginVertex);
//		System.out.println(beginVertex.value);
//		
//		while(!stack.isEmpty()) {
//			Vertex<V, E> vertex = stack.pop();
//			for (Edge<V, E> edge: vertex.outEdges) {
//				if (visitedVertices.contains(edge.to)) continue;
//				stack.push(edge.from);
//				stack.push(edge.to);
//				visitedVertices.add(edge.to);
//				System.out.println(edge.to.value);
//				break;
//			}
//		}
//	}
	
//	@Override
//	public void dfs(V begin) {
//		// TODO Auto-generated method stub
//		Vertex<V, E>beginVertex = vertices.get(begin);
//		if (beginVertex == null) return;
////		System.out.println();
//		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//		dfs(beginVertex, visitedVertices);
//		
//	}
	private void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> set) {
		System.out.println(vertex.value);
		set.add(vertex);
		
		for (Edge<V, E> edge : vertex.outEdges) {
			if (!set.contains(edge.to)) {
				dfs(edge.to, set);
			}
		}
	}
	
	@Override
	public List<V> toplogicalSort() {
		// TODO Auto-generated method stub
		List<V> list = new ArrayList<>();
		Queue<Vertex<V, E>> queue = new LinkedList<>();
		Map<Vertex<V, E>, Integer> ins = new HashMap<>();
		// 初始化(将度为0 的节点放入队列中)
		vertices.forEach((v, vertex) -> {
			int in = vertex.inEdges.size();
			if (in == 0) {
				queue.offer(vertex);
			} else {
				ins.put(vertex, in);
			}
		});
		
		while (!queue.isEmpty()) {
			Vertex<V, E> vertex = queue.poll();
			// 放入返回结果中
			list.add(vertex.value);
			
			// 遍历
			for (Edge<V, E> edge: vertex.outEdges) {
				int toIn = ins.get(edge.to) - 1;
				if (toIn == 0) {
					queue.offer(edge.to);
				} else {
					ins.put(edge.to, toIn);
				}
			}
		}
		
		return list;
	}
	
	@Override
	public Set<EdgeInfo> mst() {
		// TODO Auto-generated method stub
//		return prim();
		return  Math.random() > 0.5 ? kruskal() : prim();
	}
	
	@Override
	public Map<V, E> shortedestPath(V begin) {
		// TODO Auto-generated method stub
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return null;
		
		Map<V, E> selectedPaths = new HashMap<>();
		Map<Vertex<V, E>, E> paths = new HashMap<>();
		
		for (Edge<V, E>edge : beginVertex.outEdges) {
			paths.put(edge.to, edge.weight);
		}
		
		while (!paths.isEmpty()) {
			Entry<Vertex<V, E>, E> minEntry = getVertexMinPath(paths);
			// minEntry离开左面，对他的outEdges进行松弛操作
			Vertex<V, E> minVertex = minEntry.getKey();
			selectedPaths.put(minVertex.value, minEntry.getValue());
			paths.remove(minVertex);
			// 对它的minVertex的outEdges进行松弛操作
			for (Edge<V, E> edge : minVertex.outEdges) {
				// 如果edge.to已经离开左面，就没必要进行松弛操作
				if (selectedPaths.containsKey(edge.to.value) || edge.to.equals(beginVertex)) continue;
//				if (selectedPaths.containsKey(edge.to.value)) continue;
				// 以前的最短路径: beginVertex -> edge.to
				E oldWeight = paths.get(edge.to);
				// 新的最短路径
				E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
				if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0 ) {
					// 更新
					paths.put(edge.to, newWeight);
				}
//				relax();
			}
		}
//		selectedPaths.remove(begin);
		return selectedPaths;
	}
	
	@Override
	public Map<V, PathInfo<V, E>> shortedestPathInfo(V begin) {
//		return dijkstra(begin);
		return bellmanFord(begin);
	}
	
	private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return null;
		
		Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
		
		PathInfo<V, E> path = new PathInfo<>();
		path.weight = weightManager.zero();
		selectedPaths.put(begin, path);
		
		int count = vertices.size() - 1;
		for (int i = 0; i < count; i++) { // 执行v-1次
			for (Edge<V, E> edge: edges) {
				PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
				if (fromPath  == null) continue;
				relax(selectedPaths, edge, fromPath);
			}
		}
		for (Edge<V, E> edge: edges) {
			PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
			if (fromPath  == null) continue;
			if (relax(selectedPaths, edge, fromPath)) {
				// 有负权环
				return null;
			}
		}
		
		selectedPaths.remove(begin);
		return selectedPaths;
	}
	
	private Map<V, PathInfo<V, E>> dijkstra(V begin) {
		// TODO Auto-generated method stub
		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) return null;
		
		Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
		Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
		
		for (Edge<V, E>edge : beginVertex.outEdges) {
			PathInfo path = new PathInfo<>();
			path.weight = edge.weight;
			path.edgeInfos.add(edge.info());
			paths.put(edge.to, path);
		}
		
		while (!paths.isEmpty()) {
			Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
			// minEntry离开左面，对他的outEdges进行松弛操作
			Vertex<V, E> minVertex = minEntry.getKey();
			selectedPaths.put(minVertex.value, minEntry.getValue());
			paths.remove(minVertex);
			// 对它的minVertex的outEdges进行松弛操作
			for (Edge<V, E> edge : minVertex.outEdges) {
				// 如果edge.to已经离开左面，就没必要进行松弛操作
				if (selectedPaths.containsKey(edge.to.value) || edge.to.equals(beginVertex)) continue;
				
				relaxForDijkstra(paths, edge, minEntry.getValue());
			}
		}
//						selectedPaths.remove(begin);
		return selectedPaths;
	}
	
	/*
	 * 从paths中挑一个最短的路径出来
	 */
	private Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
		Iterator<Entry<Vertex<V, E>, PathInfo<V, E>>> it = paths.entrySet().iterator();
		Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();
		while (it.hasNext()) {
			Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
			E weight = entry.getValue().getWeight();
			if (weightManager.compare(weight, minEntry.getValue().getWeight()) < 0) {
				minEntry = entry;
			} 
		}
		return minEntry;
	}
	
	
	
	public Entry<Vertex<V, E>, E> getVertexMinPath(Map<Vertex<V, E>, E> paths) {
		Entry<Vertex<V, E>, E> minEntry = null;
		
		for (Entry<Vertex<V, E>, E> entry: paths.entrySet()) {
			E weight = entry.getValue();
			if (weightManager.compare(weight, minEntry.getValue()) < 0 || minEntry == null ) {
				minEntry = entry;
			} 
		}
		return minEntry;
	}
	
	private void relaxForDijkstra(Map<Vertex<V, E>, PathInfo<V, E>> paths, Edge<V, E> edge,  PathInfo<V, E> fromPath) {
		// 以前的最短路径: beginVertex -> edge.to
		PathInfo<V, E> oldPath = paths.get(edge.to);
		// 新的最短路径
		E newWeight = weightManager.add(fromPath.weight, edge.weight);
		
		if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return;
		if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
			oldPath = new PathInfo<>();
			paths.put(edge.to, oldPath);
		} else {
			oldPath.edgeInfos.clear();
		}
		
		oldPath.weight = newWeight;
		oldPath.edgeInfos.addAll(fromPath.edgeInfos);
		oldPath.edgeInfos.add(edge.info());
	}
	
	private boolean relax(Map<V, PathInfo<V, E>> paths, Edge<V, E> edge,  PathInfo<V, E> fromPath) {
		// 以前的最短路径: beginVertex -> edge.to
		PathInfo<V, E> oldPath = paths.get(edge.to.value);
		// 新的最短路径
		E newWeight = weightManager.add(fromPath.weight, edge.weight);
		
		if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;
		if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
			oldPath = new PathInfo<>();
			paths.put(edge.to.value, oldPath);
		} else {
			oldPath.edgeInfos.clear();
		}
		
		oldPath.weight = newWeight;
		oldPath.edgeInfos.addAll(fromPath.edgeInfos);
		oldPath.edgeInfos.add(edge.info());
		// 松弛成功
		return true;
	}
	
	
	private Set<EdgeInfo> prim() {
		Iterator<Vertex<V, E>> it = vertices.values().iterator();
		if (!it.hasNext()) return null;
		
		Vertex<V, E> vertex = it.next();
		Set<EdgeInfo> edgeInfos = new HashSet<>();
		Set<Vertex<V, E>> addedVertices = new HashSet<>();
		
		MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);
		addedVertices.add(vertex);
		
//		int edgeSize = vertices.size() - 1;
		int verticesSize = vertices.size();
		while (!heap.isEmpty() && edgeInfos.size() < verticesSize) {
			Edge<V, E> edge = heap.remove();
			// 如果最小生成树已经添加过
			if (addedVertices.contains(edge.to)) continue;
			
			edgeInfos.add(edge.info());
			addedVertices.add(edge.to);
			heap.addAll(edge.to.outEdges);
		}
		
		return edgeInfos;
	}
	
	private Set<EdgeInfo> kruskal() {
		int edgeSize = vertices.size() - 1;
		if (edgeSize <= -1) return null;
		Set<EdgeInfo> edgeInfos = new HashSet<>();
		MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);
		UnionFind<Vertex<V, E>> uf = new UnionFind<>();
		vertices.forEach((V v, Vertex<V, E>vertex) -> {
			uf.makeSet(vertex);
		});
		
		
		while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
			Edge<V, E> edge = heap.remove();
			// 小心构成环
			if (uf.isSame(edge.from, edge.to)) continue;
			edgeInfos.add(edge.info());
			uf.union(edge.from, edge.to);
		}
		
		return edgeInfos;
	}
	
	private static class Vertex<V, E> {
		V value;
		Set<Edge<V, E>> inEdges = new HashSet<>();
		Set<Edge<V, E>> outEdges = new HashSet<>();
		public Vertex(V value) {
			super();
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return Objects.equals(value, ((Vertex<V, E>)obj).value);
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return value == null ? 0 : value.hashCode();
		}

		@Override
		public String toString() {
			return value == null ? "null" : value.toString();
//			return "Vertex [value=" + valueStr + ", inEdges=" + inEdges + ", outEdges=" + outEdges + "]";
		}
		
		
	}
	
	private static class Edge<V, E> {
		Vertex<V, E> from;
		Vertex<V, E> to;
		E weight;
		
		public Edge(Vertex<V, E> from, Vertex<V, E> to) {
			super();
			this.from = from;
			this.to = to;
		}
		
		EdgeInfo<V, E> info() {
			return new EdgeInfo<V, E>(from.value, to.value, weight);
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (obj == null) return false;
			Edge<V, E> edge = (Edge<V, E>)obj;
			return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			int fromCode = from.hashCode();
			int toCode = to.hashCode();
			return fromCode * 31 + toCode;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
}
