package com.example.avni.beegeneric.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Docs implements Parcelable {
    private int id;
    private String name;
    private String url;

    public Docs() {
    }

    public Docs(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    protected Docs(Parcel in) {
        id = in.readInt();
        name = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Docs> CREATOR = new Creator<Docs>() {
        @Override
        public Docs createFromParcel(Parcel in) {
            return new Docs(in);
        }

        @Override
        public Docs[] newArray(int size) {
            return new Docs[size];
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
