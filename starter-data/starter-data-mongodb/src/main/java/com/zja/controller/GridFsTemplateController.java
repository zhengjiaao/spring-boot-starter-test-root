/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 13:25
 * @Since:
 */
package com.zja.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * mongo 存储文件
 *
 * 文件上传的接口，推荐使用 postman 测试。不推荐swagger进行上传多文件
 */
@Api(tags = {"GridFsTemplateController"}, description = "Mongo 文件操作-推荐用Postman工具测试")
@RequestMapping(value = "rest/file")
@RestController
public class GridFsTemplateController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    /*@Resource
    private GridFSBucket gridFSBucket;*/

    @PostMapping(value = "/post/upload/v1")
    @ApiOperation(value = "post-上传单文件")
    public Object postFile(@ApiParam("上传文件") @RequestPart(value = "file") MultipartFile file) {

        String filename = file.getOriginalFilename();

        DBObject metaData = new BasicDBObject();
        metaData.put("fileName", filename);
        metaData.put("createTime", new Date());
        try {
            ObjectId objectId = gridFsTemplate.store(file.getInputStream(), filename, file.getContentType(), metaData);
            System.out.println("ObjectId: " + objectId);
        } catch (IOException e) {
            throw new RuntimeException("System Exception while handling request" + e);
        }
        return filename;
    }

    /**
     * 文件默认是上传到数据中的fs.files和fs.chunks中
     * files是用来存储文件的信息，文件名，md5,文件大小，还有刚刚的metadata,上传时间等等数据
     */
    @PostMapping(value = "/post/upload/v2")
    @ApiOperation(value = "post-上传多文件")
    public Object postFile(@ApiParam("上传文件") @RequestPart(value = "files") MultipartFile[] files) throws Exception {

        if (files.length <= 0) {
            return "未选择要上传的文件！";
        }

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            System.out.println("上传文件：" + file.getOriginalFilename() + "  大小：" + file.getSize());
            //文件名、文件输入流、文件类型
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String contentType = file.getContentType();
            long size = file.getSize();

            //上传文件到 mongodb
            ObjectId objectId = gridFsTemplate.store(inputStream, filename, contentType);

            //DBObject metadata = new BasicDBObject(filename, size);
            //ObjectId objectId = gridFsTemplate.store(inputStream, filename, metadata);
        }
        return true;
    }

    @DeleteMapping(value = "get/delete/id")
    @ApiOperation(value = "删除文件")
    public void deleteFileById(@RequestParam(value = "id") String id) {
        gridFsTemplate.delete(new Query()
                .addCriteria(Criteria.where("_id").is(id)));
    }

    @DeleteMapping(value = "get/delete/filename")
    @ApiOperation(value = "删除文件")
    public void deleteFileByFileName(@RequestParam(value = "filename") String filename) {
        gridFsTemplate.delete(new Query()
                .addCriteria(Criteria.where("filename").is(filename)));
    }

    @GetMapping(value = "get/online/view")
    @ApiOperation(value = "在线预览-仅浏览器支持的contentType可预览 否则下载", notes = "例如图片、文本等可预览，word不可以预览")
    public void onlineViewFile(HttpServletResponse response,
                               @ApiParam(value = "id") @RequestParam String id) throws IOException {

        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));
        if (ObjectUtils.isEmpty(gridFSFile)) {
            throw new RuntimeException("GridFSFile must not be null!");
        }
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);

        //可选的，设置响应给客户端的文件格式，如果浏览器支持预览，可以预览，否则下载文件。
        //示例："image/jpeg"
        //不知道文件类型使用: application/octet-stream
        String contentType = resource.getContentType();
        System.out.println("contentType: " + contentType);
        if (StringUtils.hasText(contentType)) {
            response.setContentType(contentType);
        } else {
            response.setContentType("application/octet-stream");
        }

        //可选的，设置文件名
        response.addHeader("Content-Disposition", "attachment;filename=" +
                URLEncoder.encode(gridFSFile.getFilename(), "UTF-8"));

        //将字节从大（超过 2GB） InputStream 复制到 OutputStream
        IOUtils.copyLarge(resource.getInputStream(), response.getOutputStream());
    }

    @GetMapping(value = "get/download")
    @ApiOperation(value = "下载文件-文件流")
    public void downloadfile(HttpServletResponse response,
                             @ApiParam(value = "filename") @RequestParam String filename) throws IOException {

        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("filename").is(filename)));
        GridFsResource resource = gridFsTemplate.getResource(gridFSFile);

        //response.setCharacterEncoding("UTF-8");
        response.setContentType("application/force-download");
        //response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" +
                URLEncoder.encode(filename, "UTF-8"));

        /*byte[] bytes = toByteArray(resource.getInputStream());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.close();*/

        IOUtils.copyLarge(resource.getInputStream(), response.getOutputStream());
    }

    /**
     * InputStream 转换成byte[]
     */
    private static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

}
