package _05.二叉树;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/invert-binary-tree/
public class _226_翻转二叉树 {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) return root;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		
		return root;
    }
	
	public TreeNode invertTreeWithLevel(TreeNode root) {
		if (root == null) return root;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		
		return root;
	}
}
