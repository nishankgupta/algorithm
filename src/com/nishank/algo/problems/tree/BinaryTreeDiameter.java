package com.nishank.algo.problems.tree;

import com.nishank.algo.bean.TreeNode;

/**
 * Find the max length between two leaf nodes in a binary tree i.e diameter of a binary tree
 *
 * Soln: At each node, recusively get the max length from left and right subtree. Compare the sum of left and right length
 * with max dia and return the max length+1
 */
public class BinaryTreeDiameter {

    private static int maxDia = 0;

    public static Integer maxDia(TreeNode<String> node) {

        if (node == null) {
            return 0;
        }

        int leftDia = maxDia(node.getLeftNode());
        int rightDia = maxDia(node.getRightNode());

        int nodeDia = leftDia + rightDia + 1;

        if (nodeDia > maxDia) {
            maxDia = nodeDia;
        }

        return Math.max(leftDia, rightDia) + 1;

    }


    public static void main(String[] args) {
        TreeNode<String> rootNode = getTree();
        int rootDia = maxDia(rootNode);

        System.out.println("Diameter of the tree is: " + maxDia);
    }

    private static TreeNode<String> getTree() {
        TreeNode<String> rootNode = new TreeNode<>();
        rootNode.setData("root");

        TreeNode<String> childNode = new TreeNode<>();
        childNode.setData("root right child");
        rootNode.setRightNode(childNode);

        childNode = new TreeNode<>();
        childNode.setData("root left child");
        rootNode.setLeftNode(childNode);

        return rootNode;
    }

}
