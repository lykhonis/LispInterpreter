package com.vlad.lisp.command;

import com.vlad.lisp.LispArguments;
import com.vlad.lisp.LispException;
import com.vlad.lisp.LispGlobal;
import com.vlad.lisp.atom.LispAtom;
import com.vlad.lisp.atom.LispList;

public class LispDefun extends LispAbstractCommand {

	private String mName;
	private LispArguments mArguments;

	public LispDefun(LispList parameters, LispGlobal global) throws LispException {
		super(parameters, global);
	}
	
	@Override
	protected void init(Object... args) throws LispException {
		mName = (String) getParameters().get(1).getValue();
		mArguments = new LispArguments();
		
		for (LispAtom atom : (LispList) getParameters().get(2)) {
			mArguments.put((String) atom.getValue(), null);
		}
		
		getGlobal().defineFunction(this);
	}

	@Override
	public void validateParameters() throws LispException {
		if (getParameters().size() != 4) {
			throw new LispException("Invalid arguments amount", this);
		}
	}

	@Override
	public LispAtom execute(LispArguments arguments) throws LispException {
		LispAtom result = null;

		LispAtom atom = getParameters().get(3);
		LispCommand command = LispCommandFactory.create(atom, getGlobal());
		result = command.execute(arguments);

		return result;
	}

	public String getName() {
		return mName;
	}

	public LispArguments getArguments() {
		return mArguments;
	}
}
