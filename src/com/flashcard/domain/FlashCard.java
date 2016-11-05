package com.flashcard.domain;

import java.util.Date;


public class FlashCard {

	private String word;
	private String meaning;
	private String usage;
	
	private int numberOfViews;
	private int correctAttempt;
	
	private Date insertDate;
	private Date lastSeenDate;
	
	public FlashCard() {
		word ="----";
		meaning = "----";
		usage = "----";
	}

	public FlashCard(String word, String meaning, String usage,
			int numberOfViews, int correctAttempt, Date insertDate,
			Date lastSeenDate) {
		super();
		this.word = word;
		this.meaning = meaning;
		this.usage = usage;
		this.numberOfViews = numberOfViews;
		this.correctAttempt = correctAttempt;
		this.insertDate = insertDate;
		this.lastSeenDate = lastSeenDate;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public int getNumberOfViews() {
		return numberOfViews;
	}

	public void setNumberOfViews(int numberOfViews) {
		this.numberOfViews = numberOfViews;
	}

	public int getCorrectAttempt() {
		return correctAttempt;
	}

	public void setCorrectAttempt(int correctAttempt) {
		this.correctAttempt = correctAttempt;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getLastSeenDate() {
		return lastSeenDate;
	}

	public void setLastSeenDate(Date lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlashCard [word=");
		builder.append(word);
		builder.append(", meaning=");
		builder.append(meaning);
		builder.append(", usage=");
		builder.append(usage);
		builder.append(", numberOfViews=");
		builder.append(numberOfViews);
		builder.append(", correctAttempt=");
		builder.append(correctAttempt);
		builder.append(", insertDate=");
		builder.append(insertDate);
		builder.append(", lastSeenDate=");
		builder.append(lastSeenDate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlashCard other = (FlashCard) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	

}
