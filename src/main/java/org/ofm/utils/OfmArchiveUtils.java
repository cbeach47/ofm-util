package org.ofm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ofm.entities.ChecksummedDirectory;

public class OfmArchiveUtils {
	private static Logger LOGGER = LogManager.getLogger(OfmArchiveUtils.class);

	public static boolean serializeViaJsonToFile(ChecksummedDirectory cDir, Path outputFile) {
		File f = outputFile.toAbsolutePath().toFile();
		if(f.exists()) {
			LOGGER.error("Can't archive checksum JSON to existing file [{}]",  outputFile.toAbsolutePath().toString());
			return false;
		}
		try {
			if(!f.createNewFile()) {
				LOGGER.error("Can't create new file for checksum JSON [{}]",  outputFile.toAbsolutePath().toString());
				return false;
			}
		} catch (IOException e) {
			LOGGER.error("Unable to create file to archive checksums in JSON - [{}] - [{}]", outputFile.toAbsolutePath().toString(), e.getMessage());
			LOGGER.error(e);
			return false;
		}
		try(FileWriter fw = new FileWriter(f); BufferedWriter bw = new BufferedWriter(fw, 1024)) {
			bw.write(cDir.toJson().toString(2));
		} catch (IOException e) {
			LOGGER.error("Unable to archive checksums to JSON file - [{}] - [{}]", outputFile.toAbsolutePath().toString(), e.getMessage());
			LOGGER.error(e);
			return false;
		}
		LOGGER.info("Archived checksum JSON to [{}]", outputFile.toAbsolutePath().toString());
		return true;
	}
}
