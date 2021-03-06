package com.smo.util;

import java.util.List;

/**
 * 封装分页对象
 * 
 * @author SONG
 *
 */
public class Pager<T> {
  private int total; // 总记录数
  private List<T> rows; // 分页数据的集合

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> rows) {
    this.rows = rows;
  }
}
