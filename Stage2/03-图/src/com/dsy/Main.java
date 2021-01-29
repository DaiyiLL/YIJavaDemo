package com.dsy;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dsy.graph.Graph;
import com.dsy.graph.Graph.EdgeInfo;
import com.dsy.graph.Graph.PathInfo;
import com.dsy.graph.Graph.WeightManager;
import com.dsy.graph.ListGraph;

public class Main {
	static WeightManager<Double> weightManager = new WeightManager<Double>() {

		@Override
		public int compare(Double w1, Double w2) {
			// TODO Auto-generated method stub
			return w1.compareTo(w2);
		}

		@Override
		public Double add(Double w1, Double w2) {
			// TODO Auto-generated method stub
			return w1 + w2;
		}
		
		public Double zero() {
			return 0.0;
		};
	};
	
	public static void main(String[] args) {
//		testBFS();
//		testDFS();
		
//		testTopo();
//		testMst();
		
//		testSP();
		
		testMulTiSP();
	}
	
	private static void testMulTiSP() {
		Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
		Map<Object, Map<Object, PathInfo<Object, Double>>> sp = graph.shortedestMulPath();
		if (sp == null) return;
		sp.forEach((Object from, Map<Object, PathInfo<Object, Double>> paths) -> {
			System.out.println(from + "---------------------------");
			paths.forEach((Object to, PathInfo<Object, Double> path) -> {
				System.out.println(to + " - " + path);
			});
		});
	}
	
	private static void testSP() {
//		Graph<Object, Double> graph = directedGraph(Data.SP);
//		Graph<Object, Double> graph = undirectedGraph(Data.SP);
//		Map<Object, Double> sp = graph.shortedestPath("A");
//		System.out.println(sp);
		
//		Graph<Object, Double> graph = undirectedGraph(Data.SP);
		Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT2);
		Map<Object, PathInfo<Object, Double>> sp = graph.shortedestPathInfo("A");
//		System.out.println(sp);
		if (sp == null) {
			System.out.println("负权环");
			return;
		}
		sp.forEach((Object v, PathInfo<Object, Double> path) -> {
			System.out.println(v + " - " + path);
		});
	}
	
	private static void testMst() {
		Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
		Set<EdgeInfo> infos = graph.mst();
//		System.out.println(infos);
		for (EdgeInfo<Object, Double>info: infos) {
			System.out.println(info);
		}
	}
	
	private static void testTopo() {
		Graph<Object, Double> graph = directedGraph(Data.TOPO);
		List<Object> list = graph.toplogicalSort();
		System.out.println(list);
//		list.forEach((v) -> {
//			System.out.println(v);
//		});
	}
	
	private static void testBFS() {
//		Graph<Object, Double> graph = undirectedGraph(Data.BFS_01);
//		graph.bfs("A");
		Graph<Object, Double> graph = directedGraph(Data.BFS_02);
		graph.bfs(0, (value) -> {
			System.out.println(value);
			return value.equals(2);
		});
		
	}
	
	private static void testDFS() {
		Graph<Object, Double> graph = directedGraph(Data.DFS_02);
//		graph.dfs("a");
		graph.dfs("c", (value) -> {
			System.out.println(value);
			return false;
		});
	}
	
	private static void test01() {
		ListGraph<String, Integer> graph = new ListGraph<>();
		graph.addEdge("V0", "V4", 6);
		
		graph.addEdge("V1", "V0", 9);
		graph.addEdge("V1", "V2", 3);
		
		graph.addEdge("V2", "V0", 2);
		graph.addEdge("V2", "V3", 5);
		
		graph.addEdge("V3", "V4", 1);
		
//		graph.bfs("V1");
	}
	
	private static void test00() {
		ListGraph<String, Integer> graph = new ListGraph<>();
		graph.addEdge("V0", "V1");
		graph.addEdge("V1", "V0");
		
		graph.addEdge("V0", "V2");
		graph.addEdge("V2", "V0");
		
		graph.addEdge("V0", "V3");
		graph.addEdge("V3", "V0");
		
		graph.addEdge("V2", "V1");
		graph.addEdge("V1", "V2");
		
		graph.addEdge("V2", "V3");
		graph.addEdge("V3", "V2");
		
//		graph.print();
		
//		graph.removeEdge("V0", "V4");
//		graph.removeVerext("V0");
		graph.print();
	}
	
	private static void test() {
		ListGraph<String, Integer> graph = new ListGraph<>();
		graph.addEdge("V0", "V4", 6);
		
		graph.addEdge("V1", "V0", 9);
		graph.addEdge("V1", "V2", 3);
		
		graph.addEdge("V2", "V0", 2);
		graph.addEdge("V2", "V3", 5);
		
		graph.addEdge("V3", "V4", 1);
		
//		graph.print();
		
//		graph.removeEdge("V0", "V4");
		graph.removeVertex("V0");
		graph.print();
	}
	
	/**
	 * 有向图
	 */
	private static Graph<Object, Double> directedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
			}
		}
		return graph;
	}
	
	/**
	 * 无向图
	 * @param data
	 * @return
	 */
	private static Graph<Object, Double> undirectedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
				graph.addEdge(edge[1], edge[0]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
				graph.addEdge(edge[1], edge[0], weight);
			}
		}
		return graph;
	}
}
