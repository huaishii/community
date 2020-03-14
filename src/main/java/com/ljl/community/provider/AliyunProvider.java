package com.ljl.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ljl.community.expcetion.CustomizeErrorCode;
import com.ljl.community.expcetion.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Azz-ll on 2020/3/14
 */
@Service
public class AliyunProvider {
    @Value("${aliyuncs.endpoint}")
    private String endpoint;
    @Value("${aliyuncs.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyuncs.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyuncs.bucketName}")
    private String bucketName;
    @Value("${aliyuncs.objectName}")
    private String objectName;


    public String upload(InputStream inputStream,String fileName) {
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        ossClient.putObject(bucketName, objectName+generatedFileName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
        return null;
    }
}
