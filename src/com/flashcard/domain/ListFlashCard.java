package com.flashcard.domain;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * List must be saved in database
 * @author madhav
 *
 */
public class ListFlashCard {
	private LinkedHashSet<FlashCard> wholeList;

	public ListFlashCard(LinkedHashSet<FlashCard> wholeList) {
		super();
		this.wholeList = wholeList;
	}

	public ListFlashCard() { super();	}

	public ListFlashCard(FlashCard flashCard) {
		this.wholeList = new LinkedHashSet<FlashCard>();
		wholeList.add(flashCard);
	}

	public LinkedHashSet<FlashCard> getWholeList() {
		return wholeList;
	}

	public void setWholeList(LinkedHashSet<FlashCard> wholeList) {
		this.wholeList = wholeList;
	}

	public int getRandom(){
		if(wholeList != null && wholeList.size()>0){
			int index = new Random().nextInt(wholeList.size());
			return index;
		}
		
		return 0;
	}

	public FlashCard getIndex(int index){
	
		if(wholeList != null && wholeList.size()>0){
			index = index % wholeList.size();
			Iterator<FlashCard> itr = wholeList.iterator();

			for (int i = 0; i < index; i++) {
				itr.next();				
			}
			return itr.next() ;		
		}

		return new FlashCard();
	}

	public void add(FlashCard object) {
		wholeList = wholeList == null ? new LinkedHashSet<FlashCard>():wholeList;
		wholeList.add(object);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListFlashCard [wholeList=");
		builder.append(wholeList);
		builder.append("]");
		return builder.toString();
	}

	public int size() {
		return (wholeList == null ? new LinkedHashSet<FlashCard>():wholeList).size();
	}
	
	public void delete(FlashCard flash) {
		if(wholeList != null && wholeList.size()>0){
			wholeList.remove(flash);
			
		}
	}
	
}
