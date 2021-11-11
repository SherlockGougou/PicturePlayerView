package com.xiuyukeji.pictureplayerview.sample.utils;

import androidx.annotation.IntDef;

/**
 * 图片信息管理
 *
 * @author Created by jz on 2017/3/29 17:36
 */
public class PictureInfoUtil {

    public static final int OPAQUE = 0, TRANSPARENT = 1;
    private static volatile PictureInfoUtil instance;
    private final String mFileName = "lottielogo";
    private final String mTransparentFileName = "lottielogo_transparent";
    private final String[] mPaths;
    private final String[] mTransparentPaths;
    private final long mDuration;
    private int mType = OPAQUE;

    private PictureInfoUtil() {
        int count = 271;

        mPaths = new String[count];
        mTransparentPaths = new String[count];

        for (int i = 0; i < count; i++) {
            mPaths[i] = String.format("%s/lottie_%s.png", mFileName, getIndex(count, i + 1));
            mTransparentPaths[i] = String.format("%s/lottie_%s.png", mTransparentFileName, getIndex(count, i + 1));
        }

        mDuration = count * 1000 / 25;
    }

    public static PictureInfoUtil get() {
        if (instance == null) {
            synchronized (PictureInfoUtil.class) {
                if (instance == null) {
                    instance = new PictureInfoUtil();
                }
            }
        }
        return instance;
    }

    private static String getIndex(int max, int i) {
        return String.format("%0" + String.valueOf(max).length() + "d", i);
    }

    public int getType() {
        return mType;
    }

    public void setType(@PictureType int type) {
        this.mType = type;
    }

    public long getDuration() {
        return mDuration;
    }

    public String getFileName() {
        return mType == OPAQUE ? mFileName : mTransparentFileName;
    }

    public String[] getPaths() {
        return mType == OPAQUE ? mPaths : mTransparentPaths;
    }

    @IntDef({OPAQUE, TRANSPARENT})
    private @interface PictureType {
    }
}
