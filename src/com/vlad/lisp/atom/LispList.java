package com.vlad.lisp.atom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LispList extends LispAtom implements Iterable<LispAtom> {

	public LispList() {
		super(Collections.unmodifiableList(new ArrayList<LispAtom>()));
	}
	
	public LispList(List<LispAtom> items) {
		super(Collections.unmodifiableList(new ArrayList<LispAtom>(items)));
	}
	
	@SuppressWarnings("unchecked")
	private List<LispAtom> getItems() {
		return (List<LispAtom>) getValue();
	}

	@Override
	public Iterator<LispAtom> iterator() {
		return getItems().iterator();
	}
	
	public boolean isEmpty() {
		return getItems().isEmpty();
	}

	public int size() {
		return getItems().size();
	}
	
	public LispAtom get(int index) {
		return getItems().get(index);
	}
}
