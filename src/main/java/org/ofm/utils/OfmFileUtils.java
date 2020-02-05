package org.ofm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ofm.entities.ChecksummedDirectory;
import org.ofm.entities.ChecksummedFile;

public class OfmFileUtils {
	private static Logger LOGGER = LogManager.getLogger(OfmFileUtils.class);

	
	public static ChecksummedFile checksumFile(Path file) throws IOException {
		LOGGER.trace("Beginning checksum of file [{}].", file);
		try (InputStream is = Files.newInputStream(file)) {
		    final String d = DigestUtils.sha256Hex(is);
		    LOGGER.trace("Completed checksum of file [{}].", file);
		    return new ChecksummedFile(file.toAbsolutePath(), d);
		}
	}

	public static ChecksummedDirectory checksumDirectory(Path base) throws IOException {
		LOGGER.traceEntry("Beginning checksum of directory [{}].", base);
		ChecksummedDirectory results = new ChecksummedDirectory();
		Path dir = Files.walkFileTree(base, new FileVisitor<Path>() {

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				LOGGER.debug("Finished looking at directory [{}]", dir.toString());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				LOGGER.debug("Looking at directory [{}]", dir.toString());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				LOGGER.debug("Looking at file [{}]", file.toString());
				try {
					results.addFile(checksumFile(file));
				} catch (IOException e) {
					LOGGER.error("Unable to visit file [{}] with [{}]", file, e.getMessage());
					return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				LOGGER.error("Unable to complete walking [{}].  Failed at [{}] with [{}]", base, file, exc.getMessage());
				return FileVisitResult.TERMINATE;
			}
		});
		return results;
	}
}
