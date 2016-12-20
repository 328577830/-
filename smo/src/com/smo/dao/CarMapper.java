package com.smo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.smo.pojo.Car;

//spring 注解:此接口/类是持久层
@Repository("carMapper")
public interface CarMapper {
	  /**
	   * 分页查询
	   * @param pageno 第几页
	   * @param pagesize 每页显示记录数目
	   * @param sort 排序字段
	   * @param order 排序方式(升序 / 降序)
	   * @param ename 多条件
	   * @param beginDate
	   * @param endDate
	   * @return
	   */
	  List<Car> findPager(
	      @Param("pageno")Integer pageno,
	      @Param("pagesize")Integer pagesize,
	      @Param("sort")String sort,
	      @Param("order")String order,
	      @Param("name")String name,
	      @Param("beginDate")Date beginDate,
	      @Param("endDate")Date endDate);
	  
	  /**
	   * 查总记录数
	   * @param ename
	   * @param beginDate
	   * @param endDate
	   * @return
	   */
	  int findTotal(
	      @Param("name")String name,
	      @Param("beginDate")Date beginDate,
	      @Param("endDate")Date endDate);
	
	@Insert(" insert into T_CAR(ID,NAME,SALE_DATE,PRICE) values(#{id},#{name},#{saleDate},#{price,jdbcType=DOUBLE}) ")
	@SelectKey(statement="select SEQ_CAR.nextval from DUAL",keyProperty="id",resultType=int.class,before=true)
	int add(Car car);
	
	@Update(" update T_CAR set NAME=#{name},SALE_DATE=#{saleDate},PRICE=#{price} where ID=#{id}")
	int modify(Car car);
	@Delete(" delete from T_CAR where ID=#{id}")
	int remove(Integer id);
	@Select(" select ID,NAME,SALE_DATE saleDate,PRICE from T_CAR where ID=#{id} ")
	Car findById(Integer id);
	@Select("select ID,NAME,SALE_DATE saleDate,PRICE from T_CAR")
	List<Car> find();
}
