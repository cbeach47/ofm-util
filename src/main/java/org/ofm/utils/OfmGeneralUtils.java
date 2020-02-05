package org.ofm.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ofm.entities.ChecksummedDirectory;
import org.ofm.entities.ChecksummedFile;

public class OfmGeneralUtils {
	private static Logger LOGGER = LogManager.getLogger(OfmGeneralUtils.class);

	public static List<ChecksummedFile> checkDuplicates(ChecksummedDirectory base, ChecksummedDirectory toReview) {
		List<ChecksummedFile> dups = new ArrayList<>();
		for(ChecksummedFile cFile : toReview.getAll()) {
			if(base.getAll().contains(cFile)) {
				dups.add(cFile);
				LOGGER.info("Found duplicate:  [{}] at [{}]", cFile.getFilepath().toAbsolutePath().toString(), base.getAll().get(base.getAll().indexOf(cFile)).getFilepath().toAbsolutePath().toString());
			}
		}
		return dups;
	}
}
