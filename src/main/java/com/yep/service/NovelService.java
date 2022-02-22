package com.yep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yep.pojo.Novel;
import com.yep.pojo.RespBean;
import com.yep.pojo.RespPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author HuangSir
 * @date 2022-02-21 14:43
 */
public interface NovelService extends IService<Novel> {
   /**
    * 获取当前用户收藏的小说列表
    * @return
    */
   List<Novel> getCurrentUserNovelCollections();

   /**
    * 从数据库中根据热度查询前十名的小说集合
    * @return
    */
   List<Novel> getTopNovels();

   /**
    * 根据指定类型查找小说集合
    * @param type
    * @return
    */
   List<Novel> getNovelsByType(Integer type);

   /**
    * 根据指定作者查找小说集合（模糊查询）
    * @param author
    * @return
    */
   List<Novel> getNovelsByAuthor(String author);
   /**
    * 获得所有小说的分页信息
    * @return
    */
   RespPage getAllNovels(Integer currPage, Integer size);

   /**
    * 根据指定ID获取小说详细信息
    * @return
    */
   Novel getNovelDetailsById(Integer id);

   /**
    * 将指定ID的书籍添加到当前用户收藏中
    * @param id
    * @return
    */
   RespBean addCollection(Integer id);

   /**
    * 根据指定ID获取小说文件下载地址
    * @param id
    * @return
    */
   String downloadNovel(Integer id);

   /**
    * 模糊查询小说标题名分页信息
    * @param name
    * @return
    */
   RespPage search(String name,Integer currPage,Integer size);

   RespBean uploadNovel(Novel novel);

   RespBean uploadFile(MultipartFile file);
}
