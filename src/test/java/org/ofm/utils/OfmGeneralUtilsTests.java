package org.ofm.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ofm.TestConfig;
import org.ofm.entities.ChecksummedDirectory;
import org.ofm.entities.ChecksummedFile;

public class OfmGeneralUtilsTests {
	
	@Test
	public void testSomeDuplicates() {
		try {
			ChecksummedDirectory baseDir = OfmFileUtils.checksumDirectory(Paths.get(TestConfig.BASE, TestConfig.TEST_DIR_1));
			ChecksummedDirectory targetDir = OfmFileUtils.checksumDirectory(Paths.get(TestConfig.BASE, TestConfig.TEST_DIR_2));
			List<ChecksummedFile> dups = OfmGeneralUtils.checkDuplicates(baseDir, targetDir);
			assertEquals(1, dups.size());
			assertEquals("random_duplicate.txt", dups.get(0).getFilepath().getFileName().toString());
		} catch (IOException e) {
			fail(e);
		}
		
	}
}
