package com.fly.fankun.model.result;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class PageObject<T> implements Serializable {

	private static final long serialVersionUID = 6941956017537570294L;

	@ApiModelProperty(value = "当前页数")
	private Integer pageNum;

	@ApiModelProperty(value = "每页条数")
	private Integer pageSize;

	@ApiModelProperty(value = "总条数")
	private Long totalCount;

	@ApiModelProperty(value = "结果集")
	private List<T> result;

	@ApiModelProperty(value = "请求时间")
	private Long time;

	@ApiModelProperty(value = "特殊数据")
	private Map<String, Object> specialData;

	public PageObject() {
	}

	public PageObject(List<T> list, Long totalCount, int pageNum, int pageSize) {
		this.result = list;
		this.totalCount = totalCount;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public PageObject(List<T> list, Page page) {
		this.result = list;
		this.totalCount = page.getTotal();
		this.pageNum = page.getPageNum();
		this.pageSize = page.getPageSize();
	}

	public PageObject(List<T> list, Long totalCount, int pageNum, int pageSize, Map<String, Object> specialData) {
		this.result = list;
		this.totalCount = totalCount;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.specialData = specialData;
	}
}
