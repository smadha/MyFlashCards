package com.flashcard.utility;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.flashcard.domain.ListFlashCard;
import com.google.gson.Gson;

public class RWUtil {

	public static final String SAVED_FLASH_CARD = "SAVED_DATA";
	public static final String SHARED_PREF_KEY = "SHARED_PREF_MYFLASHCARD";
	public static final String FILE_NAME = "MyFlashCardData.txt";
	public static final File FILE_DIR = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) ;

	/**
	 * @param jsonStr
	 * @param sharedPref
	 */
	public static void saveFlashCardList(final ListFlashCard obj, final SharedPreferences sharedPref
			,final Context context) {
		System.out.println("saveFlashCardList called, new size: " + obj.size());
		String jsonStr = new Gson().toJson(obj);
		
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(SAVED_FLASH_CARD, jsonStr);
		editor.commit();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				saveJsonToFile(sharedPref, context);
				
			}
		}).start();
	}
	
	/**
	 * @param sharedPref
	 * @return
	 */
	public static ListFlashCard getSavedFlashCards(SharedPreferences sharedPref) {
		System.out.println("getSavedFlashCards called");

		Gson gson = new Gson();
		String jsonStr = getFlashCardJson(sharedPref);
		ListFlashCard listOfCards = gson.fromJson(jsonStr, ListFlashCard.class);
		
		listOfCards = listOfCards==null?new ListFlashCard():listOfCards;
		System.out.println("saved listOfCards size: " + listOfCards.size());
		
		return listOfCards;
	}

	/**
	 * @param sharedPref
	 * @return
	 */
	public static String getFlashCardJson(SharedPreferences sharedPref) {
		return sharedPref.getString(SAVED_FLASH_CARD, "");
	}
	
	public static int saveJsonToFile(SharedPreferences sharedPref, Context context){
		/*try {
			FileOutputStream out = context.openFileOutput(FILE_NAME, Context.MODE_WORLD_WRITEABLE);
			out.write(getFlashCardJson(sharedPref).getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		File f = new File(FILE_DIR, RWUtil.FILE_NAME);
		try {
			f.createNewFile();

			FileOutputStream out = new FileOutputStream(f);
			out.write(getFlashCardJson(sharedPref).getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}
}
