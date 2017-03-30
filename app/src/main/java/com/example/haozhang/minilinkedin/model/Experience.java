package com.example.haozhang.minilinkedin.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by haozhang on 3/17/17.
 */

public class Experience implements Parcelable {

    public String id;
    public String title;
    public String company;
    public Date startDate;
    public Date endDate;
    public List<String> items;

    public Experience() {
        id = UUID.randomUUID().toString();
    }

    protected Experience(Parcel in) {
        id = in.readString();
        company = in.readString();
        title = in.readString();
        startDate = new Date(in.readLong());
        endDate = new Date(in.readLong());
        items = in.createStringArrayList();
    }

    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        @Override
        public Experience createFromParcel(Parcel in) {
            return new Experience(in);
        }

        @Override
        public Experience[] newArray(int size) {
            return new Experience[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(company);
        parcel.writeString(title);
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(endDate.getTime());
        parcel.writeStringList(items);
    }
}
