package video.zkk.com.huyavideo.bean.home;

import java.util.List;

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class FuliBean {

    /**
     * code : 200
     * msg : success
     * user : [{"href_src":"http://v.huya.com/play/7960367.html","id":1,"img_src":"http://vimg.dwstatic.com/1722/7960367/9-220x124.jpg","title":"【福利】人好看还是歌好听","video_id":"7960367"},{"href_src":"http://v.huya.com/play/7873677.html","id":2,"img_src":"http://vimg.dwstatic.com/1722/7873677/9-220x124.jpg","title":"【福利】小白兔，两只耳朵竖起来~","video_id":"7873677"},{"href_src":"http://v.huya.com/play/7489419.html","id":3,"img_src":"http://vimg.dwstatic.com/1721/7489419/9-220x124.jpg","title":"【福利】端午节临近你需要补充营养","video_id":"7489419"},{"href_src":"http://v.huya.com/play/7422389.html","id":4,"img_src":"http://vimg.dwstatic.com/1721/7422389/9-220x124.jpg","title":"【福利】喔哦~跟上这节奏","video_id":"7422389"},{"href_src":"http://v.huya.com/play/7341675.html","id":5,"img_src":"http://vimg.dwstatic.com/1721/7341675/9-220x124.jpg","title":"【福利】这身材你打几分？","video_id":"7341675"},{"href_src":"http://v.huya.com/play/7276365.html","id":6,"img_src":"http://vimg.dwstatic.com/1721/7276365/9-220x124.jpg","title":"【福利】丰满型才带劲！","video_id":"7276365"},{"href_src":"http://v.huya.com/play/7213331.html","id":7,"img_src":"http://vimg.dwstatic.com/1721/7213331/9-220x124.jpg","title":"【福利】我。。。感觉我有点晕胸","video_id":"7213331"},{"href_src":"http://v.huya.com/play/7147951.html","id":8,"img_src":"http://vimg.dwstatic.com/1721/7147951/9-220x124.jpg","title":"【福利】频率太快了，我受不了啊！","video_id":"7147951"},{"href_src":"http://v.huya.com/play/6896825.html","id":9,"img_src":"http://vimg.dwstatic.com/1720/6896825/9-220x124.jpg","title":"【福利】是不是能看到底裤呀","video_id":"6896825"},{"href_src":"http://v.huya.com/play/6823549.html","id":10,"img_src":"http://vimg.dwstatic.com/1720/6823549/9-220x124.jpg","title":"【福利】阿英又穿上女仆装了","video_id":"6823549"}]
     */

    private int code;
    private String msg;
    private List<UserBean> user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * href_src : http://v.huya.com/play/7960367.html
         * id : 1
         * img_src : http://vimg.dwstatic.com/1722/7960367/9-220x124.jpg
         * title : 【福利】人好看还是歌好听
         * video_id : 7960367
         */

        private String href_src;
        private int id;
        private String img_src;
        private String title;
        private String video_id;

        public String getHref_src() {
            return href_src;
        }

        public void setHref_src(String href_src) {
            this.href_src = href_src;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg_src() {
            return img_src;
        }

        public void setImg_src(String img_src) {
            this.img_src = img_src;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }
    }
}
