package org.zerock.ex00.common.upload;

import java.io.File;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	private static final Logger logger =LoggerFactory.getLogger(FileUploadController.class);
	
	
	@GetMapping("fileuploadform")
	public void callFileUploadForm() {
		logger.info("upload Form");
	}
	
	
	
	@PostMapping("/fileuploadformAction")
	public void fileuploadformAction(MultipartFile[] uploadFiles, Model model) {
		 logger.info("====FileUpload With Form ========"); 
		 String strUploadFileRepoDir="C:\\upload";
		 for(MultipartFile multipartUploadFile : uploadFiles) {
		logger.info("==================");
		logger.info("uploadFile name:"+ multipartUploadFile.getOriginalFilename());
		logger.info("uploadFile name:"+ multipartUploadFile.getSize());		
		  File saveUploadFile = new File(strUploadFileRepoDir, multipartUploadFile.getOriginalFilename());        
			try { // 서버에 파일객체를 이용하여 업로드 파일 저장
				multipartUploadFile.transferTo(saveUploadFile);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} // End-for
	}

}