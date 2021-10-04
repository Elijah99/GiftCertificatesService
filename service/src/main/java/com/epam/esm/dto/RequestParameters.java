package com.epam.esm.dto;

import java.util.List;

public class RequestParameters {

    private int currentPage;
    private int pageSize;
    private String sortType;
    private String sortValue;
    private String searchParameter;
    private List<String> searchValue;

    public RequestParameters(int currentPage, int pageSize, String sortType, String sortValue, String searchParameter, List<String> searchValue) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.sortType = sortType;
        this.sortValue = sortValue;
        this.searchParameter = searchParameter;
        this.searchValue = searchValue;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public List<String> getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(List<String> searchValue) {
        this.searchValue = searchValue;
    }
}
