package com.example.demo.mapper;

import com.example.demo.model.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM files WHERE id = #{id}")
    File getFileUrl(int id);
    int insertFileUrl(File file);
}
