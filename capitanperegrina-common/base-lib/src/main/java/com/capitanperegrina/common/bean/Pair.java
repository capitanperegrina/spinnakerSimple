package com.capitanperegrina.common.bean;

import java.io.Serializable;

public class Pair<K,V> implements Serializable {

	private static final long serialVersionUID = 7466344278532502488L;

	public K val1;
	public V val2;

	public Pair() {
		super();
	}

	public Pair(final K val1, final V val2) {
		super();
		this.val1 = val1;
		this.val2 = val2;
	}

	public K getVal1() {
		return this.val1;
	}

	public void setVal1(final K val1) {
		this.val1 = val1;
	}

	public V getVal2() {
		return this.val2;
	}

	public void setVal2(final V val2) {
		this.val2 = val2;
	}

	@Override
	public String toString() {
		return "Pair [val1=" + this.val1 + ", val2=" + this.val2 + "]";
	}
}
