package ru.nol.qbook.lingu;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	private final Map<String, WordForm> wordForms = new HashMap<String, WordForm>();

	public void addWord(String singleForm, String severalForm, String manyForm) {
		wordForms.put(singleForm, new WordForm(singleForm, severalForm,	manyForm));
	}

	public String getForm(String word, int number) {
		final WordForm wordForm = wordForms.get(word);
		if (wordForm == null)
			return null;
		final int lastDigit = number % 10;
		final boolean teen = number %100 <20 && number%100>10;
		if( teen ) return wordForm.getMany();
		if( lastDigit == 1) return wordForm.getSingle();
		if( lastDigit < 5 && lastDigit > 1) return wordForm.getSeveral();
		return wordForm.getMany();
	}

	protected class WordForm {
		private final String single, several, many;

		public WordForm(String single, String several, String many) {
			super();
			this.single = single;
			this.several = several;
			this.many = many;
		}

		public String getSingle() {
			return single;
		}

		public String getSeveral() {
			return several;
		}

		public String getMany() {
			return many;
		}
	}

}
