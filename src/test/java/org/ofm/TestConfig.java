package org.ofm;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.ofm.entities.ChecksummedFile;
import org.ofm.utils.OfmFileUtils;

public class TestConfig {
	public static final String FILE_RANDOM_TXT_SUM = "4f2dcc69dc13e4a86d6efd2e4bc8d845a1c7da9f10dd49949d4178737af833fe";
	public static final String FILE_RANDOM_TXT_NAME = "random.txt";
	public static final String FILE_RANDOM_DUPLICATE_TXT_NAME = "random_duplicate.txt";
	public static final String FILE_FLOWERS_JPG_SUM = "3a3508da1793aaaf81b5773a34d0921bb6c4a35a91d596eb2afd88c43e33c606";
	public static final String FILE_FLOWERS_JPG_NAME = "flowers.jpg";
	public static final String FILE_TD2_UNIQUE_TXT_NAME = "unique.txt";
	public static final String FILE_TD2_RANDOM_DUPLICATE_TXT_NAME = "td2f1/random_duplicate.txt";

	public static final String BASE = "src/test/resources";
	public static final String TEST_DIR_1 = "testDirectory1";
	public static final String TEST_DIR_2 = "testDirectory2";
	
	public static final String TEMP = "temp/junit";
	
	public static List<ChecksummedFile> setupTestDirectory1() throws IOException {
		List<ChecksummedFile> l = new ArrayList<>();
		l.add(OfmFileUtils.checksumFile(Paths.get(BASE, TEST_DIR_1, FILE_FLOWERS_JPG_NAME)));
		l.add(OfmFileUtils.checksumFile(Paths.get(BASE, TEST_DIR_1, FILE_RANDOM_TXT_NAME)));
		l.add(OfmFileUtils.checksumFile(Paths.get(BASE, TEST_DIR_1, FILE_RANDOM_DUPLICATE_TXT_NAME)));
		return l;
	}
}
