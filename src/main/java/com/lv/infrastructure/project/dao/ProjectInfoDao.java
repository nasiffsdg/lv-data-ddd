package com.lv.infrastructure.project.dao;

import com.lv.domain.project.ProjectInfoDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author QiangZai
 * @version 1.0
 * @date 2023/8/6 1:28 AM
 */
public interface ProjectInfoDao {

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    ProjectInfoDo queryById(Long id);

    /**
     * 统计总行数
     * @param projectInfoDo 查询条件
     * @return 总行数
     */
    long count(ProjectInfoDo projectInfoDo);

    /**
     * 新增数据
     * @param projectInfoDo 实例对象
     * @return 影响行数
     */
    int insert(ProjectInfoDo projectInfoDo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     * @param entities List<ProjectInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProjectInfoDo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     * @param entities List<ProjectInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ProjectInfoDo> entities);

    /**
     * 修改数据
     * @param projectInfoDo 实例对象
     * @return 影响行数
     */
    int update(ProjectInfoDo projectInfoDo);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
