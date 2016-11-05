package com.example.myflashcards;

import com.flashcard.utility.RWUtil;

import android.app.Activity;
import android.content.SharedPreferences;

public class AbstractActivity extends Activity {
	public AbstractActivity() {
		super();
	}
	protected SharedPreferences getSharedPreferences(){
		return getSharedPreferences(RWUtil.SHARED_PREF_KEY,MODE_PRIVATE);
	}
	
	public void writeOnConsole(String str){
		System.out.println(str);
	}
}
