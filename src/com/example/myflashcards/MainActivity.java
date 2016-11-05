package com.example.myflashcards;

import java.io.File;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flashcard.domain.FlashCard;
import com.flashcard.domain.ListFlashCard;
import com.flashcard.utility.RWUtil;

/**
 * First View: Show stored flash card- MainActivity Second View: Take User Input
 * 
 * @author madhav
 * 
 */

public class MainActivity extends AbstractActivity {

	private boolean isRandom = false;
	private static int curIndex = -1;
	private ListFlashCard listOfCards;
	private FlashCard currCard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// mockTestData();

		listOfCards = getFromStorage();

		getNextCard(listOfCards);

		//File file = new File(getFilesDir(), RWUtil.FILE_NAME);
		File file = new File(RWUtil.FILE_DIR , RWUtil.FILE_NAME);

		TextView view = (TextView) findViewById(R.id.fileName);
		view.setText(file.getAbsolutePath() + ": " + file.getTotalSpace());

	}

	private ListFlashCard getFromStorage() {
		SharedPreferences sharedPref = getSharedPreferences();
		ListFlashCard listOfCards = RWUtil.getSavedFlashCards(sharedPref);
		return listOfCards;
	}

	private void getNextCard(ListFlashCard listOfCards) {
		FlashCard cardOnDisplay;

		if (isRandom) {
			cardOnDisplay = getRandom();
		} else {
			curIndex = curIndex >= listOfCards.size() - 1 ? 0 : curIndex + 1;
			cardOnDisplay = listOfCards.getIndex(curIndex);
		}
		paintNewCard(cardOnDisplay, true, true);

	}
	
	private FlashCard getRandom() {
		int rand = listOfCards.getRandom();
		curIndex = rand;
		return listOfCards.getIndex(rand);		
	}

	private void getPrevCard(ListFlashCard listOfCards) {
		FlashCard cardOnDisplay;

		if (isRandom) {
			cardOnDisplay = getRandom();
		} else {
			curIndex = curIndex <= 0 ? listOfCards.size() - 1 : curIndex - 1;
			cardOnDisplay = listOfCards.getIndex(curIndex);
		}
		paintNewCard(cardOnDisplay, true, true);

	}

	private void paintNewCard(FlashCard cardOnDisplay, boolean hideMeaning, boolean hideHint) {
		currCard = cardOnDisplay;
		TextView view = (TextView) findViewById(R.id.word);
		view.setText(cardOnDisplay.getWord());
		
		if(!hideMeaning){
			setMeaning(cardOnDisplay);
		}else{
			setMeaning(new FlashCard());
		}
	}

	/**
	 * @param cardOnDisplay
	 */
	public void setMeaning(FlashCard cardOnDisplay) {
		TextView view;
		view = (TextView) findViewById(R.id.meaning);
		view.setText(cardOnDisplay.getMeaning());

		view = (TextView) findViewById(R.id.example);
		view.setText(cardOnDisplay.getUsage());
	}

	/*
	 * private void mockTestData() { writeOnConsole("Mocking data");
	 * ListFlashCard list = new ListFlashCard(new FlashCard("vociforus",
	 * "unplesantly Loud",
	 * "Him being vociforus talk on his cellphone annoyed everyone", 0, 0, new
	 * Date(), new Date()));
	 * 
	 * 
	 * // to write SharedPreferences sharedPref = getSharedPreferences();
	 * 
	 * RWUtil.saveFlashCardList(list, sharedPref,this); }
	 */

	public void showNext(View showSavedCard) {
		writeOnConsole(" showNext success");
		getNextCard(listOfCards);
	}
	
	public void showPrev(View showSavedCard) {
		writeOnConsole(" showPrev success");
		getPrevCard(listOfCards);
	}
	
	public void showMeaning(View showSavedCard) {
		writeOnConsole(" showMeaning success");
		paintNewCard(currCard,false,false);
	}
	
	public void toggleRandom(View showSavedCard) {
		writeOnConsole(" toggleRandom success");
		isRandom = !isRandom;

		Button view = (Button) findViewById(R.id.toggleRandom);
		view.setText("Random: " + isRandom);

	}

	public void insertNewCard(View showSavedCard) {
		writeOnConsole(" Navigating to insertNewCard");
		moveToInsertCard(true);
	}

	public void editCard(View showSavedCard) {
		writeOnConsole(" Navigating to editCard");
		moveToInsertCard(false);
	}

	public void moveToInsertCard(boolean isInsert) {
		Intent intent = new Intent(this, InsertNewCard.class);
		// intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		if (!isInsert) {
			TextView text = (TextView) findViewById(R.id.word);
			String message = text.getText().toString();
			intent.putExtra(InsertNewCard.INTENT_WORD_KEY, message);

			text = (TextView) findViewById(R.id.meaning);
			message = text.getText().toString();
			intent.putExtra(InsertNewCard.INTENT_MEANING_KEY, message);

			text = (TextView) findViewById(R.id.example);
			message = text.getText().toString();
			intent.putExtra(InsertNewCard.INTENT_USAGE_KEY, message);

		}
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		listOfCards = getFromStorage();

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		listOfCards = getFromStorage();

	}

	public boolean isRandom() {
		return isRandom;
	}

	public int getCurIndex() {
		return curIndex;
	}

	public boolean setIsRandom() {
		return isRandom;
	}

	public int setCurIndex() {
		return curIndex;
	}

}
