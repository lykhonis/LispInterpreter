package com.vlad.lisp.atom;

import com.vlad.lisp.LispException;

public class LispInteger extends LispAtom {

	private static Object correctValue(Object value) throws LispException {
		Object result = null;
		
		if (value instanceof String) {
			try {
				result = new Integer((String) value);
			} catch (NumberFormatException e) {
				throw new LispException(e.getMessage(), value);
			}
		} else if (value instanceof Integer) {
			result = (Integer) value;
		} else {
			throw new LispException("Invalid value for integer", value);
		}
		
		return result;
	}
	
	public LispInteger(Object value) throws LispException {
		super(correctValue(value));
	}

	public static boolean isValid(Object value) {
		if (value instanceof Integer) {
			return true;
		}
		if (value instanceof String) {
			try {
				new Integer((String) value);
				return true;
			} catch (NumberFormatException e) {
			}
		}
		return false;
	}

	public Integer getInteger() {
		return (Integer) getValue();
	}
}
