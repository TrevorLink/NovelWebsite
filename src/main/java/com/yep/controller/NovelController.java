package com.yep.controller;

import com.yep.pojo.Novel;
import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;
import com.yep.service.NovelService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

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
   @ApiImplicitParam(name = "type",value = "小说类型",type = "int",paramType = "query",required = true)
   public  List<Novel> getNovelsByType(Integer type){
      return novelService.getNovelsByType(type);
   }
   @GetMapping("/author")
   @ApiOperation(value = "根据指定作者查询小说列表",notes = "主要功能之一：小说分类（根据名称模糊查询）")
   @ApiImplicitParam(name = "author",value = "指定的小说作者",type = "String",paramType = "query",required = true)
   public List<Novel> getNovelsByAuthor(String author){
      return  novelService.getNovelsByAuthor(author);
   }
   @GetMapping("/")
   @ApiOperation(value = "获取小说列表分页信息",notes = "主页中小说分页数据，出于效率考量，这里不全部返回所有的小说信息，只是返回一个特殊的分页信息（ID，图片，名称）")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "currPage",value = "当前页码（默认为1）",type = "int",paramType = "query",required = false),
           @ApiImplicitParam(name = "size",value = "每页展示的数量（默认为10）",type = "int",paramType = "query",required = false)
   })
   public RespPage getAllNovels(@RequestParam(defaultValue = "1")Integer currPage,
                                @RequestParam(defaultValue = "10") Integer size){
      return  novelService.getAllNovels(currPage,size);
   }
   @GetMapping("/details")
   @ApiOperation(value = "查看小说详细信息",notes = "在主页或者是管理端的时候只有分页数据，点击调用这个接口获取小说的详细信息")
   @ApiImplicitParam(name = "id",value = "小说ID",type = "int",paramType = "query")
   public Novel getNovelDetailsById(@ApiParam(required = true) Integer id){
      return  novelService.getNovelDetailsById(id);
   }
   @PutMapping("collection")
   @ApiOperation(value = "添加指定小说到我的收藏",notes = "在小说的详细信息中点击添加到收藏")
   @ApiImplicitParam(name = "id",value = "小说ID",type = "int",paramType = "query")
   public RespBean addCollection(@ApiParam(required = true) Integer id){
      return novelService.addCollection(id);
   }
   @GetMapping("download")
   @ApiOperation(value = "下载小说",notes = "在小说的详细信息中点击下载小说")
   @ApiImplicitParam(name = "id",value = "小说ID",type = "int",paramType = "query")
   public String downloadNovel(@ApiParam(required = true) Integer id){
      return novelService.downloadNovel(id);
   }
   @GetMapping("/search")
   @ApiOperation(value = "根据指定名称模糊查询小说分页信息",notes = "返回模糊查询的小说分页信息")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "currPage",value = "当前页码（默认为1）",type = "int",paramType = "query",required = false,defaultValue = "1"),
           @ApiImplicitParam(name = "size",value = "每页展示的数量（默认为10）",type = "int",paramType = "query",required = false,defaultValue = "10"),
           @ApiImplicitParam(name = "name",value = "模糊查询的小说名称",type = "String",paramType = "query",required = true)
   })
   public  RespPage search(String name,
                           @RequestParam(defaultValue = "1") Integer currPage,
                            @RequestParam(defaultValue = "10") Integer size){
      return novelService.search(name,currPage,size);
   }
   @PostMapping("/novel")
   @ApiOperation(value = "上传小说",notes = "主要功能之一，用户通过表单上传小说")
   @ApiImplicitParams({
           @ApiImplicitParam(name = "name",value = "小说标题",required = true),
           @ApiImplicitParam(name = "type",value = "小说类型",type = "int",required = true),
           @ApiImplicitParam(name = "author",value = "小说作者",required = true),
           @ApiImplicitParam(name = "description",value = "小说描述",required = true),
           @ApiImplicitParam(name = "imageUrl",value = "小说封面地址",required = true),
           @ApiImplicitParam(name = "downloadUrl",value = "小说文件地址",required = true),
           @ApiImplicitParam(name = "hot",value = "小说热度",required = true)
   })
   public RespBean uploadNovel(@ApiIgnore Novel novel){
      return  novelService.uploadNovel(novel);
   }
   @PostMapping(value = "/file",headers = "content-type=multipart/form-data")
   @ApiOperation(value = "上传文件到服务器返回资源地址",notes = "在上传小说表单的时候上传小说缩略图/小说文件的通用接口")
   @ApiParam(name = "file",value = "待上传的文件/图片",required = true)
   public RespBean uploadFile(MultipartFile file){
      return novelService.uploadFile(file);
   }
}
