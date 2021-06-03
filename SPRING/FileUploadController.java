package org.zerock.ex00.common.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

////다중파일 업로드 방법1: form 방식의 파일업로드
////파일 업로드 요청 JSP 페이지 호출
//	@GetMapping("fileUploadForm")
//	public void callFileUploadForm() {
//		logger.info("upload Form");
//	}
//
////다중파일 업로드 방법1: form 방식의 파일업로드
////Model이용, 업로드 파일 저장
//	@PostMapping("/fileUploadFormAction")
//	public void fileUploadActionPost(MultipartFile[] uploadFiles, Model model) {
//		logger.info("====FileUpload With Form ========");
////저장경로 (Windows 환경이므로 경로구분자를 \\로 지정)
//		String strUploadFileRepoDir = "C:\\upload";
//		for (MultipartFile multipartUploadFile : uploadFiles) {
//			logger.info("=================================");
//			logger.info("Upload File Name: " + multipartUploadFile.getOriginalFilename());
//			logger.info("Upload File Size: " + multipartUploadFile.getSize());
////업로드 파일의 리소스(저장폴더와 파일이름)가 설정된 File 객체 생성
//			//File saveUploadFile = new File(strUploadFileRepoDir, multipartUploadFile.getOriginalFilename());
//			
//			String strUploadFileName = multipartUploadFile.getOriginalFilename();
//			
//			strUploadFileName = strUploadFileName.substring(strUploadFileName.lastIndexOf("\\")+1);
//			
//			File saveUploadFile = new File(strUploadFileRepoDir, strUploadFileName);
//			
//			try {
////서버에 파일객체를 이용하여 업로드 파일 저장
//				multipartUploadFile.transferTo(saveUploadFile);
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//			}
//		} // End-for
//	}

	@GetMapping("/fileUploadAjax")
	public void callFileUPloadAjax() {
		logger.info("upload Ajax Form");
	}

	// Ajax: 날짜형식의 디렉토리 구조를 생성후 업로드파일 저장 구성
	// Step1- 날짜 형식 경로 문자열 생성 메소드
	private String getDatefmtPathName() {
		// 날짜 형식 지정
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		// 날짜 생성
		Date date = new Date();
		// 날짜 형식이 지정된 날짜문자열(yyyy/MM/dd) 생성
		String strDatefmtPathName = simpleDateFormat.format(date);
		// 경로구분자를 운영체제에 맞도록 변경한 문자열을 반환
		return strDatefmtPathName.replace("/", File.separator);
	}

	// Ajax: 썸네일 이미지 생성
	// Step1: 업로드파일이 이미지 파일여부 검사 메소드 정의
	private boolean checkIsImageForUploadFile(File uploadFile) {
		try {
			String strContentType = Files.probeContentType(uploadFile.toPath());
			System.out.println("업로드파일의 ContentType: " + strContentType);
			return strContentType.startsWith("image");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("/fileUploadAjaxAction")
	public void fileUploadActionPost(MultipartFile[] uploadFiles) {
		logger.info("====Start FileUpload With Ajax====");

		String strUploadFileRepoDir = "C:\\upload";

		// 날짜 형식(yyyy/MM/dd)의 폴더구조 생성
		// File(String Parent, String Child)
		// 부모경로(레포지토리 경로) 문자열에 자식경로(날짜형식 경로) 문자열이 더해진 File 객체 생성
		// 날짜형식 경로를 생성하기 위하여 앞에서 생성한 getDatefmtPathName() 메서드 호출
		File fileUploadPath = new File(strUploadFileRepoDir, getDatefmtPathName());
		logger.info("upload path: " + fileUploadPath);
		// 경로가 존재하지 않으면 전체 폴더 구조 생성
		if (fileUploadPath.exists() == false) {
			// 이때, 기존폴더 구조중 일부가 있으면 없는 부분 부터 나머지 전체를 생성
			fileUploadPath.mkdirs();
		}

		for (MultipartFile multipartUploadFile : uploadFiles) {

			logger.info("======================================");
			logger.info("Upload File Name: " + multipartUploadFile.getOriginalFilename());
			logger.info("Upload File Size: " + multipartUploadFile.getSize());

			// 업로드파일이름 원본문자열
			String strUploadFileName = multipartUploadFile.getOriginalFilename();

			// IE10 파일이름추출: multipartUploadFile.getOriginalFilename()에서 업로드 파일이름만 추출
			// 파일이름만 있는 경우, 파일이름만 추출됨
			strUploadFileName = strUploadFileName.substring(strUploadFileName.lastIndexOf("\\") + 1);
			logger.info("최종처리된 업로드 파일이름: " + strUploadFileName);

			// 업로드 정보(저장폴더와 파일이름 문자열)의 파일객체 생성
			File saveUploadFile = new File(strUploadFileRepoDir, strUploadFileName);

			try {
				// 서버에 파일객체를 이용하여 업로드 파일 저장
				multipartUploadFile.transferTo(saveUploadFile);
				// 업로드된 파일에 대하여 이미지파일인지를 검사후, 이미지 파일일경우, 썸네일이미지 생성
				// 이미지파일이 아닌꼉우, if 문 처리 없음.
				// 이미지 파일 인 경우,
				if (checkIsImageForUploadFile(saveUploadFile)) {
					// 썸네일 생성경로와 파일이름이 설정된 파일객체를 전송 보내는 FileOutputStream 객체 생성
					FileOutputStream outputStreamForThumbnail = new FileOutputStream(
							new File(fileUploadPath, "s_" + strUploadFileName));
					// FileOutput-스트림으로 보내진 파일객체를 서버에 저장(input)하여, 100X100 크기(px)의 썸네일 생성
					// 라이브러리 메소드 사용
					Thumbnailator.createThumbnail(multipartUploadFile.getInputStream(), outputStreamForThumbnail, 100,
							100);
					// OUT 스트림리소스 닫기
					outputStreamForThumbnail.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} // End-for

	}

}
