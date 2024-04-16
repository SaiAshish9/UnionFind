package com.sai.designPatterns.unionFind;

import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class UnionFind {
    int[] parent; // representative, leader, root
    int[] rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }
}

public class KruskalMinimumSpanningTree {

    public static List<Edge> kruskal(List<Edge> edges, int n) {
        Collections.sort(edges);

        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            if (uf.find(edge.src) != uf.find(edge.dest)) {
                uf.union(edge.src, edge.dest);
                mst.add(edge);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 7, 8));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 7, 11));
        edges.add(new Edge(2, 3, 7));
        edges.add(new Edge(2, 5, 4));
        edges.add(new Edge(2, 8, 2));
        edges.add(new Edge(3, 4, 9));
        edges.add(new Edge(3, 5, 14));
        edges.add(new Edge(4, 5, 10));
        edges.add(new Edge(5, 6, 2));
        edges.add(new Edge(6, 7, 1));
        edges.add(new Edge(6, 8, 6));
        edges.add(new Edge(7, 8, 7));

        List<Edge> mst = kruskal(edges, 9);

        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }
}
// With efficient data structures like Union-Find,
// Kruskal's algorithm runs in O(E log E) time, where E is the number of edges.
//The algorithm sorts all the edges by weight and
// then iterates over them, adding edges to the MST if they do not create a cycle.

//Kruskal's algorithm grows the minimum spanning tree edge
//        by edge by selecting the smallest edge that does not form a cycle.

//  Prim's algorithm can be more efficient in dense graphs (where E is close to V^2)
//        because it has a better time complexity in terms of the number of vertices.

//    With a priority queue implementation, Prim's algorithm runs in O(E + V log V) time,
//        where V is the number of vertices and E is the number of edges.


//Kruskal's Algorithm: It doesn't necessarily select adjacent edges. Instead,
//        it selects the smallest edge that does not form a cycle with the edges already
//        in the spanning tree. The algorithm iterates over the edges in non-decreasing
//        order of weight and adds them to the MST if they connect two disjoint components.
//
//        Prim's Algorithm: It does indeed select adjacent edges in the sense that it
//        starts from an initial vertex and greedily adds the smallest edge that connects
//        a vertex in the MST to a vertex outside the MST. It grows the tree vertex by vertex,
//        always selecting the smallest available edge.