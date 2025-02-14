// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)

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
    static int preind = 0;
    static HashMap<Integer,Integer> hm = new HashMap<>();
    public static treenode buildtree(int[] inorder, int[] preorder, int start, int stop) {
        if(start>stop||preind>=inorder.length) return null;
        int rv = preorder[preind++];
        treenode root = new treenode(rv);
        int inind = hm.get(rv);
        root.left = buildtree(inorder, preorder, start, inind - 1);
        root.right = buildtree(inorder, preorder, inind + 1, stop);
        return root;
    }
    public static ArrayList<Integer> spiral(treenode root, int l, int u) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<treenode> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        boolean ltr = true;
        while (!q.isEmpty() ) {
            int size = q.size();
            List<Integer> temp = new ArrayList<>();
            for (int i=0; i< size;i++) {
                treenode node = q.poll();
                temp.add(node.data);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            if (level>=l&&level<=u) {
                if(level%2==0) {
                    Collections.reverse(temp);
                }
                res.addAll(temp);
            }
            level++;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int inor[] = new int[n];
        for(int i=0;i<n;i++) {
            inor[i] = sc.nextInt();
        }
        int preor[] = new int[n];
        for(int i=0;i<n;i++) {
            preor[i] = sc.nextInt();
        }
        int l = sc.nextInt();
        int u = sc.nextInt();
        preind = 0;
        for(int i=0;i<n;i++) hm.put(inor[i],i);
        treenode tr = buildtree(inor, preor, 0, n-1);
        List<Integer> res = spiral(tr,l,u);
        for(int x : res) {
            System.out.print(x+" ");
        }
    }
}