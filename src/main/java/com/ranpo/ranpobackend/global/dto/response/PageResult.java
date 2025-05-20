package com.ranpo.ranpobackend.global.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResult<T> {

    private List<T> content;
    private PageInfo pageInfo;

    private PageResult(List<T> content, PageInfo page) {
        this.content = content;
        this.pageInfo = page;
    }

    public static <T> PageResult<T> from(Page<T> pageData) {
        return new PageResult<>(
                pageData.getContent(),
                PageInfo.from(pageData)
        );
    }

    @Getter
    public static class PageInfo {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private boolean last;

        private PageInfo(int page, int size, long totalElements, int totalPages, boolean last) {
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.last = last;
        }

        public static PageInfo from(Page<?> page) {
            return new PageInfo(
                    page.getNumber(),
                    page.getSize(),
                    page.getTotalElements(),
                    page.getTotalPages(),
                    page.isLast()
            );
        }
    }
}
