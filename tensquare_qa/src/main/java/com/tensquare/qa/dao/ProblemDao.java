package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(value = "select * from tb_problem tp, tb_pl tpl where tp.id=tpl.problemid and labelid = ? ORDER BY replytime Desc",nativeQuery = true) // nativeQuery支持sql语句
    public Page<Problem> newlist(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem tp, tb_pl tpl where tp.id=tpl.problemid and labelid = ? ORDER BY reply Desc",nativeQuery = true)
	public Page<Problem> hotlist(String labelid, Pageable pageable);

    @Query(value = "select * from tb_problem tp, tb_pl tpl where tp.id=tpl.problemid and labelid = ? and reply = 0 ORDER BY createtime Desc",nativeQuery = true)
	public Page<Problem> waitlist(String labelid, Pageable pageable);
}
