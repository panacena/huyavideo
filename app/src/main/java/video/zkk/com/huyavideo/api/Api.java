package video.zkk.com.huyavideo.api;

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class Api {

    public static String base_url="http://192.168.1.103:8080/";

    //http://121.43.38.23:8080/gb28181_up/meizhi?start=0&pageNum=10
    public   static String FULI_URL=base_url+"cVideoMonitor_yichang_up/fuli";

    public   static String Xiusekexue_URL=base_url+"cVideoMonitor_yichang_up/xiusekexue";

    public   static String Erciyuan_URL=base_url+"cVideoMonitor_yichang_up/erciyuan";

    public   static String Meizhi_URL=base_url+"cVideoMonitor_yichang_up/meizhi";

    public   static String  Zhaiwu_URL=base_url+"cVideoMonitor_yichang_up/zhaiwu";

    public   static String  Video="http://v-api-play.huya.com/index.php";


    public   static String  DouYuVideo="https://apiv2.douyucdn.cn/videonc/Stream/getAppPlayer?client_sys=android";


    public   static String  SmallVideo="https://apiv2.douyucdn.cn/video/ShortVideo/getTopicVideoList?tid=2&limit=20&client_sys=android";
    public   static String  YuLeVideo2="https://apiv2.douyucdn.cn/video/ShortVideo/getCateVideoList?cid=14&limit=20&client_sys=android";


    public   static String  YuLeVideo="https://v.douyu.com/video/shortvideo/listAjax?limit=50&page=1&uid=0&type=tag&id=14";



    public   static String  ShengHuoYuLeWuDao="https://apiv2.douyucdn.cn/Video/Video/getCate2VideoList1?cate2_id=18&limit=20&action=hot&client_sys=android";



    public   static String  SmallVideoUrl="https://vmobile.douyu.com/video/getInfo";


    public   static String  HuoShanLiveUrl="https://api.huoshan.com/hotsoon/feed/?type=live&live_source=live_small_picture&min_time=0&count=20&live_sdk_version=230&iid=11367420541&device_id=19583544230&ac=wifi&channel=xiaomi&aid=1112&app_name=live_stream&version_code=230&version_name=2.3.0&device_platform=android&ssmix=a&device_type=Redmi+Note+3&device_brand=Xiaomi&os_api=21&os_version=5.0.2&uuid=869677028810872&openudid=5ed40069e20354be&manifest_version_code=230&resolution=1080*1920&dpi=480&update_version_code=2301";

    //微时刻
    public   static String  WeiShiKeVideoUrl="https://apiv2.douyucdn.cn/Video/Video/getCate1VideoList1?cate1_id=4&limit=20&action=hot&client_sys=android";
}
