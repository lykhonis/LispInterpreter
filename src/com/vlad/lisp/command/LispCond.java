package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;

public class LispCond extends LispAbstractCommand {

	public LispCond(LispList parameters, LispGlobal global) throws LispException {
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
		LispAtom result = null;

		for (int i = 1; i < getParameters().size(); i++) {
			LispAtom atomListCondition = getParameters().get(i);
			
			if (!(atomListCondition.isList() && !atomListCondition.isNil())) {
				throw new LispException("Invalid value, expecting for list");
			}
			
			LispAtom atomCondition = evaluateAtom(((LispList) atomListCondition).get(0), arguments);

			if (!atomCondition.isNil()) {
				result = evaluateAtom(((LispList) atomListCondition).get(1), arguments);
				break;
			}
		}

		return result;
	}
}
