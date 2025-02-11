// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7

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
    treenode root;
    public treenode buildtree(int n, int[] tree) {
        if (n==0 || tree[0]==-1) return null;
        treenode root = new treenode(tree[0]);
        Queue<treenode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty()&&i<n) {
            treenode curr = q.poll();
            if(i<n && tree[i]!=-1) {
                curr.left = new treenode(tree[i]);
                q.add(curr.left);
            }
            i++;
            if(i<n && tree[i]!=-1) {
                curr.right = new treenode(tree[i]);
                q.add(curr.right);
            }
                i++;
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
            Solution sol = new Solution();
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
