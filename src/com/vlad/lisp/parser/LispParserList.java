package com.vlad.lisp.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LispParserList extends LispParserAtom implements Iterable<LispParserAtom> {
	
	private List<LispParserAtom> mChildren;

	public LispParserList(String data, int startPosition, int endPosition) {
		this(data, startPosition, endPosition, null);
	}
	
	public LispParserList(String data, int startPosition, int endPosition, LispParserAtom parent) {
		super(data, startPosition, endPosition, parent);
		
		mChildren = new ArrayList<LispParserAtom>();
	}
	
	public List<LispParserAtom> getChildren() {
		return Collections.unmodifiableList(mChildren);
	}
	
	@Override
	protected void addChild(LispParserAtom item) {
		mChildren.add(item);
	}

	@Override
	public Iterator<LispParserAtom> iterator() {
		return mChildren.iterator();
	}
}
