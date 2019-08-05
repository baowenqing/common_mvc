package com.juziwl.commonlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.juziwl.commonlibrary.BuildConfig;
import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.glideprogress.OnLoadProgressListener;
import com.juziwl.commonlibrary.glideprogress.ProgressInterceptor;
import com.juziwl.commonlibrary.model.ImageSize;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.WeakHashMap;

/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月19日
 * @description 加载图片工具类
 */

public class LoadingImgUtil {
    public static final String GIF_SUFFIX = ".gif";


    /**
     * 通用版
     *
     * @param resId      显示的图
     * @param errorResId 占位/失败/默认图图
     * @param imageView
     */
    public static void loadImage(int resId, int errorResId, ImageView imageView) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(errorResId)
                .error(errorResId)
                .fallback(errorResId)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(resId)
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }


    /**
     * 通用版
     *
     * @param url        显示的图
     * @param errorResId 占位/失败/默认图图
     * @param imageView
     */
    public static void loadImageWithoutPlaceHolder(String url, int errorResId, ImageView imageView) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        RequestOptions options = new RequestOptions()
                //.placeholder(errorResId)
                .error(errorResId)
                .fallback(errorResId)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }


    /**
     * 没有设置占位图，并且默认图设定了宽高大小；
     */
    public static void loadImageWithoutPlaceHolder(String url, int errorResId
            , ImageView imageView, int width, int height) {

        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .error(errorResId)
                .fallback(errorResId)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new CustomListener(imageView, width, height))
                .into(imageView);
    }


    public static void loadImageWithPlaceHolder(String url, int errorResId, ImageView imageView) {

        if (imageView == null || imageView.getContext() == null) return;

        RequestOptions options = new RequestOptions()
                .placeholder(errorResId)
                .error(errorResId)
                .fallback(errorResId)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }


    /**
     * 添加头像，过滤头像中是否已包含了默认地址前缀；
     */
    public static void loadimg(String baseUrl, String url, int errorResId, ImageView imageView) {

        if (imageView == null || imageView.getContext() == null) {
            return;
        }

        if (url != null && url.contains(baseUrl)) {
            loadImageWithoutPlaceHolder(url, errorResId, imageView);
        } else {
            loadImageWithoutPlaceHolder(baseUrl + url, errorResId, imageView);
        }
    }


    /**
     * @param url
     * @param imageView
     * @param isShowHead false 是普通图片,,true 是头像图片
     */
    public static void loadimg(String url, ImageView imageView, boolean isShowHead) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        //common_default_head
        int resId = isShowHead ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .error(resId)
                .fallback(resId)
//                .dontTransform()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }


    public static void loadimgCenterCrop(String url, int resId, ImageView imageView) {

        if (imageView == null || imageView.getContext() == null) {
            return;
        }

        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(resId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .error(resId)
                .fallback(resId)
                .centerCrop()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    /**
     * @param url
     * @param imageView
     * @param isShowHead false 是普通图片,,true 是头像图片
     */
    public static void loadimgCenterCrop(String url, ImageView imageView, boolean isShowHead) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        //common_default_head
        int resId = isShowHead ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(resId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .error(resId)
                .fallback(resId)
                .centerCrop()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void loadimgWithoutPlaceholder(String url, ImageView imageView, ImageSize imageSize) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        int resId = android.R.color.transparent;
        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(resId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .fallback(resId)
                .error(resId)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void loadImgAds(String url, ImageView imgview, final onLoadingImageListener loadListener) {
        if (imgview == null || imgview.getContext() == null) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (!isLoadGIF(url)) {
            options = options
//                    .dontTransform()
                    .dontAnimate();
        }
        Glide.with(imgview.getContext()).load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(new DrawableImageViewTarget(imgview) {

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (loadListener != null) {
                            loadListener.onLoadingFailed();
                        }
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        super.onLoadCleared(placeholder);
                    }

                    @Override
                    public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        if (loadListener != null) {
                            loadListener.onLoadingComplete(null);
                        }
                    }
                });
    }

    public static void loadingLocalImage(String url, ImageSize imageSize, ImageView imageView) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(R.mipmap.common_falseimg);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.common_falseimg)
                .fallback(R.mipmap.common_falseimg)
                .error(R.mipmap.common_falseimg)
//                .dontTransform()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .load(url)
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void displayImageWithImageSize(String url, ImageView imageView, ImageSize imageSize,
                                                 onLoadingImageListener loadListener, boolean flag) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        int resId = flag ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(resId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .fallback(resId)
                .error(resId)
//                .dontTransform()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void displayImageWithImageSizeProgressBar(String url, ImageView imageView,
                                                            ImageSize imageSize, final ProgressBar progressBar, boolean isHead) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        int resId = isHead ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .fallback(resId)
                .error(resId)
//                .dontTransform()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(new DrawableImageViewTarget(imageView) {

                    private void setVisibility(boolean visible) {
                        if (progressBar != null) {
                            progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }
                    }

                    @Override
                    public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        setVisibility(false);
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        setVisibility(true);
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        setVisibility(false);
                    }
                });
    }

    public static void displayLongImageSize(String url, ImageView imageView, ImageSize imageSize) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        int resId = R.mipmap.common_banner_onloading;
        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(resId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .fallback(resId)
                .error(resId)
//                .dontTransform()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void displayImageWithSizeResIdListener(String url, ImageView imageView, ImageSize imageSize, int resId, final onLoadingImageListener listener) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .fallback(resId)
                .error(resId)
//                .dontTransform()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .asBitmap()
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        if (listener != null) {
                            listener.onLoadingComplete(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (listener != null) {
                            listener.onLoadingFailed();
                        }
                    }
                });
    }

    public static void displayImageWithoutPlaceholder(String url, ImageView imageView,
                                                      ImageSize imageSize, boolean isHeader,
                                                      OnLoadProgressListener progressListener) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        int resId = isHeader ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(resId);
            return;
        }
        String imageUrlHost = replaceImageUrlHost(url);
        ProgressInterceptor.addListener(imageUrlHost, progressListener);
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .fallback(resId)
                .error(resId)
                .fitCenter()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .load(imageUrlHost)
                .apply(options)
                .into(new DrawableImageViewTarget(imageView) {
                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if (progressListener != null) {
                            progressListener.onLoadStart();
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (progressListener != null) {
                            progressListener.onLoadFinish();
                        }
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        super.onLoadCleared(placeholder);
                        if (progressListener != null) {
                            progressListener.onLoadFinish();
                        }
                    }

                    @Override
                    public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        super.onResourceReady(resource, transition);
                        if (progressListener != null) {
                            progressListener.onLoadFinish();
                        }
                    }
                });
    }

    public static void displayLocalImage(String url, ImageView imageView, ImageSize imageSize) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        int resId = android.R.color.transparent;
        if (StringUtils.isEmpty(url)) {
            imageView.setImageResource(resId);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(resId)
                .fallback(resId)
                .error(resId)
//                .dontTransform()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .load(url)
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void loadOriginalImg(String url, ImageView imageView, Drawable placeholder) {
        if (imageView == null || imageView.getContext() == null) {
            return;
        }
        if (StringUtils.isEmpty(url)) {
            imageView.setImageDrawable(placeholder);
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)
                .error(placeholder)
                .fallback(placeholder)
//                .dontTransform()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .apply(options)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void clearMemory() {
        final Glide glide = Glide.get(Global.application);
        glide.clearMemory();
        ThreadExecutorManager.getInstance().runInThreadPool(() -> glide.clearDiskCache());
    }

    public static void clearView(View view) {
        Glide.with(Global.application).clear(view);
    }

    public static void getCacheImage(String path, final Handler handler, final onLoadingImageListener listener) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .download(replaceImageUrlHost(path))
                .apply(options)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, Transition<? super File> transition) {
                        Message msg = handler.obtainMessage(100, resource.getAbsolutePath());
                        handler.sendMessage(msg);
                        if (listener != null) {
                            listener.onLoadingComplete(null);
                        }
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (listener != null) {
                            listener.onLoadingFailed();
                        }
                    }
                });
    }

    public static void getImageFile(String path, final OnFileImageLoadingListener listener) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(Global.application)
                .download(replaceImageUrlHost(path))
                .apply(options)
                .into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, Transition<? super File> glideAnimation) {
                        if (listener != null) {
                            listener.onFileLoadingComplete(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        if (listener != null) {
                            listener.onLoadingFailed();
                        }
                    }
                });
    }

    public static void getCacheImageBitmap(final String path, final Handler handler, ImageSize imageSize, final onLoadingImageListener loadingImageListener) {
        RequestOptions options = new RequestOptions()
//                .dontTransform()
                .dontAnimate()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (imageSize != null) {
            options = options.override(imageSize.width, imageSize.height);
        }
        Glide.with(Global.application)
                .asBitmap()
                .load(replaceImageUrlHost(path))
                .apply(options)
//                .listener(new LoggingListener<>())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> glideAnimation) {
                        if (loadingImageListener != null) {
                            loadingImageListener.onLoadingComplete(resource);
                        }
                        if (handler != null) {
                            WeakHashMap<String, Bitmap> map = new WeakHashMap<>();
                            map.put(path, resource);
                            handler.sendMessage(handler.obtainMessage(0, map));
                        }
                    }

                    @Override
                    public void onLoadFailed(Drawable errorDrawable) {
                        if (loadingImageListener != null) {
                            loadingImageListener.onLoadingFailed();
                        }
                    }
                });


    }


    public static String replaceImageUrlHost(String url) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        //测试库url问题 这里简易处理一下
        //   http://172.17.10.7/download/http://172.17.10.7/download/group2/M00/00/54/rBEKSFzaHlOAL0EPAABizcsUT4E927.png
        if (!url.contains("http")) {
            url = Global.BASE_URL_DOWNLOAD + url;
        }
        return url;
    }

    public static boolean isLoadGIF(String path) {
        String ext = path.substring(path.lastIndexOf(".") + 1);
        return ext.equalsIgnoreCase("gif");
    }

    public static void resumeLoading() {
        Glide.with(Global.application).resumeRequests();
    }

    public static void pauseLoading() {
        Glide.with(Global.application).pauseRequests();
    }

    public interface onLoadingImageListener {
        void onLoadingComplete(Bitmap bitmap);

        void onLoadingFailed();
    }

    public interface OnFileImageLoadingListener extends onLoadingImageListener {
        void onFileLoadingComplete(File file);
    }

    public static class LoggingListener<R> implements RequestListener<R> {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<R> target, boolean isFirstResource) {
            if (BuildConfig.DEBUG) {
                Logger.d("onException", e);
            }
            return false;
        }

        @Override
        public boolean onResourceReady(R resource, Object model, Target<R> target, DataSource dataSource, boolean isFirstResource) {
            if (BuildConfig.DEBUG) {
                Logger.d("onResourceReady=" + model.toString());
            }
            return false;
        }
    }

    private static class BigImageProgressListenerTarget<T extends Drawable> extends ImageViewTarget<T> {
        private OnLoadProgressListener progressListener;

        public BigImageProgressListenerTarget(ImageView imageView, OnLoadProgressListener progressListener) {
            super(imageView);
            this.progressListener = progressListener;
        }

        @Override
        public void onResourceReady(T resource, @Nullable Transition<? super T> transition) {
            super.onResourceReady(resource, transition);
            if (progressListener != null) {
                progressListener.onLoadFinish();
            }
        }

        @Override
        public void onLoadStarted(@Nullable Drawable placeholder) {
            super.onLoadStarted(placeholder);
            if (progressListener != null) {
                progressListener.onLoadStart();
            }
        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {
            super.onLoadFailed(errorDrawable);
            if (progressListener != null) {
                progressListener.onLoadFinish();
            }
        }

        @Override
        public void onLoadCleared(@Nullable Drawable placeholder) {
            super.onLoadCleared(placeholder);
            if (progressListener != null) {
                progressListener.onLoadFinish();
            }
        }

        @Override
        protected void setResource(@Nullable T resource) {
            view.setImageDrawable(resource);
        }
    }


    public static class CustomListener<R> implements RequestListener<R> {

        ImageView mImageView;
        int mWidth;
        int mHeight;

        public CustomListener(ImageView imageView, int width, int height) {
            this.mImageView = imageView;
            this.mWidth = width;
            this.mHeight = height;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<R> target, boolean isFirstResource) {
            //加载失败时，显示的默认图片宽高一致
            ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
            layoutParams.width = DisplayUtils.dip2px(mWidth);
            layoutParams.height = DisplayUtils.dip2px(mHeight);
            mImageView.setLayoutParams(layoutParams);
            return false;
        }

        @Override
        public boolean onResourceReady(R resource, Object model, Target<R> target, DataSource dataSource, boolean isFirstResource) {
            return false;
        }
    }


}
