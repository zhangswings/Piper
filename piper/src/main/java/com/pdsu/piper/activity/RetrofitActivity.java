package com.pdsu.piper.activity;

import com.pdsu.piper.R;
import com.pdsu.piper.api.MovieService;
import com.pdsu.piper.bean.MovieEntity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit && RxJava Test
 * use butterknife
 */
public class RetrofitActivity extends AppCompatActivity
{
    //请求接口baseUrl
    String baseUrl = "https://api.douban.com/v2/movie/";


    @Bind(R.id.click_me_BN)
    Button mClickMeBN;
    @Bind(R.id.result_TV)
    TextView mResultTV;

    //进行网络请求
    private void getMovie()
    {
        //初始化retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieEntity> topMovieCall = movieService.getTopMovie(0, 10);

        topMovieCall.enqueue(new Callback<MovieEntity>()
        {
            //请求失败
            @Override
            public void onFailure(final Call<MovieEntity> call, final Throwable t)
            {
                mResultTV.setText(t.getMessage());
            }

            //请求成功
            @Override
            public void onResponse(final Call<MovieEntity> call, final Response<MovieEntity> response)
            {
                mResultTV.setText(response.body().toString());
            }
        });
    }

    @OnClick(R.id.click_me_BN)
    public void onClick()
    {
        getMovie();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }
}
