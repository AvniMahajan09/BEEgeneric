package com.example.avni.beegeneric.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Photos implements Parcelable {
    private int id;
    private String name;
    private String imgurl;

 public Photos(){

 }
    public Photos(int id, String name, String imgurl) {
        this.id = id;
        this.name = name;
        this.imgurl = imgurl;
    }


    protected Photos(Parcel in) {
        id = in.readInt();
        name = in.readString();
        imgurl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(imgurl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
