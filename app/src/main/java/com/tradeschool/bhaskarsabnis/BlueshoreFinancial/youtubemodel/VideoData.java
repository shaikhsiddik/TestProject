package com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubemodel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class VideoData {
    @SerializedName("VIDEO_ID")
    @Expose
    private String vIDEOID;
    @SerializedName("VIDEO_TITLE")
    @Expose
    private String vIDEOTITLE;
    @SerializedName("VIDEO_URL")
    @Expose
    private String vIDEOURL;
    @SerializedName("VIDEO_PARAM")
    @Expose
    private String vIDEOPARAM;
    @SerializedName("VIDEO_Timestamp")
    @Expose
    private String vIDEOTimestamp;

    public String getVIDEOID() {
        return vIDEOID;
    }

    public void setVIDEOID(String vIDEOID) {
        this.vIDEOID = vIDEOID;
    }

    public String getVIDEOTITLE() {
        return vIDEOTITLE;
    }

    public void setVIDEOTITLE(String vIDEOTITLE) {
        this.vIDEOTITLE = vIDEOTITLE;
    }

    public String getVIDEOURL() {
        return vIDEOURL;
    }

    public void setVIDEOURL(String vIDEOURL) {
        this.vIDEOURL = vIDEOURL;
    }

    public String getVIDEOPARAM() {
        return vIDEOPARAM;
    }

    public void setVIDEOPARAM(String vIDEOPARAM) {
        this.vIDEOPARAM = vIDEOPARAM;
    }

    public String getVIDEOTimestamp() {
        return vIDEOTimestamp;
    }

    public void setVIDEOTimestamp(String vIDEOTimestamp) {
        this.vIDEOTimestamp = vIDEOTimestamp;
    }
}
