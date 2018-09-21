//Leetcode 124. Binary Tree Maximum Path Sum
//Tree, DFS, Divide & Conquer
//Hard

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Solution 1: Divide and Conquer
//Define a new data type: ResultType

class Solution {
    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        // maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath; 
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    } 
    
    private ResultType helper(TreeNode root) {
        if (root == null){
            return new ResultType(0, Integer.MIN_VALUE);
        }
        
        //Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        //Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0); //sometimes the value can be negative, we need to avoid add it to our path.
        
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        
        return new ResultType(singlePath, maxPath);
    }
    
    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
}

//Solution 2: Divide and Conquer
//Use a public outside variable "result"
//Special way to call functions and give result values during the function

class Solution {
    int result = Integer.MIN_VALUE; //make a outside variable
    public int maxPathSum(TreeNode root) {
        helper(root); 
        //run the function but don't use the final return value
        //only save the "result" when we run the function
        return result;
    }
    
    public int helper(TreeNode root){
        if(root == null){
            return 0;
        }
        //Divide
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        //Conquer
        result = Math.max(result, left + right + root.val); //find the maximum path result
        
        return Math.max(left,right) + root.val;
    }
}
