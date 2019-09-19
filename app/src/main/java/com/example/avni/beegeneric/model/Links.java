package com.example.avni.beegeneric.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Links implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String linkurl;

    public Links(){

    }
    public Links(int id, String title, String description, String linkurl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.linkurl = linkurl;
    }

    protected Links(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        linkurl = in.readString();
    }

    public static final Creator<Links> CREATOR = new Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(linkurl);
    }
}
