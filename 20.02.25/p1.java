// You are a gardener designing a beautiful floral pathway in a vast botanical 
// garden. The garden is currently overgrown with plants, trees, and bushes 
// arranged in a complex branching structure, much like a binary tree. Your task 
// is to carefully prune and rearrange the plants to form a single-file walking 
// path that visitors can follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, 
//        where the right branch connects to the next plant in the sequence, 
//        and the left branch is always trimmed (set to null).
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print the list.


// Sample Input:
// -------------
// 1 2 5 3 4 -1 6

// Sample Output:
// --------------
// 1 2 3 4 5 6


// Explanation:
// ------------
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6

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
    static treenode temp = null;
    public static treenode buildtree(int[] arr) {
        int n = arr.length;
        if(n==0) return null;
        int i=1;
        treenode root = new treenode(arr[0]);
        Queue<treenode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty() && i<n) {
            treenode node = q.poll();
            if(i<n && arr[i]!= -1) {
                node.left = new treenode(arr[i]);
                q.add(node.left);
            }
            i++;
            if(i<n && arr[i]!=-1)  {
                node.right = new treenode(arr[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    public static void treetolist(treenode root) {
        if(root==null) return;
        treetolist(root.right);
        treetolist(root.left);
        root.left = null;
        root.right = temp;
        temp = root;
    }
    public static void inor(treenode root) {
        if(root==null) return;
        inor(root.left);
        System.out.print(root.data + " ");
        inor(root.right);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] st = sc.nextLine().split(" ");
        int[] arr = new int[st.length];
        for(int i=0;i<st.length;i++) {
            arr[i] = Integer.parseInt(st[i]);
        }
        treenode root = buildtree(arr);
        treetolist(root);
        inor(root);
        
    }
}