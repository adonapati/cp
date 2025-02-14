// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7

import java.util.*;

class treenode {
    int data;
    treenode left, right;
    treenode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    static int postindex;
    public static treenode buildtree(int[] inorder, int[] postorder, Map<Integer,Integer> inmap, int start, int stop) {
        if(start>stop||postindex<0) return null;
        int rootvalue = postorder[postindex--];
        treenode root = new treenode(rootvalue);
        int inind = inmap.get(rootvalue);
        root.right = buildtree(inorder, postorder, inmap, inind+1, stop);
        root.left = buildtree(inorder, postorder, inmap, start, inind-1);
        return root;
    }
    public static ArrayList<Integer> printlevel(int l, int u, treenode btree) {
        ArrayList<Integer> res = new ArrayList<>();
        if (btree == null) return res;
        Queue<treenode> q = new LinkedList<>();
        q.add(btree);
        int level = 1;
        while(!q.isEmpty() ) {
            int size = q.size();
            if(level>=l&&level<=u) {
                for (int i = 0; i < size; i++) {
                    treenode node = q.poll();
                    res.add(node.data);
                    if (node.left != null) {
                        q.add(node.left);
                    }
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
            }
            else {
                for (int i = 0; i < size; i++) {
                    treenode node = q.poll();
                    if (node.left != null) q.add(node.left);
                    if (node.right != null) q.add(node.right);
                }
            }
            if (level>u) break;
            level++;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] postorder = new int[n];
        for(int i=0;i<n;i++) {
            inorder[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++) {
            postorder[i] = sc.nextInt();
        }
        postindex = n-1;
        Map<Integer,Integer> inmap = new HashMap<>();
        for(int i=0;i<n;i++) {
            inmap.put(inorder[i],i);
        }
        int q = sc.nextInt();
        treenode btree = buildtree(inorder, postorder, inmap, 0, n-1);
        while(q-- > 0) {
            int l = sc.nextInt();
            int u = sc.nextInt();
            System.out.println(printlevel(l,u,btree));
        }
    }
}