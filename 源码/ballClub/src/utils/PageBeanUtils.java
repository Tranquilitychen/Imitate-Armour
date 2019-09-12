package utils;

import java.util.ArrayList;
import java.util.List;

public class PageBeanUtils {

	private static List<String> type_list=new ArrayList<String>();
	private static List<String> all_list=new ArrayList<String>();
	private static List<String> pattern_list=new ArrayList<String>();
	static {
		
		type_list.add("1");
		type_list.add("2");
		type_list.add("3");
		type_list.add("4");
		type_list.add("5");
		all_list.add("6");
		pattern_list.add("7");
		
	}
	public static List<String> getType_list() {
		return type_list;
	}
	public static List<String> getAll_list() {
		return all_list;
	}
	public static List<String> getPattern_list() {
		return pattern_list;
	}
	
	
	
	
	
}
