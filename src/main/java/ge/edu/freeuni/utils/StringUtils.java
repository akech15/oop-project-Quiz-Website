package ge.edu.freeuni.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class StringUtils {

	public static boolean isEmpty(String s) {
		return s == null || s.trim().isEmpty();
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static String listToString(List<String> list, char delimiter){
		String result = "";
		for (int i = 0; i < list.size() - 1; i++) {
			result += list.get(i);
			if(i == list.size() - 2){
				continue;
			}
			result += delimiter;
		}
		return result;
	}

	public static List<String> stringToList(String str, char delimiter){
		List<String> result = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, String.valueOf(delimiter));
		while (tokenizer.hasMoreTokens()){
			result.add(tokenizer.nextToken());
		}
		return result;
	}



}
