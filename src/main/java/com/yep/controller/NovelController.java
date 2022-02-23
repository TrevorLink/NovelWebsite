package com.yep.controller;

import com.yep.pojo.Novel;
import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;
import com.yep.service.NovelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api("用户模块小说功能")
@RequestMapping("novel")
public class NovelController {
   @Autowired
   private NovelService novelService;
   @GetMapping("collection")
   @ApiOperation(value = "获取当前用户的小说收藏",notes = "主要实现的功能之一：点击获取当前的用户收藏列表")
   public List<Novel> getCurrentUserNovelCollections(){
      return novelService.getCurrentUserNovelCollections();
   }
   @GetMapping("/top")
   @ApiOperation(value = "获取小说热度排行榜",notes ="根据数据库中小说的热度字段排序返回前十名最高热度的小说列表" )
   public List<Novel> getTopNovels(){
      return  novelService.getTopNovels();
   }
   @GetMapping("/type")
   @ApiOperation(value = "根据指定类型查询小说列表",notes = "主要功能之一：小说分类（根据小说类型精确查询）")
   public  List<Novel> getNovelsByType(@ApiParam(required = true) Integer type){
      return novelService.getNovelsByType(type);
   }
   @GetMapping("/author")
   @ApiOperation(value = "根据指定作者查询小说列表",notes = "主要功能之一：小说分类（根据名称模糊查询）")
   public List<Novel> getNovelsByAuthor(@ApiParam(required = true) String author){
      return  novelService.getNovelsByAuthor(author);
   }
   @GetMapping("/")
   @ApiOperation(value = "获取小说列表分页信息",notes = "主页中小说分页数据，出于效率考量，这里不全部返回所有的小说信息，只是返回一个特殊的分页信息（ID，图片，名称）")
   public RespPage getAllNovels(@ApiParam(required = false) Integer currPage,
                                @ApiParam(required = false) Integer size){
      return  novelService.getAllNovels(currPage,size);
   }
   @GetMapping("/details")
   @ApiOperation(value = "查看小说详细信息",notes = "在主页或者是管理端的时候只有分页数据，点击调用这个接口获取小说的详细信息")
   public Novel getNovelDetailsById(@ApiParam(required = true) Integer id){
      return  novelService.getNovelDetailsById(id);
   }
   @PutMapping("collection")
   @ApiOperation(value = "添加指定小说到我的收藏",notes = "在小说的详细信息中点击添加到收藏")
   public RespBean addCollection(@ApiParam(required = true) Integer id){
      return novelService.addCollection(id);
   }
   @GetMapping("download")
   @ApiOperation(value = "下载小说",notes = "在小说的详细信息中点击下载小说")
   public String downloadNovel(@ApiParam(required = true) Integer id){
      return novelService.downloadNovel(id);
   }
   @GetMapping("/search")
   @ApiOperation("根据指定名称模糊查询小说分页信息")
   public  RespPage search(@ApiParam(required = true) String name,
                           @ApiParam(required = false,value = "分页的当前页（默认为1）") @RequestParam(defaultValue = "1") Integer currPage,
                           @ApiParam(required = false,value = "每页展示的数据大小（默认为10）") @RequestParam(defaultValue = "10") Integer size){
      return novelService.search(name,currPage,size);
   }
   @PostMapping("/novel")
   @ApiOperation(value = "上传小说",notes = "主要功能之一，用户通过表单上传小说")
   public RespBean uploadNovel(Novel novel){
      return  novelService.uploadNovel(novel);
   }
   @PostMapping("/file")
   @ApiOperation(value = "上传文件到服务器返回资源地址",notes = "在上传小说表单的时候上传小说缩略图/小说文件的通用接口")
   public RespBean uploadFile(MultipartFile file){
      return novelService.uploadFile(file);
   }
}
