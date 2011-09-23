package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;
import com.vlad.lisp.atom.LispNumber;

public class LispNumberModulo extends LispAbstractCommand {

	public LispNumberModulo(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() != 3) {
			throw new LispException("Invalid arguments amount", this);
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
				result = (LispNumber) atom;
			} else {
				result = result.modulo((LispNumber) atom);
			}
		}

		return result;
	}
}
