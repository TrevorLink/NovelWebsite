package com.yep.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yep.mapper.NovelMapper;
import com.yep.pojo.*;
import com.yep.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuangSir
 * @date 2022-02-21 14:44
 */
@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel> implements NovelService {
   @Autowired
   private NovelMapper novelMapper;
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
   public RespPage getAllNovels(@RequestParam(defaultValue = "1") Integer currPage,
                                @RequestParam(defaultValue = "10") Integer size) {
      Page<Novel> page = new Page<>(currPage,size);
      Page<Novel> newPage = novelMapper.selectPage(page, null);
      Long count = novelMapper.selectCount(null);
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
      int insert = novelMapper.insert(novel);
      if(insert!=1) return RespBean.error("服务器发生错误，上传失败！");
      return RespBean.ok("上传成功！");
   }

}
