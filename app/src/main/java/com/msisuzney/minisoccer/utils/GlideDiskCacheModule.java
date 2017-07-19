package com.msisuzney.minisoccer.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by chenxin.
 * Date: 2017/7/19.
 * Time: 14:25.
 */

public class GlideDiskCacheModule implements GlideModule {
    private static int cacheSize = 100*1024*1024;
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //保存到外部存储中
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,cacheSize));

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
