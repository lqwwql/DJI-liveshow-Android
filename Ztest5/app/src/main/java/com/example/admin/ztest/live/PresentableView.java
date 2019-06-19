package com.example.admin.ztest.live;

/**
 * Created by wrs on 2019/6/19,11:48
 * projectName: Ztest5
 * packageName: com.example.admin.ztest.live
 */

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;


public interface PresentableView {
    /**
     * Returns string id for the description of this View. This might be shown inside the View itself.
     */
    @StringRes
    int getDescription();

    /**
     * Return the hint to user on how to find this View in code
     * @return
     */
    @NonNull
    String getHint();
}
