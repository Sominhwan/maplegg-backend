package kr.co.maple.common.util.file.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.maple.common.util.file.service.FileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileController {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	private final FileService fileService;
	
	// 파일 업로드
	@PostMapping(value="/upload/file")
	public HttpEntity<?> fileUpload(@RequestPart(value = "file", required = false) MultipartFile file) {
        return fileService.fileUpload(file);
	}
	// 파일 불러오기
	@GetMapping(value="/load/file")
	public HttpEntity<?> fileLoad(@RequestParam String fileName) {
		log.info(fileName);
		return fileService.loadFile(fileName);
//        return new ResponseEntity<>(
//                ResDTO.builder()
//                        .code(0)
//                        .message("파일 업로드 성공.")
//                        .build(),
//                        HttpStatus.OK);
	}
}
