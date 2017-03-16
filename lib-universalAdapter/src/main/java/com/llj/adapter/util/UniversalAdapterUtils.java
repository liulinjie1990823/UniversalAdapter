package com.llj.adapter.util;

import android.view.View;

/**
 * PROJECT:CommonAdapter
 * DESCRIBE:
 * Created by llj on 2017/2/11.
 */

public class UniversalAdapterUtils {
    public static final int VIEW_HOLDER_TAG_ID = 10086;

    public static final int VIEW_HOLDER_INDEX_ID = 10087;

    public static void setViewHolder(View view, Object holder) {
        if (view != null) {
            view.setTag(VIEW_HOLDER_TAG_ID, holder);
        }
    }

    @SuppressWarnings("unchecked")
    public static <Holder> Holder getViewHolder(View view) {
        if (view == null) {
            return null;
        }
        return (Holder) view.getTag(VIEW_HOLDER_TAG_ID);
    }

    public static int getIndex(View view) {
        return (int) view.getTag(VIEW_HOLDER_INDEX_ID);
    }
}
