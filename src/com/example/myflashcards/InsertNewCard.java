package com.example.myflashcards;

import com.flashcard.domain.FlashCard;
import com.flashcard.domain.ListFlashCard;
import com.flashcard.utility.RWUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertNewCard extends AbstractActivity {

	public static final String INTENT_WORD_KEY = "WORD_TO_EDIT";
	public static final String INTENT_MEANING_KEY = "MEANING_TO_EDIT";
	public static final String INTENT_USAGE_KEY = "USAGE_TO_EDIT";
	private String word = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_new_card);

		Intent intent = getIntent();
		if(intent.getExtras() != null){
			word = intent.getExtras().getString(INTENT_WORD_KEY);
			String meaning = intent.getExtras().getString(INTENT_MEANING_KEY);
			String usage = intent.getExtras().getString(INTENT_USAGE_KEY);

			((EditText)findViewById(R.id.word)).setText(word);
			((EditText)findViewById(R.id.meaning)).setText(meaning);
			((EditText)findViewById(R.id.example)).setText(usage);

		}
	}

	public void saveWord(View view){
		FlashCard flash = new FlashCard();
		flash.setWord(((EditText)findViewById(R.id.word)).getText().toString());
		flash.setMeaning(((EditText)findViewById(R.id.meaning)).getText().toString());
		flash.setUsage(((EditText)findViewById(R.id.example)).getText().toString());

		writeOnConsole(flash.toString());

		ListFlashCard list = RWUtil.getSavedFlashCards( getSharedPreferences());

		list.delete(flash);
		
		list.add(flash);
		
		RWUtil.saveFlashCardList(list, getSharedPreferences(),this);

		Intent intent = new Intent(this,MainActivity.class);
		//intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(intent);
	}


}
