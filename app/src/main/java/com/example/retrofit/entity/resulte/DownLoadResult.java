package com.example.retrofit.entity.resulte;

import java.util.List;

/**
 * Created by heng on 2018/10/31
 */
public class DownLoadResult {

    /**
     * msg : success
     * data : [{"id":1,"name":"盘山步道","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1506335764398.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track3.mp4","type":0,"price":null,"qrUrl":null,"enName":"Road Jogging"},{"id":2,"name":"白云远山","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1506335769470.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track5.mp4","type":0,"price":null,"qrUrl":null,"enName":"Open Space"},{"id":3,"name":"晨光弱影","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1506335784996.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track8.mp4","type":0,"price":null,"qrUrl":null,"enName":"Daybreak"},{"id":4,"name":"林荫步道","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1516861141094.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track1.mp4","type":0,"price":null,"qrUrl":null,"enName":"Forest Jogging"},{"id":5,"name":"高山曲径","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1516861304687.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track2.mp4","type":0,"price":null,"qrUrl":null,"enName":"Foothills"},{"id":6,"name":"高山远路","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1516861333639.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track4.mp4","type":0,"price":null,"qrUrl":null,"enName":"Mountain Highway"},{"id":7,"name":"海岸公路","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1516861346383.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track6.mp4","type":0,"price":null,"qrUrl":null,"enName":"Road Near Ocean"},{"id":8,"name":"公园小径","thumbUrl":"http://myclub.oss-cn-shanghai.aliyuncs.com/img/1516861353079.jpg","downloadUrl":"http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track7.mp4","type":0,"price":null,"qrUrl":null,"enName":"Garden Walk"}]
     * status : 0
     */

    private String msg;
    private String status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 盘山步道
         * thumbUrl : http://myclub.oss-cn-shanghai.aliyuncs.com/img/1506335764398.jpg
         * downloadUrl : http://marvsmart2.oss-cn-shanghai.aliyuncs.com/TaiWan/track/track3.mp4
         * type : 0
         * price : null
         * qrUrl : null
         * enName : Road Jogging
         */

        private int id;
        private String name;
        private String thumbUrl;
        private String downloadUrl;
        private int type;
        private Object price;
        private Object qrUrl;
        private String enName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public Object getQrUrl() {
            return qrUrl;
        }

        public void setQrUrl(Object qrUrl) {
            this.qrUrl = qrUrl;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", thumbUrl='" + thumbUrl + '\'' +
                    ", downloadUrl='" + downloadUrl + '\'' +
                    ", type=" + type +
                    ", price=" + price +
                    ", qrUrl=" + qrUrl +
                    ", enName='" + enName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DownLoadResult{" +
                "msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
