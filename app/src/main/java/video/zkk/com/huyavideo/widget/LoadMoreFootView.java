package video.zkk.com.huyavideo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class LoadMoreFootView extends TextView implements SwipeTrigger, SwipeLoadMoreTrigger {
    public LoadMoreFootView(Context context) {
        super(context);
    }

    public LoadMoreFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadMore() {
        setText("加载更多...");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                setText("松开加载更多...");
            } else {
                setText("上拉加载更多...");
            }
        } else {
            setText("加载完毕...");
        }
    }

    @Override
    public void onRelease() {
        setText("LOADING MORE");
    }

    @Override
    public void onComplete() {
        setText("完成!");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
