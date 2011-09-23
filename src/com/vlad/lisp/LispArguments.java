package com.vlad.lisp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vlad.lisp.atom.LispArgument;
import com.vlad.lisp.atom.LispAtom;

public class LispArguments implements Iterable<LispArgument> {

	private List<LispArgument> mItems;

	public LispArguments() {
		mItems = new ArrayList<LispArgument>();
	}

	public LispArguments(LispArguments arguments) {
		mItems = new ArrayList<LispArgument>(arguments.mItems);
	}

	public int size() {
		return mItems.size();
	}

	public void putAll(LispArguments arguments) {
		for (LispArgument argument : arguments) {
			put(argument);
		}
	}

	public void put(LispArgument argument) {
		int i = getIndexByName(argument.getName());
		if (i >= 0) {
			mItems.set(i, argument);
		} else {
			mItems.add(argument);
		}
	}

	public void put(int index, LispAtom atom) {
		LispArgument argument = get(index);
		mItems.set(index, new LispArgument(argument.getName(), atom));
	}

	public void put(String name, LispAtom atom) {
		put(new LispArgument(name, atom));
	}

	public LispArgument get(int index) {
		return mItems.get(index);
	}

	public boolean contains(String name) {
		return getByName(name) != null;
	}

	public boolean contains(LispArgument argument) {
		return argument != null && contains(argument.getName());
	}

	private int getIndexByName(String name) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public LispArgument getByName(String name) {
		for (LispArgument argument : mItems) {
			if (argument.getName().equals(name)) {
				return argument;
			}
		}
		return null;
	}

	@Override
	public Iterator<LispArgument> iterator() {
		return mItems.iterator();
	}
}
