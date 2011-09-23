package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;

public class LispLet extends LispAbstractCommand {

	public LispLet(LispList parameters, LispGlobal global) throws LispException {
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
		LispArguments localArguments = new LispArguments(arguments);

		for (LispAtom atom : (LispList) getParameters().get(1)) {
			LispAtom name = ((LispList) atom).get(0);
			LispAtom value = evaluateAtom(((LispList) atom).get(1), localArguments);
			
			localArguments.put((String) name.getValue(), value);
		}
		
		for (int i = 2; i < getParameters().size(); i++) {
			LispAtom atom = getParameters().get(i);
			LispCommand command = LispCommandFactory.create(atom, getGlobal());
			result = command.execute(localArguments);
		}

		return result;
	}
}
