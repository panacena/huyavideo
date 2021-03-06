package video.zkk.com.huyavideo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class RefreshHeaderView extends TextView implements SwipeRefreshTrigger, SwipeTrigger {

    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onRefresh() {
        setText("下加载中...");
    }

    @Override
    public void onPrepare() {
        setText("ggggggggg");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                setText("松开加载更多...");
            } else {
                setText("下拉加载更多...");
            }
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        setText("加载完成!");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
