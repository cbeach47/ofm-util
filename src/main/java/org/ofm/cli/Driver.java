package org.ofm.cli;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ofm.entities.ChecksummedDirectory;
import org.ofm.entities.ChecksummedFile;
import org.ofm.utils.OfmArchiveUtils;
import org.ofm.utils.OfmFileUtils;
import org.ofm.utils.OfmGeneralUtils;

public class Driver {
	private static Logger LOGGER = LogManager.getLogger(Driver.class);

	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			LOGGER.error("Need an function parameter.  -r memoriesDir checksumReportsDir` (Review changes to memories) , `-c memoriesDir` (checksum directory) , `-d memoriesDir newDir` identify duplicates");
		} else if ("-r".equals(args[0])) {
			LOGGER.info("Review Changes function needs to be implemented!");
		} else if ("-c".equals(args[0])) {
			if(args.length == 3) {
				LOGGER.info("Checksumming directory [{}] to file [{}]", args[1], args[2]);
				OfmArchiveUtils.serializeViaJsonToFile(OfmFileUtils.checksumDirectory(Paths.get(args[1])), Paths.get(args[2]));
			} else {
				LOGGER.error("ChecksumDir - Needs parameters: memoriesDir outputFileName");
			}
		} else if ("-d".equals(args[0])) {
			if(args.length == 3) {
				LOGGER.info("Indentifying Duplicates checking [{}] against [{}]", args[2], args[1]);
				ChecksummedDirectory baseDir = OfmFileUtils.checksumDirectory(Paths.get(args[1]));
				ChecksummedDirectory newDir = OfmFileUtils.checksumDirectory(Paths.get(args[2]));
				List<ChecksummedFile> dups = OfmGeneralUtils.checkDuplicates(baseDir, newDir);
				LOGGER.info("Found [{}] duplicates", dups.size());
			} else {
				LOGGER.error("Identify Duplicates needs parameters: memoriesDir newDir");
			}
		} else {
			LOGGER.error("Unknown function [{}]", args[0]);
		}
	}
	
	public List<ChecksummedFile> checksumDir(String baseDir, String dirPath) throws IOException {
		ChecksummedFile cf = OfmFileUtils.checksumFile(Paths.get(baseDir, dirPath));
		LOGGER.info("Visited [{}]", cf);
		return null;
	}
}
