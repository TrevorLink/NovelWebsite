package com.yep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.NovelMapper;
import com.yep.pojo.*;
import com.yep.service.NovelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HuangSir
 * @date 2022-02-21 14:44
 */
@Service
@Slf4j
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {
   @Autowired
   private NovelMapper novelMapper;
   @Value("${file.dir}")
   private  String fileDir;
   @Override
   public List<Novel> getCurrentUserNovelCollections() {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Integer userId = user.getId();
      return novelMapper.getCurrentUserNovelCollections(userId);
   }

   @Override
   public List<Novel> getTopNovels() {
      QueryWrapper<Novel> wrapper = new QueryWrapper<>();
      wrapper.orderByDesc("hot");
      wrapper.last("limit 10");
      return novelMapper.selectList(wrapper);
   }

   @Override
   public List<Novel> getNovelsByType(Integer type) {
      QueryWrapper<Novel> wrapper = new QueryWrapper<>();
      wrapper.eq("type",type);
      return novelMapper.selectList(wrapper);
   }

   @Override
   public List<Novel> getNovelsByAuthor(String author) {
      QueryWrapper<Novel> wrapper = new QueryWrapper<>();
      wrapper.like("author",author);
      return novelMapper.selectList(wrapper);
   }

   @Override
   public RespPage getAllNovels( Integer currPage,
                                 Integer size) {
      log.debug("当前页码：{}",currPage);
      log.debug("每页记录数：{}",size);
      QueryWrapper<Novel> wrapper = new QueryWrapper<>();
      wrapper.eq("status",NovelStatus.ON_LIST);
      Page<Novel> page = new Page<>(currPage,size);
      Page<Novel> newPage = novelMapper.selectPage(page, wrapper);
      Long count = novelMapper.selectCount(wrapper);
      List<Novel> records = newPage.getRecords();
      List<NovelPage> list = new ArrayList<>();
      for(Novel novel : records){
         NovelPage novelPage = new NovelPage(novel.getId(), novel.getImageUrl(), novel.getName());
         list.add(novelPage);
      }
      return new RespPage(count,list);
   }

   @Override
   public Novel getNovelDetailsById(Integer id) {
      log.debug("id={}",id);
      return novelMapper.selectById(id);
   }

   @Override
   public RespBean addCollection(Integer id) {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Integer userId = user.getId();
      Integer count = novelMapper.addCollection(userId,id);
      if (count!=1){
         return RespBean.error("服务器出错，收藏失败！");
      }
      return RespBean.ok("添加收藏成功！");
   }

   @Override
   public String downloadNovel(Integer id) {
      return novelMapper.selectById(id).getDownloadUrl();
   }

   @Override
   public RespPage search(String name,Integer currPage,Integer size) {
      QueryWrapper<Novel> wrapper = new QueryWrapper<>();
      wrapper.like("name",name);
      wrapper.eq("status",NovelStatus.ON_LIST);
      Page<Novel> page = new Page<>(currPage, size);
      List<Novel> records = novelMapper.selectPage(page, wrapper).getRecords();
      Long count = novelMapper.selectCount(wrapper);
      List<NovelPage> list = new ArrayList<>();
      for(Novel novel : records){
         NovelPage novelPage = new NovelPage(novel.getId(), novel.getImageUrl(), novel.getName());
         list.add(novelPage);
      }
      return new RespPage(count,list);
   }

   @Override
   public RespBean uploadNovel(Novel novel) {
      log.debug("传入的novel:{}",novel);
      novel.setStatus(NovelStatus.IN_PROGRESS);
      int insert = novelMapper.insert(novel);
      if(insert!=1) {
         return RespBean.error("服务器发生错误，上传失败！");
      }
      return RespBean.ok("上传成功！");
   }

   @Override
   public RespBean uploadFile( MultipartFile file) {
      log.debug("接收到的文件：{}",file);
      String filename = file.getOriginalFilename();
      try {
         file.transferTo(new File(fileDir, filename));
      } catch (IOException e) {
         e.printStackTrace();
         return RespBean.error("上传失败！");
      }
      return RespBean.ok("上传成功！","120.24.179.229:8080/"+filename);
   }

}
