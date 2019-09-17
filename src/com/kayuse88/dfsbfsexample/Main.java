package com.kayuse88.dfsbfsexample;

import java.util.*;

class Graph {
    private ArrayList<Integer>[] graph;
    private boolean[] visited;
    private int[] parent;

    public Graph(int size) {
        graph = new ArrayList[size + 1];
        for (int i = 0; i < size + 1; i++)
            graph[i] = new ArrayList<Integer>();
        visited = new boolean[size + 1];
        parent = new int[size + 1];
    }

    public void addEdge(int m, int n) {
        graph[m].add(n);
        graph[n].add(m);
    }

    public void addEdge(int[][] edges) {
        for (int[] edge : edges)
            addEdge(edge[0], edge[1]);
    }

    public int getSize() {
        return graph.length - 1;
    }

    public List<Integer> adjacentEdges(int node) {
        return graph[node];
    }

    public static void DFS(Graph G, int v) {
        // label v as discovered
        G.visited[v] = true;
        System.out.print(v + " ");
        // for all directed edges from v to w that are in G.adjacentEdges(v) do
        for (int w : G.adjacentEdges(v)) {
            // if vertex w is not labeled as discovered then
            if (!G.visited[w])
                // recursively call DFS(G, w)
                DFS(G, w);
        }
    }

    public static void DFS_iterative(Graph G, int v) {
        // let S be a stack
        Stack<Integer> S = new Stack();
        // S.push(v)
        S.push(v);
        // while S is not empty
        while (!S.empty()) {
            // v = S.pop()
            v = S.pop();
            // if v is not labeled as discovered:
            if (!G.visited[v]) {
                // label v as discovered
                G.visited[v] = true;
                System.out.print(v + " ");
                // for all edges from v to w in G.adjacentEdges(v) do
                for (int w = G.adjacentEdges(v).size(); w > 0; w--) {
                    // S.push(w)
                    S.push(G.adjacentEdges(v).get(w - 1));
                }
            }
        }
    }

    public static int BFS(Graph G, int start_v) {
        // let Q be a queue
        Queue Q = new LinkedList<Integer>();
        // label start_v as discovered
        G.visited[start_v] = true;
        // Q.enqueue(start_v)
        Q.add(start_v);
        // while Q is not empty
        while (!Q.isEmpty()) {
            // v = Q.dequeue()
            int v = (int)Q.poll();
            System.out.print(v + " ");
            // if v is the goal:
            if (v == G.getSize())
                // return v
                return v;
            // for all edges from v to w in G.adjacentEdges(v) do
            for(int w: G.adjacentEdges(v))
                // if w is not labeled as discovered:
                if (!G.visited[w]) {
                    // label w as discovered
                    G.visited[w] = true;
                    // w.parent = v
                    G.parent[w] = v;
                    // Q.enqueue(w)
                    Q.add(w);
                }
        }

        // If cannot reach the goal, return 0
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {1, 2}, {1, 5}, {2, 3},
                {3, 7}, {2, 4}, {4, 6},
                {6, 8}};

        Graph graph = new Graph(8);
        graph.addEdge(edges);

        // 주석 처리를 통해 원하는 탐색 방식 수행
        // Graph.DFS(graph, 1);
        // Graph.DFS_iterative(graph, 1);
        Graph.BFS(graph, 1);
    }
}