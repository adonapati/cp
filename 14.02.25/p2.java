// Mr. Rakesh is interested in working with Data Structures.

// He has constructed a Binary Tree (BT) and asked his friend 
// Anil to check whether the BT is a self-mirror tree or not.

// Can you help Rakesh determine whether the given BT is a self-mirror tree?
// Return true if it is a self-mirror tree; otherwise, return false.

// Note:
// ------
// In the tree, '-1' indicates an empty (null) node.

// Input Format:
// -------------
// A single line of space separated integers, values at the treenode

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 2 1 1 2 3 3 2

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 2 1 1 -1 3 -1 3

// Sample Output-2:
// ----------------
// false


import java.util.*;

class BinaryTree{
    int data;
    BinaryTree left;
    BinaryTree right;
    BinaryTree(int data){
        this.data=data;
        left=null;
        right=null;
    }
}

class Solution{
    public static void main(String[]  args){
        Scanner sc=new Scanner(System.in);
        String[] arr=sc.nextLine().split(" ");
        int[] nodes=new int[arr.length];
        for(int i=0;i<nodes.length;i++){
            nodes[i]=Integer.parseInt(arr[i]);
        }
        BinaryTree root=build(nodes);
        System.out.println(symmetric(root));
    }
    public static boolean isSymmetric(BinaryTree left,BinaryTree right){
        if(left==null || right==null){
            return left==right;
        }
        if(left.data!=right.data){
            return false;
        }
        return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }
    
    public static boolean symmetric(BinaryTree root){
        if(root==null){
            return true;
        }
        return isSymmetric(root.left,root.right);
    }
    
     public static BinaryTree build(int[] levelorder){
        int n=levelorder.length;
        if(n==0){
            return null;
        }
        int i=1;
        BinaryTree root=new BinaryTree(levelorder[0]);
        Queue<BinaryTree> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty() && i<n){
            BinaryTree node=q.poll();
            if(i<n && levelorder[i]!=-1){
                node.left=new BinaryTree(levelorder[i]);
                q.add(node.left);
            }
            i++;
            if(i<n && levelorder[i]!=-1){
                node.right=new BinaryTree(levelorder[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
}