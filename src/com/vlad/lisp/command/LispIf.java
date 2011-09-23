package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispBoolean;
import com.vlad.lisp.atom.LispList;

public class LispIf extends LispAbstractCommand {

	public LispIf(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() < 3 || getParameters().size() > 4) {
			throw new LispException("Invalid arguments amount", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispAtom result = null;

		LispAtom atomCondition = evaluateAtom(getParameters().get(1), arguments);
		
		if (atomCondition.isNil()) {
			if (getParameters().size() == 4) {
				result = evaluateAtom(getParameters().get(3), arguments);
			} else {
				result = new LispBoolean(false);
			}
		} else {
			result = evaluateAtom(getParameters().get(2), arguments);
		}

		return result;
	}
}
