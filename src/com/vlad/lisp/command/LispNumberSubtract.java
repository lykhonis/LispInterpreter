package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;
import com.vlad.lisp.atom.LispNumber;

public class LispNumberSubtract extends LispAbstractCommand {

	public LispNumberSubtract(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() < 2) {
			throw new LispException("Too few arguments", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispNumber result = null;

		for (int i = 1; i < getParameters().size(); i++) {
			LispAtom atom = evaluateAtom(getParameters().get(i), arguments);

			if (!atom.isNumber()) {
				throw new LispException("Invalid value, expecting for number", atom);
			}

			if (result == null) {
				if (getParameters().size() == 2) {
					result = ((LispNumber) atom).negative();
				} else {
					result = (LispNumber) atom;
				}
			} else {
				result = result.subtract((LispNumber) atom);
			}
		}

		return result;
	}
}
