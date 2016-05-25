package com.swuos.ALLFragment.library.util;

import com.swuos.net.OkhttpNet;
import com.swuos.swuassistant.Constant;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.RequestBody;

/**
 * Created by 张孟尧 on 2016/5/24.
 */
public class LibSearch {
    /**
     * @param bookName 书名
     * @return 只能返回搜索结果的第一页, 搜索更多请使用下面的方法
     */
    @Deprecated
    public String bookSearch(String bookName) {
        RequestBody requestBody = new FormBody.Builder()
                .add("title", "")
                .add("su", "")
                .add("flword", "")
                .add("viewtype", "")
                .add("corename", "")
                .add("oneSearchWord", "")
                .add("onefq", "")
                .add("searchModel", "front")
                .add("searchType", "oneSearch")
                .add("tag", "search")
                .add("subtag", "simsearch")
                .add("searchFieldType", "text")
                .add("field", "title")
                .add("gcbook", "no")
                .add("aliasname", "全部馆藏")
                .add("q", bookName).build();
        OkhttpNet okhttpNet = new OkhttpNet();
        Headers header = new Headers.Builder()
                .add("Content-Type", "application/x-www-form-urlencoded")
                .build();
        return okhttpNet.doPost(Constant.librarySearch, requestBody, header);
    }

    /**
     * @param bookName 书名
     * @param page     页数,必须是0,10,20,30.....的格式,当page数超过书的总量是返回的网页没有书籍
     * @return 搜索结果
     */
    public String bookSearchMore(String bookName, int page) {
        RequestBody requestBody = new FormBody.Builder()
                .add("title", "")
                .add("flword", "")
                .add("corename", "")
                .add("oneSearchWord", "title" + bookName)
                .add("twoSearchWord", "")
                .add("combineSearchWold", "")
                .add("exactSearch", "")
                .add("onefq", "")
                .add("searchModel", "front")
                .add("searchType", "oneSearch")
                .add("field", "title")
                .add("gcbook", "yes")
                .add("pager.offset", String.valueOf(page))
                .add("advSearchWold", "")
                .add("flword", bookName)
                .add("q", bookName).build();
        OkhttpNet okhttpNet = new OkhttpNet();
        Headers header = new Headers.Builder()
                .add("Content-Type", "application/x-www-form-urlencoded")
                .build();
        return okhttpNet.doPost(Constant.librarySearch, requestBody, header);
    }

    /**
     * @param boodId 书的id可以从搜索的结果中获得
     * @return 书的详情
     */
    public String bookDetail(int boodId) {
        String url = Constant.libraryBookDetail + boodId;
        OkhttpNet okhttpNet = new OkhttpNet();
        return okhttpNet.doGet(url);
    }
}