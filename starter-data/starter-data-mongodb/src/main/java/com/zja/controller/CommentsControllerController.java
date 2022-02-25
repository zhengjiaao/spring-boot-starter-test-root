/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-02-25 16:51
 * @Since:
 */
package com.zja.controller;

import com.zja.dao.CommentsDao;
import com.zja.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentsControllerController {

    @Autowired
    private CommentsDao commentsDao;

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        List<Comment> list = commentsDao.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find")
    public ResponseEntity findById(@RequestParam String commentId) {
        return ResponseEntity.ok(commentsDao.findById(commentId));
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentsDao.save(comment));
    }

    @PutMapping(value = "/update/{comentId}")
    public ResponseEntity update(@PathVariable String comentId, @RequestBody Comment comment) {
        Optional<Comment> byId = commentsDao.findById(comentId);
        if (byId.isPresent()) {
            comment.setId(comentId);
            commentsDao.save(comment);
        }
        return ResponseEntity.ok(comment);
    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam String comentId) {
        commentsDao.deleteById(comentId);
    }
}
