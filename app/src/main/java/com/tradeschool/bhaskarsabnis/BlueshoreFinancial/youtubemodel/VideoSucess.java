package com.tradeschool.bhaskarsabnis.BlueshoreFinancial.youtubemodel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class VideoSucess {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("data")
    @Expose
    private List<VideoData> data = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public List<VideoData> getData() {
        return data;
    }

    public void setData(List<VideoData> data) {
        this.data = data;
    }
}
