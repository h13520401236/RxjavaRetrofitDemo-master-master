package com.example.retrofit.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.GridView;

import com.example.retrofit.HttpPostService;
import com.example.retrofit.R;
import com.example.retrofit.activity.adapter.DownAdapter;
import com.example.retrofit.entity.resulte.DownLoadResult;
import com.example.retrofit.entity.resulte.RetrofitEntity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.HttpDownManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpDownOnNextListener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.DbDownUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

public class DownActivity extends AppCompatActivity {


    private List<DownLoadResult.DataBean> data;
    private DownInfo downInfo;
    private DbDownUtil dbUtil;
    private List<DownInfo> listData;
    private String TAG = "downActivity";
    private GridView gradView;
    private DownLoadAdapter downLoadAdapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    List<DownLoadResult.DataBean> list = (List<DownLoadResult.DataBean>) msg.obj;
                    gradView = (GridView) findViewById(R.id.downLoad_movie_lv);

                    downLoadAdapter = new DownLoadAdapter(DownActivity.this, list);

                    gradView.setAdapter(downLoadAdapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);

        onButton9Click();
//        initResource();
//        initWidget();
//
//        HttpDownManager manager = HttpDownManager.getInstance();
//        DownInfo downInfo = new DownInfo("http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track7.mp4", new HttpDownOnNextListener() {
//            @Override
//            public void onNext(Object o) {
//                Log.i(TAG, "onNext: "+o);
//            }
//
//            @Override
//            public void onStart() {
//                Log.i(TAG, "onStart: ");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "onComplete: ");
//            }
//
//            @Override
//            public void updateProgress(long readLength, long countLength) {
//                Log.i(TAG, "updateProgress:  + read "+readLength);
//                Log.i(TAG, "updateProgress: countent = " +countLength);
//            }
//        });
//        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
//                "track4.mp4");
////        DownInfo apkApi = new DownInfo("http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track5.mp44");
//        downInfo.setId(111);
//        downInfo.setUpdateProgress(true);
//        downInfo.setSavePath(outputFile.getAbsolutePath());
//        manager.startDown(downInfo);


    }

    /**
     * Retrofit加入rxjava实现http请求
     */
    private void onButton9Click() {
        String BASE_URL = "http://www.izaodao.com/Api/";
        //手动创建一个OkHttpClient并设置超时时间
        okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

//        加载框
        final ProgressDialog pd = new ProgressDialog(this);

        HttpPostService apiService = retrofit.create(HttpPostService.class);
        Map<String, String> map = new HashMap<>();
        map.put("deviceId", "192893791");
        map.put("uid", "212");
        Observable<DownLoadResult> observable = apiService.getMovies("http://www.imstlife.com.cn/TW_Server/run/getVideoList.do", map);
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<DownLoadResult>() {
                            @Override
                            public void onCompleted() {
                                if (pd != null && pd.isShowing()) {
                                    pd.dismiss();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (pd != null && pd.isShowing()) {
                                    pd.dismiss();
                                }
                            }

                            @Override
                            public void onNext(DownLoadResult retrofitEntity) {
                                Log.i("listdata", "onNext: " + retrofitEntity.toString());
                                data = retrofitEntity.getData();
                                Message msg = Message.obtain();
                                msg.what = 1;
                                msg.obj = data;
                                handler.sendMessage(msg);
//                                dbUtil = DbDownUtil.getInstance();
//        listData = dbUtil.queryDownAll();
//        Log.i("listdata", "initResource: =" + listData);
//        /*第一次模拟服务器返回数据掺入到数据库中*/
//        if (listData.isEmpty()) {
//                                for (int i = 0; i < data.size(); i++) {
//                                    File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
//                                            "test" + i + ".mp4");
//                                    DownInfo apkApi = new DownInfo(data.get(i).getDownloadUrl());
//                                    apkApi.setId(i);
//                                    apkApi.setUpdateProgress(true);
//                                    apkApi.setSavePath(outputFile.getAbsolutePath());
//                                    dbUtil.save(apkApi);
//                                }
//                                listData = dbUtil.queryDownAll();

                            }

                            @Override
                            public void onStart() {
                                super.onStart();
                                pd.show();
                            }
                        }

                );
    }

    /*数据*/
    private void initResource() {
        onButton9Click();
//        dbUtil = DbDownUtil.getInstance();
//        listData = dbUtil.que、ryDownAll();
//        Log.i("listdata", "initResource: =" + listData);
//        /*第一次模拟服务器返回数据掺入到数据库中*/
//        if (listData.isEmpty()) {
//            for (int i = 0; i < data.size(); i++) {
//                File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
//                        "test" + i + ".mp4");
//                DownInfo apkApi = new DownInfo(data.get(i).getDownloadUrl());
//                apkApi.setId(i);
//                apkApi.setUpdateProgress(true);
//                apkApi.setSavePath(outputFile.getAbsolutePath());
//                dbUtil.save(apkApi);
//            }
//            listData = dbUtil.queryDownAll();
////        }
    }

    /*加载控件*/
    private void initWidget() {
        EasyRecyclerView recyclerView = (EasyRecyclerView) findViewById(R.id.rv);
        DownAdapter adapter = new DownAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.addAll(listData);
    }


}
