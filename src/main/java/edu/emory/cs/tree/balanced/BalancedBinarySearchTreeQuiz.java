package edu.emory.cs.tree.balanced;

import edu.emory.cs.tree.BinaryNode;
import edu.emory.cs.tree.balanced.AbstractBalancedBinarySearchTree;
public class BalancedBinarySearchTreeQuiz<T extends Comparable<T>> extends AbstractBalancedBinarySearchTree<T, BinaryNode<T>> {
    @Override
    public BinaryNode<T> createNode(T key) {
        return new BinaryNode<>(key);
    }

    @Override
    protected void balance(BinaryNode<T> node) {
        if (node.hasParent() && node.getParent().hasParent()) {
            BinaryNode<T> parent = node.getParent();
            BinaryNode<T> grandparent = node.getGrandParent();
            if (!parent.hasBothChildren() && grandparent.getRightChild() == parent) {
                BinaryNode<T> uncle = node.getUncle();
                if (uncle != null  && (uncle.hasLeftChild() || uncle.hasRightChild()) && !uncle.hasBothChildren()) {
                    if (parent.getRightChild() == node && parent.getRightChild() != null) {
                        rotateLeft(grandparent);
                    }
                    else {
                        rotateRight(parent);
                        rotateLeft(grandparent);
                    }
                    if (uncle.hasLeftChild()) {
                        rotateRight(grandparent);
                    }
                    else {
                        rotateLeft(uncle);
                        rotateRight(grandparent);
                    }
                }
            }
        }

    }


}