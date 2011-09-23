package com.vlad.lisp;

import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;
import com.vlad.lisp.atom.LispNumber;
import com.vlad.lisp.atom.LispNumber.Type;

public class LispAtomFormatter {

	public static String toString(LispAtom atom) {
		String result = null;

		if (atom.isNil()) {
			result = "nil";
		} else if (atom.isList()) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append('(');

			for (LispAtom childAtom : (LispList) atom) {
				if (stringBuilder.length() > 1) {
					stringBuilder.append(' ');
				}
				stringBuilder.append(toString(childAtom));
			}

			stringBuilder.append(')');
			result = stringBuilder.toString();
		} else if (atom.isNumber()) {
			if (((LispNumber) atom).getType() == Type.DOUBLE) {
				result = ((LispNumber) atom).getDouble().toString();
			} else if (((LispNumber) atom).getType() == Type.INTEGER) {
				result = ((LispNumber) atom).getInteger().toString();
			}
		} else {
			result = (String) atom.getValue();
		}

		return result;
	}
}
