package com.klee2011.hackalarm;

/**
 * Created by QifangCai on 9/13/14.
 */
public class pair<L,R> {

    private final L left;
    private final R right;

    public pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof pair)) return false;
        pair pairo = (pair) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

}