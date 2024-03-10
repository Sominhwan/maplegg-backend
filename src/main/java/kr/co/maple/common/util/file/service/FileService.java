package kr.co.maple.common.util.file.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.maple.common.model.ResDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
	private static final Logger log = LogManager.getLogger("kr.co.maple");
	/**
	 * 파일 업로드
	 *
	 * @param MultipartFile
	 * @return HttpEntity
	 * @throws IOException
	 */
	public HttpEntity<?> fileUpload(MultipartFile file) {
	    // 파일 저장 경로 설정
	    String storagePath = "C:/workspace/";
	    
	    if (file.getSize() > 10_000_000) { // 파일 크기가 10MB 이상인 경우
    	    // 파일 이름에서 확장자 제거
	    	String originalFilename = file.getOriginalFilename();
	    	String filenameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
	        // 압축 파일 저장 경로 및 이름 설정 (확장자 제거)
	        String zipFileName = storagePath + filenameWithoutExtension + ".zip";

	        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));
        		ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes())) {
	            zos.putNextEntry(new ZipEntry(file.getOriginalFilename()));
	            byte[] buffer = new byte[1024];
	            int length;
	            while ((length = bais.read(buffer)) > 0) {
	                zos.write(buffer, 0, length);
	            }
	            zos.closeEntry();
	            log.info("압축된 파일이 저장되었습니다: " + zipFileName);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        File outputFile = new File(storagePath + file.getOriginalFilename());
	        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
	            fos.write(file.getBytes());
	            log.info("파일이 저장되었습니다: " + outputFile.getPath());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("파일 업로드 성공.")
                        .build(),
                        HttpStatus.OK);
	}
	/**
	 * 파일 불러오기
	 *
	 * @param String
	 * @return HttpEntity
	 * @throws IOException
	 */
	public HttpEntity<?> loadFile(String fileName) {
	    try {
	        // 파일 저장 경로 설정
	        Path filePath = Paths.get("C:/workspace/" + fileName).normalize();
	        Resource resource = new UrlResource(filePath.toUri());

	        if (resource.exists() || resource.isReadable()) {
	            // 파일의 MIME 타입을 결정
	            String contentType = Files.probeContentType(filePath);
	            if (contentType == null) {
	                // MIME 타입을 결정할 수 없는 경우, 기본값으로 설정
	                contentType = "application/octet-stream";
	            }

	            // 파일 리소스와 헤더를 포함한 ResponseEntity 반환
	            return ResponseEntity.ok()
	                    .contentType(MediaType.parseMediaType(contentType))
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                    .body(resource);
	        } else {
	            throw new RuntimeException("파일을 찾을 수 없습니다 " + fileName);
	        }
	    } catch (IOException e) {
	        throw new RuntimeException("파일 로딩중 에러! " + fileName, e);
	    }
	}
	// 원본파일 + 압축파일 같이 저장
	public HttpEntity<?> fileUpload2(MultipartFile file) {
		if (file.getSize() > 10_000_000) { // 파일 크기가 10MB 이상인 경우
		    File convFile = new File("C:/workspace/" + file.getOriginalFilename());
		    try {
		        convFile.createNewFile();
		        FileOutputStream fos = new FileOutputStream(convFile);
		        fos.write(file.getBytes());
		        fos.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }


		    // 압축 파일 저장 경로 및 이름 설정
		    String zipFileName = convFile.getPath().concat(".zip");
		    
		    try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName))) {
		        zos.putNextEntry(new ZipEntry(convFile.getName()));
		        byte[] bytes = file.getBytes();
		        zos.write(bytes, 0, bytes.length);
		        zos.closeEntry();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    log.info("압축된 파일이 저장되었습니다: " + zipFileName);
		}
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(0)
                        .message("파일 업로드 성공.")
                        .build(),
                        HttpStatus.OK);
	}
}



