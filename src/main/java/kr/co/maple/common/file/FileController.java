package kr.co.maple.common.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.maple.common.model.ResDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileController {
	private static final Logger log = LogManager.getLogger("kr.co.maple");

	// 프로필 이미지 단일 저장
	@PostMapping(value="/upload/file")
	public HttpEntity<?> profileImageUpload(@RequestPart(value = "file", required = false) MultipartFile file) {
		log.info("파일 사이즈 --> " + file.getSize());
		log.info("파일 타입 --> " + file.getContentType());
		log.info("파일 이름 --> " + file.getOriginalFilename());
		log.info("파일 리소스 --> " + file.getResource());

        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("파일 업로드 성공.")
                        .build(),
                        HttpStatus.OK
        );
	}
}
