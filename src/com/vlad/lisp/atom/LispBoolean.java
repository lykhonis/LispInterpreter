package com.vlad.lisp.atom;

import com.vlad.lisp.LispException;

public class LispBoolean extends LispAtom {

	public LispBoolean(Boolean value) throws LispException {
		super(value ? "t" : null);
	}
}
