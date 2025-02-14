// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]

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
    public static treenode buildtree(int[] arr) {
        if(arr.length==0) return null;
        treenode root = new treenode(arr[0]);
        Queue<treenode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty()&&i<arr.length) {
            treenode curr = q.poll();
            if(i<arr.length && arr[i] != -1) {
                curr.left = new treenode(arr[i]);
                q.add(curr.left);
            }
            i++;
            if(i<arr.length && arr[i] != -1) {
                curr.right = new treenode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
    public static List<Integer> right(treenode root, int dep, List<Integer> res) {
        if(root==null || root.data==-1) return null;
        if(dep==res.size()) res.add(root.data);
        right(root.right, dep+1, res);
        right(root.left, dep+1, res);
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ar = sc.nextLine().split(" ");
        int[] arr = new int[ar.length];
        for(int i=0;i<ar.length;i++) {
            arr[i] = Integer.parseInt(ar[i]);
        }
        treenode root = buildtree(arr);
        List<Integer> res = new ArrayList<>();
        List<Integer> ans = right(root, 0, res);
        System.out.println(ans);
    }
}