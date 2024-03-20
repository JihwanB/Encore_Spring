package com.example.encore_spring_pjt.ctrl.board.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// List<BoardResponse> + Pagination 정보를 담는 객체로서의 역할
@Getter
@Setter
public class PageResponse<T> {
    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public PageResponse(List<T> list, Pagination pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }

}
