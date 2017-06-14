package video.zkk.com.huyavideo.bean.smallvideo;

/**
 * Created by Administrator on 2017/6/11 0011.
 */

public class SmallVideoUrlBean {

    /**
     * error : 0
     * data : {"video_url":"http://videows1.douyucdn.cn/live/normal_14336731420170609191253-upload-00c7/playlist.m3u8?k=1e426ed8ec1eae307fb3908bf9042ed2&t=593d5d28&u=0&ct=h5&vid=788292&d="}
     */

    private int error;
    /**
     * video_url : http://videows1.douyucdn.cn/live/normal_14336731420170609191253-upload-00c7/playlist.m3u8?k=1e426ed8ec1eae307fb3908bf9042ed2&t=593d5d28&u=0&ct=h5&vid=788292&d=
     */

    private DataBean data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String video_url;

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }
    }
}
