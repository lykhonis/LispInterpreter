package com.vlad.lisp;

import java.util.ArrayList;
import java.util.List;

import com.vlad.lisp.command.LispDefun;

public class LispGlobal {
	
	private List<LispDefun> mDefinedFunctions;
	
	public LispGlobal() {
		mDefinedFunctions = new ArrayList<LispDefun>();
	}
	
	public void defineFunction(LispDefun defun) {
		if (!isDefinedFunction(defun)) {
			mDefinedFunctions.add(defun);
		}
	}
	
	public LispDefun getDefinedFunctionByName(String name) {
		for (LispDefun defun : mDefinedFunctions) {
			if (defun.getName().equals(name)) {
				return defun;
			}
		}
		return null;
	}
	
	public boolean isDefinedFunction(LispDefun defun) {
		return mDefinedFunctions.contains(defun);
	}
	
	public boolean isDefinedFunction(String name) {
		return getDefinedFunctionByName(name) != null;
	}
}
