package com.epam.esm.dto;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestParameters that = (RequestParameters) o;
        return currentPage == that.currentPage &&
                pageSize == that.pageSize &&
                Objects.equals(sortType, that.sortType) &&
                Objects.equals(sortValue, that.sortValue) &&
                Objects.equals(searchParameter, that.searchParameter) &&
                Objects.equals(searchValue, that.searchValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, pageSize, sortType, sortValue, searchParameter, searchValue);
    }

    @Override
    public String toString() {
        return "RequestParameters{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", sortType='" + sortType + '\'' +
                ", sortValue='" + sortValue + '\'' +
                ", searchParameter='" + searchParameter + '\'' +
                ", searchValue=" + searchValue +
                '}';
    }
}
