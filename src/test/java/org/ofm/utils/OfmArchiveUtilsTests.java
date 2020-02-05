package org.ofm.utils;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.ofm.TestConfig;

public class OfmArchiveUtilsTests {
	
	@Test
	public void testConvertChecksummedDirectoryToJsonPrettyPrint() {
		try {
			Path output = Paths.get(TestConfig.BASE, TestConfig.TEMP, "TEST_" + System.currentTimeMillis() + ".json");
			assertTrue(OfmArchiveUtils.serializeViaJsonToFile(OfmFileUtils.checksumDirectory(Paths.get(TestConfig.BASE, TestConfig.TEST_DIR_1)), output));
			assertTrue(output.toFile().exists());
		} catch (IOException e) {
			fail(e);
		}
		
	}
}
