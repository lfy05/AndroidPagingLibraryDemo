package dev.feiyang.paginglibrarytry;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Game {

    @SerializedName("genre")
    public String genre;

    @SerializedName("imgURL")
    public String imgURL;

    @SerializedName("subgenre")
    public String subgenre;

    @SerializedName("title")
    public String title;

    @NonNull
    @SerializedName("pid")
    public String  pid;

    @SerializedName("rating")
    public double rating;

    @SerializedName("rCount")
    public long rCount;

    public Game(String genre, String imgURL, String subgenre, String title, String pid, double rating, long rCount) {
        this.genre = genre;
        this.imgURL = imgURL;
        this.subgenre = subgenre;
        this.title = title;
        this.pid = pid;
        this.rating = rating;
        this.rCount = rCount;
    }

    /**
     * Create Game object from JSON object
     * @param jsonObject
     */
    public Game(JSONObject jsonObject) throws JSONException {
        this.genre = jsonObject.getString("genre");
        this.imgURL = jsonObject.getString("imgURL");
        this.subgenre =jsonObject.getString("subgenre");
        this.title = jsonObject.getString("title");
        this.pid = jsonObject.getString("pid");
        this.rating = jsonObject.getDouble("rating");
        this.rCount = jsonObject.getLong("rCount");
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getrCount() {
        return rCount;
    }

    public void setrCount(long rCount) {
        this.rCount = rCount;
    }

}
