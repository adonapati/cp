// 156. Binary Tree Upside Down

// Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

// Example:

// Input: [1,2,3,4,5]

//     1
//    / \
//   2   3
//  / \
// 4   5

// Output: return the root of the binary tree [4,5,2,#,#,3,1]

//    4
//   / \
//  5   2
//     / \
//    3   1

// Clarification:

// Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.

// The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

// Here's an example:

//    1
//   / \
//  2   3
//     /
//    4
//     \
//      5

// The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].

import java.util.*;
class treenode {
    int data;
    treenode left;
    treenode right;
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
    public static void levelorder(treenode root) {
        if(root==null) return;
        Queue<treenode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                treenode curr = queue.poll();
                System.out.print(curr.data+" ");
                if(curr.left!=null) queue.add(curr.left);
                if(curr.right!=null) queue.add(curr.right);
            }
        }
    }
    public static treenode upsidedown(treenode root) {
        if(root==null) {
            return root;
        }
        if(root.left==null && root.right==null) {
            return root;
        }
        treenode newroot = upsidedown(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newroot;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] st = sc.nextLine().split(" ");
        int[] arr = new int[st.length];
        for(int i=0;i<st.length;i++) {
            arr[i] = Integer.parseInt(st[i]);
        }
        treenode root = buildtree(arr);
        treenode up = upsidedown(root);
        levelorder(up);
    }
}


// alternate (with -1):
// import java.util.*;

// class treenode {
//     int data;
//     treenode left;
//     treenode right;

//     treenode(int data) {
//         this.data = data;
//         this.left = null;
//         this.right = null;
//     }
// }

// class Solution {
//     public static treenode buildtree(int[] arr) {
//         if (arr.length == 0) return null;
//         treenode root = new treenode(arr[0]);
//         Queue<treenode> q = new LinkedList<>();
//         q.add(root);
//         int i = 1;
//         while (!q.isEmpty() && i < arr.length) {
//             treenode curr = q.poll();
//             if (i < arr.length) {
//                 curr.left = new treenode(arr[i]);
//                 q.add(curr.left);
//                 i++;
//             }
//             if (i < arr.length) {
//                 curr.right = new treenode(arr[i]);
//                 q.add(curr.right);
//                 i++;
//             }
//         }
//         return root;
//     }

//     public static void levelorder(treenode root) {
//         if (root == null) return;
//         Queue<treenode> queue = new LinkedList<>();
//         queue.add(root);
//         List<String> result = new ArrayList<>();

//         while (!queue.isEmpty()) {
//             int size = queue.size();
//             boolean allNull = true; // Track if all nodes in the level are null
            
//             for (int i = 0; i < size; i++) {
//                 treenode curr = queue.poll();
//                 if (curr == null) {
//                     result.add("-1");
//                     queue.add(null);
//                     queue.add(null);
//                 } else {
//                     result.add(String.valueOf(curr.data));
//                     if (curr.left != null || curr.right != null) {
//                         allNull = false;
//                     }
//                     queue.add(curr.left);
//                     queue.add(curr.right);
//                 }
//             }
            
//             // Stop adding null levels when all are null
//             if (allNull) break;
//         }
        
//         // Remove trailing "-1" values that are not needed
//         while (result.size() > 1 && result.get(result.size() - 1).equals("-1")) {
//             result.remove(result.size() - 1);
//         }
        
//         System.out.println(String.join(" ", result));
//     }

//     public static treenode upsidedown(treenode root) {
//         if (root == null || root.left == null) {
//             return root;
//         }

//         // Recursive call to find new root
//         treenode newRoot = upsidedown(root.left);

//         // Perform the transformation
//         root.left.left = root.right;  // Original right becomes new left
//         root.left.right = root;       // Original root becomes new right

//         // Detach the old parent
//         root.left = null;
//         root.right = null;

//         return newRoot;
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         String[] st = sc.nextLine().split(" ");
//         int[] arr = new int[st.length];
//         for (int i = 0; i < st.length; i++) {
//             arr[i] = Integer.parseInt(st[i]);
//         }
//         treenode root = buildtree(arr);
//         treenode up = upsidedown(root);
//         levelorder(up);
//     }
// }
