package com.bumptech.glide.samples.svg;

import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.SVG;

/**
 * Convert the {@link SVG}'s internal representation to an Android-compatible one ({@link Picture}).
 */
public class SvgDrawableTranscoder implements ResourceTranscoder<SVG, PictureDrawable> {
  @Nullable
  @Override
  public Resource<PictureDrawable> transcode(
      @NonNull Resource<SVG> toTranscode, @NonNull Options options) {
    SVG svg = toTranscode.get();
    int width = (int) svg.getDocumentWidth();
    int height = (int) svg.getDocumentHeight();
    Picture picture;
    if (width < 0 || height < 0) {
      picture = svg.renderToPicture();
    } else {
      picture = svg.renderToPicture(width, height);
    }
    PictureDrawable drawable = new PictureDrawable(picture);
    return new SimpleResource<>(drawable);
  }
}