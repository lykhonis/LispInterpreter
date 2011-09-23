package com.vlad.lisp.atom;

public class LispString extends LispAtom {

	public LispString(Object value) {
		super(value);
	}

	public String getString() {
		return (String) getValue();
	}
}
