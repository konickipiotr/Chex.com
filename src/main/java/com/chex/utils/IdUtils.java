package com.chex.utils;

import java.util.ArrayList;
import java.util.List;

public class IdUtils {

	public static String getContinentId(String idplace) {
		
		return idplace.substring(0, 2)+"0000000000000";
	}

	public static String getCountryId(String idplace) {
		return idplace.substring(0, 5)+"0000000000";
	}

	public static String getRegionId(String idplace) {
		return idplace.substring(0, 8)+"0000000";
	}

	public static String getSubregionId(String idplace) {
		return idplace.substring(0, 11)+"0000";
	}
	
	public static List<Long> splitToLongList(String ids){
		if(ids == null || ids.isBlank())
			return null;
		List<Long> id_list = new ArrayList<>();
		String[] idarray = ids.split(":");
		
		for(String snumber : idarray) {
			try {
				Long id = Long.parseLong(snumber);
				id_list.add(id);
			}catch (NumberFormatException e) {
			}
		}
		
		if(id_list.isEmpty()) return null;
		
		
		return id_list;
	}

}
