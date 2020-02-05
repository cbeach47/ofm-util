package org.ofm.utils;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.ofm.TestConfig;
import org.ofm.entities.ChecksummedDirectory;
import org.ofm.entities.ChecksummedFile;

public class OfmFileUtilsTests {
	
	
	@Test
	public void fileChecksumShouldBeConsistent() {
		try {
			ChecksummedFile cf = OfmFileUtils.checksumFile(Paths.get(TestConfig.BASE, TestConfig.TEST_DIR_1, TestConfig.FILE_RANDOM_TXT_NAME));
			assertEquals(TestConfig.FILE_RANDOM_TXT_SUM, cf.getChecksum());
			ChecksummedFile cf2 = OfmFileUtils.checksumFile(Paths.get(TestConfig.BASE, TestConfig.TEST_DIR_1, TestConfig.FILE_RANDOM_TXT_NAME));
			assertEquals(cf.getChecksum(), cf2.getChecksum());
		} catch (IOException e) {
			fail("checksum threw: " + e.getMessage());
		}
	}
	
	@Test
	public void fileChecksumImage() {
		try {
			ChecksummedFile cf = OfmFileUtils.checksumFile(Paths.get(TestConfig.BASE, TestConfig.TEST_DIR_1, TestConfig.FILE_FLOWERS_JPG_NAME));
			assertEquals(TestConfig.FILE_FLOWERS_JPG_SUM, cf.getChecksum());
		} catch (IOException e) {
			fail("checksum threw: " + e.getMessage());
		}
	}
	
	@Test
	public void dirChecksum() {
		try {
			List<ChecksummedFile> control = TestConfig.setupTestDirectory1();
			ChecksummedDirectory cd = OfmFileUtils.checksumDirectory(Paths.get(TestConfig.BASE, TestConfig.TEST_DIR_1).toAbsolutePath());
			List<ChecksummedFile> test = cd.getAll();
			test.sort(null); // Natural ordering
			
			assertEquals(control.size(), test.size());
			for(int i = 0; i < control.size(); i++) {
				assertEquals(control.get(i).getFilepath().toString(), test.get(i).getFilepath().toString());
				assertEquals(control.get(i).getChecksum(), test.get(i).getChecksum());
			}
		} catch (IOException e) {
			fail("checksumDir threw: " + e.getMessage());
		}
	}
}
