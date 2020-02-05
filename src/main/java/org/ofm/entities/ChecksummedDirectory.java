package org.ofm.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChecksummedDirectory {
	List<ChecksummedFile> files;
	
	public ChecksummedDirectory() {
		files = new ArrayList<>();
	}
	
	public void addFile(ChecksummedFile f) {
		files.add(f);
	}
	
	public List<ChecksummedFile> getAll() {
		return files;
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		
		files.sort(null);
		
		JSONArray jsonFiles = new JSONArray();
		for(ChecksummedFile f : files) {
		jsonFiles.put(f.toJson());
		}
		json.put("files", jsonFiles);
		
		return json;
	}
}
