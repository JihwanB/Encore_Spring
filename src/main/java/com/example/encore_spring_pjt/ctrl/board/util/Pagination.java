package com.example.encore_spring_pjt.ctrl.board.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Pagination {

    // 전체 데이터 수
    private int totalRecordCnt;

    // 전체 페이지 수
    private int totalPageCnt;

    // 첫 페이지, 마지막 페이지 번호
    private int firstPage;
    private int lastPage;

    // limit offset 위치 번호
    private int limitStart;

    // 이전페이지, 다음페이지 존재하는지 여부
    private boolean existsPrevPage;
    private boolean existsNextPage;

    public Pagination() {
    }

    public Pagination(int totalRecordCnt, PageDTO params) {
        if (totalRecordCnt > 0) {
            this.totalRecordCnt = totalRecordCnt;
            calc(params);
        }
    }

    public void calc(PageDTO params) {
        // 전체 페이지 수
        this.totalPageCnt = ((totalRecordCnt - 1) / params.getRecordSize()) + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우
        // 현재 페이지에 전체 페이지 수를 저장
        if (params.getPage() > this.totalPageCnt)
            params.setPage(this.totalPageCnt);

        // 첫 페이지 번호 계산
        this.firstPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize();

        // 마지막 페이지 번호 계산
        this.lastPage = this.firstPage + params.getPageSize() - 1;

        // 마지막 페이지 번호가 전체 페이지 수보다 큰 경우
        // 마지막 페이지에 전체 페이지 수를 저장
        if (this.lastPage > this.totalPageCnt)
            this.lastPage = this.totalPageCnt;

        // limit 시작 위치
        this.limitStart = (params.getPage() - 1) * params.getRecordSize();

        // 이전 페이지 존재 여부
        this.existsPrevPage = (firstPage != 1);

        // 다음 페이지 존재 여부
        this.existsNextPage = (lastPage * params.getRecordSize()) < totalRecordCnt;
    }

}
