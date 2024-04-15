/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-08-07 17:45
 * @Since:
 */
package com.zja.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: zhengja
 * @since: 2023/08/07 17:45
 */
@Data
public final class PageData<T> implements Serializable {

    @ApiModelProperty("数据")
    private List<T> data;
    @ApiModelProperty("当前页号")
    private int index;
    @ApiModelProperty("页大小")
    private int size;
    @ApiModelProperty("查询到的数据量")
    private int length;
    @ApiModelProperty("总数据量")
    private long count;
    @ApiModelProperty("总页数")
    private int pages;
    @ApiModelProperty("是否第一页")
    private boolean first;
    @ApiModelProperty("是否最后一页")
    private boolean last;
    @ApiModelProperty("是否有下一页")
    private boolean hasNext;
    @ApiModelProperty("是否有上一页")
    private boolean hasPrev;

    public static <T> PageData<T> of(Page<T> page) {
        return of(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements());
    }

    public static <T> PageData<T> of(@Nonnull List<T> data, int pageIndex, int pageSize, long totalSize) {
        PageData<T> dp = new PageData<>();
        dp.data = data;
        dp.index = pageIndex;
        dp.size = pageSize;
        dp.length = data.size();
        dp.count = totalSize;
        dp.pages = BigDecimal.valueOf(totalSize).divide(BigDecimal.valueOf(pageSize), RoundingMode.UP).intValue();
        dp.first = pageIndex == 0;
        dp.hasPrev = !dp.first;
        dp.hasNext = (dp.pages - dp.index) > 1;
        dp.last = !dp.hasNext;
        return dp;
    }

    public void convert(Function<T, T> mapper) {
        this.data = data.stream().map(mapper).collect(Collectors.toList());
    }

    public <R extends Serializable> PageData<R> map(Function<List<T>, List<R>> mapper) {
        return of(mapper.apply(this.getData()), this.getIndex(), this.getSize(), this.getCount());
    }
}
