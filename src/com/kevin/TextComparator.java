package com.kevin;

import java.util.*;
import java.util.Map.Entry;

public class TextComparator implements Comparator<Entry<String, Integer>>{
	
	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		return o2.getValue().compareTo(o1.getValue());
	}

}
