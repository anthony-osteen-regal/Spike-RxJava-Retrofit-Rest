package com.regal.spike.RestApi.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowTimeResponse {
    @SerializedName("TheatreCode")
    private String theaterCode;

    @SerializedName("AdvertiseShowDate")
    private String advertiseShowDate;

    @SerializedName("Performances")
    private List<PerformanceResponse> performancesList;

    public List<PerformanceResponse> getPerformancesList() {
        return performancesList;
    }

    public void setPerformancesList(List<PerformanceResponse> performancesList) {
        this.performancesList = performancesList;
    }

    public String getTheaterCode() {
        return theaterCode;
    }

    public void setTheaterCode(String theaterCode) {
        this.theaterCode = theaterCode;
    }

    public String getAdvertiseShowDate() {
        return advertiseShowDate;
    }

    public void setAdvertiseShowDate(String advertiseShowDate) {
        this.advertiseShowDate = advertiseShowDate;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("TheaterCode:").append(theaterCode);
        sb.append("\n");
        sb.append("AdvertiserShowDate:").append(advertiseShowDate);
        sb.append("\n");

        for( PerformanceResponse e : performancesList ){
            sb.append(e.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    //Child classes
    public class PerformanceResponse {

        @SerializedName("Title")
        private String title;

        @SerializedName("CalendarShowTime")
        private String showTime;

        public String getTitle() {
            return title;
        }

//        @SerializedName("ImageUrl")
//        private String imageUrl;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("Title:").append(title);
            sb.append("\n");
            sb.append("CalendarShowTime:").append(showTime);
            sb.append("\n");
            return sb.toString();
        }
    }
}
