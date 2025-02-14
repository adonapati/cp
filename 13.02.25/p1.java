// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

import java.util.*;

class treenode {
    int data;
    treenode left, right;
    treenode(int data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }
}

class Solution {
    public static treenode buildtree(int[] preorder, Map<Integer, Integer> hmap, int prestart, int preend, int poststart) {
        if (prestart > preend) return null;
        treenode root = new treenode(preorder[prestart]);
        if (prestart == preend) return root;
        int lv = preorder[prestart + 1];
        int lind = hmap.get(lv);
        int ls = lind - poststart + 1;
        root.left = buildtree(preorder, hmap, prestart+1, prestart+ls, poststart);
        root.right = buildtree(preorder, hmap, prestart+ls+1, preend, lind + 1);
        return root;
    }
    public static List<List<Integer>> lev(treenode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<treenode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levsize = queue.size();
            List<Integer> curr = new ArrayList<>();
            for (int i = 0; i < levsize; i++) {
                treenode node = queue.poll();
                curr.add(node.data);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(curr);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] pre = sc.nextLine().split(" ");
        String[] pos = sc.nextLine().split(" ");
        int[] preorder = new int[pre.length];
        int[] postorder = new int[pos.length];
        for (int i = 0; i < preorder.length; i++) {
            preorder[i] = Integer.parseInt(pre[i]);
            postorder[i] = Integer.parseInt(pos[i]);
        }
        Map<Integer, Integer> hmap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            hmap.put(postorder[i], i);
        }
        treenode root = buildtree(preorder, hmap, 0, preorder.length - 1, 0);
        System.out.println(lev(root));
    }
}