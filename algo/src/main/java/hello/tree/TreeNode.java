package hello.tree;

/* AVL 树节点类 */
class TreeNode {
    public int val;        // 节点值
    public int height;     // 节点高度
    public TreeNode left;  // 左子节点
    public TreeNode right; // 右子节点
    public TreeNode(int x) { val = x; }
}