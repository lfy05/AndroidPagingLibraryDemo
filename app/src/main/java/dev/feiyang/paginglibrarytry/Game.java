package dev.feiyang.paginglibrarytry;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Game {
    @ColumnInfo
    @SerializedName("genre")
    public String genre;

    @ColumnInfo
    @SerializedName("imgURL")
    public String imgURL;

    @ColumnInfo
    @SerializedName("subgenre")
    public String subgenre;

    @ColumnInfo
    @SerializedName("title")
    public String title;

    @PrimaryKey
    @NonNull
    @SerializedName("pid")
    public String  pid;

    @ColumnInfo
    @SerializedName("rating")
    public double rating;

    @ColumnInfo
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

    /**
     * Generates a hashmap representing the game object
     * @return HashMap<String, String> representing the object
     */
    public HashMap<String, String> getHashMap(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("genre", this.genre);
        hashMap.put("imgURL", this.imgURL);
        hashMap.put("subgenre", this.subgenre);
        hashMap.put("title", this.title);
        hashMap.put("pid", this.pid);
        hashMap.put("rating", Double.toString(this.rating));
        hashMap.put("rCount", Long.toString(this.rCount));

        return hashMap;
    }

    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("genre", this.genre);
        jsonObject.put("imgURL", this.imgURL);
        jsonObject.put("pid", this.pid);
        jsonObject.put("rating", this.rating);
        jsonObject.put("rCount", this.rCount);
        jsonObject.put("subgenre", this.subgenre);
        jsonObject.put("title", this.title);

        return jsonObject;
    }
}
