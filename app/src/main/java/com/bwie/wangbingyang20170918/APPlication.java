package com.bwie.wangbingyang20170918;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;

import com.bwie.wangbingyang20170918.utils.CrashHandler;
import com.bwie.wangbingyang20170918.utils.LogcatHelper;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * 作者：王兵洋  2017/9/18 09:19
 * 类的用途：
 */
public class APPlication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initImageloader();
        CrashHandler.getInstance().init(this);//初始化全局异常管理
        this.mContext = this;
        LogcatHelper.getInstance(this).start();
    }

    public static Context getContext() {
        return mContext;
    }

    public void initImageloader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .resetViewBeforeLoading(false) // default
                .delayBeforeLoading(0).cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .handler(new Handler()) // default
                .build();

        File picPath = new File(Environment.getExternalStorageDirectory()
                .getPath() + File.separator + "MyDemo" + File.separator + "files");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3)
                // default
                .threadPriority(Thread.NORM_PRIORITY - 1)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                // default
                .diskCache(new UnlimitedDiskCache(picPath))
                // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(1000)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // default
                .imageDownloader(
                        new BaseImageDownloader(getApplicationContext())) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(options) // default
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }
}
