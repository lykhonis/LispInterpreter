package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispBoolean;
import com.vlad.lisp.atom.LispList;

public class LispOr extends LispAbstractCommand {

	public LispOr(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() < 3) {
			throw new LispException("Too few arguments", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispBoolean result = null;
		
		for (int i = 1; i < getParameters().size(); i++) {
			LispAtom atom = evaluateAtom(getParameters().get(i), arguments);
			
			if (result == null) {
				result = new LispBoolean(!atom.isNil());
			} else {
				result = new LispBoolean(!result.isNil() || !atom.isNil());
			}
			
			if (!result.isNil()) {
				break;
			}
		}

		return result;
	}
}
