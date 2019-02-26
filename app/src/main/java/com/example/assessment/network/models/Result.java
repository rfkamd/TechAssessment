
package com.example.assessment.network.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("adx_keywords")
    @Expose
    public String adxKeywords;
    @SerializedName("column")
    @Expose
    public String column;
    @SerializedName("section")
    @Expose
    public String section;
    @SerializedName("byline")
    @Expose
    public String byline;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("abstract")
    @Expose
    public String _abstract;
    @SerializedName("published_date")
    @Expose
    public String publishedDate;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("asset_id")
    @Expose
    public int assetId;
    @SerializedName("views")
    @Expose
    public int views;
    @SerializedName("des_facet")
    @Expose
    public List<String> desFacet = null;
    @SerializedName("org_facet")
    @Expose
    public String orgFacet;
    @SerializedName("per_facet")
    @Expose
    public String perFacet;
    @SerializedName("geo_facet")
    @Expose
    public String geoFacet;
    @SerializedName("media")
    @Expose
    public List<Medium> media = null;

}
