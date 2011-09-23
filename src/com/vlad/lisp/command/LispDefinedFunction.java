package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;

public class LispDefinedFunction extends LispAbstractCommand {

	private LispDefun mDefun;
	
	public LispDefinedFunction(LispList parameters, LispDefun defun) throws LispException {
		super(parameters, defun.getGlobal(), defun);
	}
	
	public LispDefun getDefun() {
		return mDefun;
	}
	
	@Override
	protected void init(Object... args) throws LispException {
		mDefun = (LispDefun) args[0];
	}
	
	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() - 1 != mDefun.getArguments().size()) {
			throw new LispException("Invalid arguments amount for defined function", mDefun.getName());
		}
	}
	
	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispAtom result = null;

		LispArguments localArguments = mDefun.getArguments();
		for (int i = 1; i < getParameters().size(); i++) {
			LispAtom atom = evaluateAtom(getParameters().get(i), arguments);
			localArguments.put(i - 1, atom);
		}
		
		LispArguments argumentsToExecute = new LispArguments(arguments);
		argumentsToExecute.putAll(localArguments);
		
		result = mDefun.execute(argumentsToExecute);
		
		return result;
	}
}
