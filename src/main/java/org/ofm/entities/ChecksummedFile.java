package org.ofm.entities;

import java.nio.file.Path;

import org.json.JSONObject;

public class ChecksummedFile implements Comparable<ChecksummedFile> {
	private Path filepath;
	
	private String checksum;

	public ChecksummedFile(Path path, String digest) {
		this.filepath = path;
		this.checksum = digest;
	}

	public Path getFilepath() {
		return filepath;
	}

	public void setFilepath(Path filepath) {
		this.filepath = filepath;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	
	public String toString() {
		return filepath + ", " + checksum;
	}

	@Override
	public int compareTo(ChecksummedFile o) {
		return this.filepath.compareTo(o.filepath);
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("file", filepath.toAbsolutePath().toString());
		json.put("checksum", checksum);
		return json;
	}
	
	// Checks against the checksum.  Filename is not important here.
	 @Override
    public boolean equals(Object o) { 
        if (o == this) { 
            return true; 
        } 
  
        if (!(o instanceof ChecksummedFile)) { 
            return false; 
        } 
          
        ChecksummedFile targetCFile = (ChecksummedFile) o; 
          
        // Compare the data members and return accordingly  
        return this.checksum.equals(targetCFile.checksum);
    } 
}
