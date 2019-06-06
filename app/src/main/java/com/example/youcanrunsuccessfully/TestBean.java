package com.example.youcanrunsuccessfully;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

@Entity
public class TestBean {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String url;

    private String text;

    public TestBean() {
    }

    @Ignore
    public TestBean(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @BindingAdapter({"loadImage"})
    public static void loadImage(final ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
