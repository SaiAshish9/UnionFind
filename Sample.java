package com.sai.designPatterns.unionFind;

// Union-Find, also known as Disjoint-Set data structure, is a fundamental concept in computer
// science used to solve problems involving the grouping of elements into disjoint sets
// and efficiently performing operations on these sets.
// The basic operations supported by Union-Find data structure are:
// MakeSet(x): Creates a new set containing the element x.
// Union(x, y): Merges the sets containing elements x and y into a single set.
// Find(x): Finds the representative (also called leader or root) of the set containing element x.
// Disjoint Set Union (DSU)/Union-Find

public class Sample {

    int[] representative;
    int[] size;

    int find(int u) {
        if (u == representative[u])
            return u;

        else
            return find(representative[u]);
    }

    void combine(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v)    // already in the same set so no action needed
            return;
        else
            representative[v] = u;
    }

//    Path Compression:
//    When we call find() function we traverse the path from given node ‘u’ all
//    upto its representative what if we can set the representative of all the nodes in the path while finding the representative for ‘u’;

    int findOptimal(int u) {
        if (u == representative[u])
            return u;

        else
            return representative[u] = find(representative[u]);
    }

//    Union by Size:

//    We can rectify this problem, we just need to keep the height of the tree as minimum as possible.
//    Remember when we called combine(u,v) we always make u as the representative of v,
//    however we could have done opposite as well. We can reduce the tree height by comparing
//    the size of sets we are going to
//    combine and always make the bigger set as the representative of the smaller.
//    DSU with union by size works in O(log N) which is fast enough to solve almost all problems,

    void combineOptimal(int u, int v) {
        u = find(u);
        v = find(v);

        if (u == v)
            return;

        else {
            if (size[u] > size[v]) {
                representative[v] = u;
                size[u] += size[v];
            } else {
                representative[u] = v;
                size[v] += size[u];
            }

        }
    }

}
