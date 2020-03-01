package com.springstudy.util;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springstudy.entity.Board;
import com.springstudy.entity.DocFile;

@Component("fileUtil")
public class FileUtils {
	private static final String filePath = "C:\\repository\\"; // ������ ����� ��ġ

	public List<? extends DocFile> parseInsertFileInfo(Board Board, MultipartHttpServletRequest mpRequest)
			throws Exception {

		Iterator<String> iterator = mpRequest.getFileNames();

		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		List<DocFile> list = new ArrayList<DocFile>();

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

	public void fileDown(HttpServletResponse response, String storedName, String fileName) throws Exception {
		// ������ �����ߴ� ��ġ���� ÷�������� �о� byte[]�������� ��ȯ�Ѵ�.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath + storedName));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	public void fileDelete(String storedName) throws Exception {
		File file = new File(filePath + storedName);
		file.delete();
	}

	// 32���� ������ ���ڿ��� ����� ��ȯ����
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}