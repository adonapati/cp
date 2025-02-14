// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.

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
            if(i<arr.length) {
                curr.left = new treenode(arr[i]);
                q.add(curr.left);
                i++;
            }
            if(i<arr.length) {
                curr.right = new treenode(arr[i]);
                q.add(curr.right);
                i++;
            }
        }
        return root;
    }
    public static int finddist(treenode root, int e) {
        if(root==null) return -1;
        int dis = -1;
        
        if((root.data==e) || (dis = finddist(root.left, e)) >= 0 || (dis = finddist(root.right, e)) >= 0 ) return dis+1;
        return dis;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] st = sc.nextLine().split(" ");
        int[] arr = new int[st.length];
        for(int i=0;i<st.length;i++) {
            arr[i] = Integer.parseInt(st[i]);
        }
        int e1 = sc.nextInt();
        int e2 = sc.nextInt();
        treenode root = buildtree(arr);
        if(e1 == e2) {
            System.out.println(0);
            return;
        }
        int d1 = finddist(root, e1);
        int d2 = finddist(root, e2);
        System.out.println(d1+d2);
    }
}