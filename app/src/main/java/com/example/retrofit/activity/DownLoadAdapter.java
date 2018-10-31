package com.example.retrofit.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.retrofit.R;
import com.example.retrofit.entity.resulte.DownLoadResult;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.HttpDownManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpDownOnNextListener;

import java.io.File;
import java.util.List;
import java.util.Vector;

import static com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState.DOWN;
import static com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState.ERROR;
import static com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState.FINISH;
import static com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState.PAUSE;
import static com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState.START;
import static com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownState.STOP;

/**
 * Created by heng on 2018/10/31
 */
public class DownLoadAdapter extends BaseAdapter {
    private Context mContext;
    private List<DownLoadResult.DataBean> mList;
    private DownInfo apkApi;
    private HttpDownManager manager;

    public DownLoadAdapter(Context context, List<DownLoadResult.DataBean> list) {
        this.mContext = context;
        this.mList = list;
        manager = HttpDownManager.getInstance();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public DownLoadResult.DataBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.download_movies_layout, null);
            holder.moviesImg = convertView.findViewById(R.id.movies_img);
            holder.moviesName = convertView.findViewById(R.id.movies_name_tv);
            holder.progressBar = convertView.findViewById(R.id.firstBar);
            holder.downLoadBtn = convertView.findViewById(R.id.download);
            holder.tipRate = convertView.findViewById(R.id.tip_rate);
            holder.hideMovie = convertView.findViewById(R.id.hide_movies);
            holder.showMovie = convertView.findViewById(R.id.show_movies);
            holder.deleteMovie = convertView.findViewById(R.id.delete_movies);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.downLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("adapter111", "onClick: 点击按钮");
                DownInfo downInfo = new DownInfo(mList.get(position).getDownloadUrl(), new HttpDownOnNextListener() {
                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void updateProgress(long readLength, long countLength) {
                        holder.progressBar.setMax((int) countLength);
                        holder.progressBar.setProgress(((int) readLength) > 0 ? (int) readLength : -((int) readLength));
                        Log.i("adapter111", "updateProgress: countLength = " + countLength);
                        Log.i("adapter111", "updateProgress: " + readLength);
                    }
                });
                   File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        "track" + position + 1 + ".mp4");
                downInfo.setId(mList.get(position).getId());
                downInfo.setUpdateProgress(true);
                downInfo.setSavePath(outputFile.getAbsolutePath());
                holder.progressBar.setMax((int) downInfo.getCountLength());
                holder.progressBar.setProgress((int) downInfo.getReadLength());

                manager.startDown(downInfo);
//                setData(downInfo);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView moviesImg;
        TextView moviesName, tipRate;
        Button downLoadBtn, hideMovie, showMovie, deleteMovie;//
        NumberProgressBar progressBar;
    }

    private void setData(DownInfo apkApi) {

        /*第一次恢复 */
        switch (apkApi.getState()) {
            case START:
                /*起始状态*/
                break;
            case PAUSE:
                Log.i("adapter111", "setData: 暂停中");
                break;
            case DOWN:
                manager.startDown(apkApi);
                Log.i("adapter111", "setData: 重新下载");
                break;
            case STOP:
                Log.i("adapter111", "setData:下载停止");
                break;
            case ERROR:
                Log.i("adapter111", "setData: 下载错误");
                break;
            case FINISH:
                Log.i("adapter111", "setData: 下载完成");
                break;
        }
    }


}




