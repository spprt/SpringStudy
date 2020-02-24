package com.springstudy.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springstudy.entity.Board;
import com.springstudy.entity.DocFile;

@Component("fileUtil")
public class FileUtils {
	private static final String filePath = "C:\\repository\\"; // 파일이 저장될 위치

	public List<? extends DocFile> parseInsertFileInfo(Board Board, MultipartHttpServletRequest mpRequest) throws Exception {

		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<DocFile> list = new ArrayList<DocFile>();
		Map<String, Object> listMap = null;

		Long id = Board.getId();

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs();
		}

		while (iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				DocFile f = new DocFile();
				f.setDocid(id);
				f.setFileName(originalFileName);
				f.setFileSize(multipartFile.getSize());
				f.setStoredName(storedFileName);
				list.add(f);
			}
		}
		return list;
	}

	// 32개의 랜덤한 문자열을 만들어 반환해줌
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}