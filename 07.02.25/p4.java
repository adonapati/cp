// You are developing an application for a garden management system where each tree 
// in the garden is represented as a binary tree structure. The system needs to 
// allow users to plant new trees in a systematic way, ensuring that each tree is 
// filled level by level.

// A gardener wants to:
//  - Plant trees based on user input.
//  - Ensure trees grow in a balanced way by filling nodes level by level.
//  - Inspect the garden layout by performing an in-order traversal, which helps 
//    analyze the natural arrangement of trees.

// Your task is to implement a program that:
//     - Accepts a list of N tree species (as integers).
//     - Builds a binary tree using level-order insertion.
//     - Displays the in-order traversal of the tree.

// Input Format:
// -------------
// - An integer N representing the number of tree plants.
// - A space-separated list of N integers representing tree species.

// Output Format:
// --------------
// A list of integers, in-order traversal of tree.


// Sample Input:
// -------------
// 7
// 1 2 3 4 5 6 7

// Sample Output:
// --------------
// 4 2 5 1 6 3 7


// Explanation:
// ------------
// The tree looks like this:

//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// The in order is : 4 2 5 1 6 3 7

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


class p4 {
    treenode root;
    public treenode buildtree(int n, int[] tree) {
        if(n==0) return null;
        treenode root = new treenode(tree[0]);
        Queue<treenode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty()&&i<n) {
            treenode curr = q.poll();
            if(i<n) {
                curr.left = new treenode(tree[i]);
                q.add(curr.left);
                i++;
            }
            if(i<n) {
                curr.right = new treenode(tree[i]);
                q.add(curr.right);
                i++;
            }
        }
        return root;
    }
    public void inorder(treenode root) {
            if(root==null) return;
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        
        }
        public static void main(String[] args) {
            p4 sol = new p4();
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int tree[] = new int[n];
            for(int i=0;i<n;i++) {
                tree[i] = sc.nextInt();
            }
            sol.root = sol.buildtree(n,tree);
            sol.inorder(sol.root);
            sc.close();
        
    }
}
