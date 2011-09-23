package com.vlad.lisp.atom;

import com.vlad.lisp.LispException;

public class LispDouble extends LispAtom {

	private static Object correctValue(Object value) throws LispException {
		Object result = null;
		
		if (value instanceof String) {
			try {
				result = new Double((String) value);
			} catch (NumberFormatException e) {
				throw new LispException(e.getMessage(), value);
			}
		} else if (value instanceof Double) {
			result = (Double) value;
		} else {
			throw new LispException("Invalid value for double", value);
		}
		
		return result;
	}
	
	public LispDouble(Object value) throws LispException {
		super(correctValue(value));
	}

	public static boolean isValid(Object value) {
		if (value instanceof Double) {
			return true;
		}
		if (value instanceof String) {
			try {
				new Double((String) value);
				return true;
			} catch (NumberFormatException e) {
			}
		}
		return false;
	}

	public Double getDouble() {
		return (Double) getValue();
	}
}
