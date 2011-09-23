package com.vlad.lisp.parser;

public class LispParserAtom {

	private LispParserAtom mParent;
	private String mData;
	private int mStartPosition;
	private int mEndPosition;
	
	public LispParserAtom(String data, int startPosition, int endPosition) {
		this(data, startPosition, endPosition, null);
	}

	public LispParserAtom(String data, int startPosition, int endPosition, LispParserAtom parent) {
		mData = data;
		mParent = parent;
		mStartPosition = startPosition;
		mEndPosition = endPosition;
		
		if (mParent != null) {
			mParent.addChild(this);
		}
	}
	
	protected void addChild(LispParserAtom item) {	
	}

	public LispParserAtom getParent() {
		return mParent;
	}
	
	public int getEndPosition() {
		return mEndPosition;
	}
	
	public int getStartPosition() {
		return mStartPosition;
	}

	public void setEndPosition(int endPosition) {
		mEndPosition = endPosition;
	}
	
	public void setStartPosition(int startPosition) {
		mStartPosition = startPosition;
	}

	public String getValue() {
		return mData.substring(mStartPosition, mEndPosition + 1);
	}
}
