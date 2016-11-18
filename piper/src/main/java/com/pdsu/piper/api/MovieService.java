package com.pdsu.piper.api;

import com.pdsu.piper.bean.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/18.
 */

public interface MovieService
{
    /**
     * 获取电影数据接口
     *
     * @param start 开始位置
     * @param cunt  总数
     * @return MovieEntity
     */
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int cunt);
}
