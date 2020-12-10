package com.example.demo.mapper;

import com.example.demo.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM files WHERE filename = #{fileName}")
    File getFile(String fileName);

    @Insert("INSERT INTO files ( filename,userid) VALUES(#{fileName}, #{owner})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFileUrl(File file);

    @Select("SELECT * FROM files")
    List<File> getAllFiles();

    @Delete("DELETE FROM files WHERE fileId = #{fileId}")
    Integer deleteFile(int id);
}
