package com.yep.controller;

import com.yep.pojo.Novel;
import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;
import com.yep.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author HuangSir
 * @date 2022-02-21 14:45
 */
@RestController
@RequestMapping("novel")
public class NovelController {
   @Autowired
   private NovelService novelService;
   @GetMapping("collection")
   public List<Novel> getCurrentUserNovelCollections(){
      return novelService.getCurrentUserNovelCollections();
   }
   @GetMapping("/top")
   public List<Novel> getTopNovels(){
      return  novelService.getTopNovels();
   }
   @GetMapping("/type")
   public  List<Novel> getNovelsByType(Integer type){
      return novelService.getNovelsByType(type);
   }
   @GetMapping("/author")
   public List<Novel> getNovelsByAuthor(String author){
      return  novelService.getNovelsByAuthor(author);
   }
   @GetMapping("/")
   public RespPage getAllNovels(Integer currPage,Integer size){
      return  novelService.getAllNovels(currPage,size);
   }
   @GetMapping("/details")
   public Novel getNovelDetailsById(Integer id){
      return  novelService.getNovelDetailsById(id);
   }
   @PutMapping("collection")
   public RespBean addCollection(Integer id){
      return novelService.addCollection(id);
   }
   @GetMapping("download")
   public String downloadNovel(Integer id){
      return novelService.downloadNovel(id);
   }
   @GetMapping("/search")
   public  RespPage search(String name, @RequestParam(defaultValue = "1") Integer currPage,
                           @RequestParam(defaultValue = "10") Integer size){
      return novelService.search(name,currPage,size);
   }
   @PostMapping("/novel")
   public RespBean uploadNovel(Novel novel){
      return  novelService.uploadNovel(novel);
   }
   @PostMapping("/file")
   public RespBean uploadFile(MultipartFile file){
      return novelService.uploadFile(file);
   }
}
