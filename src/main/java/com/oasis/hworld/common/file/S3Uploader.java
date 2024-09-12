package com.oasis.hworld.common.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * S3 업로더 컨트롤러
 * @author 조영욱
 * @since 2024.09.12
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.12  	조영욱        최초 생성
 * </pre>
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class S3Uploader {
    private final AmazonS3 amazonS3;
    @Value("${S3_BUCKET_NAME}")
    private String bucket;

    /**
     * 이미지 파일을 S3 버킷에 업로드하고, 업로드된 이미지의 URL을 반환
     *
     * @param imageFile
     * @return aws s3에 upload된 url
     */
    public String uploadImage(MultipartFile imageFile) {
        String originName = imageFile.getOriginalFilename();
        String uploadName = changeRandomImageName(originName);
        try {
            uploadToS3(imageFile, uploadName);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.QUEST_NOT_EXIST);
        }

        return amazonS3.getUrl(bucket, uploadName).toString();
    }

    /**
     * 이미지 파일을 S3에 업로드
     *
     * @param imageFile
     * @param uploadName
     * @throws IOException
     */
    public void uploadToS3(MultipartFile imageFile, String uploadName) throws IOException {
        amazonS3.putObject(new PutObjectRequest(
                        bucket,
                        uploadName,
                        imageFile.getInputStream(),
                        createObjectMetadata(imageFile.getSize(), imageFile.getContentType())
                )
        );
    }

    private String changeRandomImageName(String originName) {
        return UUID.randomUUID() + "_" + originName;
    }

    /**
     * S3에 업로드할 파일의 메타데이터를 생성
     *
     * @param size
     * @param contentType
     * @return ObjectMetadata
     */
    private ObjectMetadata createObjectMetadata(long size, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(size);
        metadata.setCacheControl("31536000");
        return metadata;
    }
}
