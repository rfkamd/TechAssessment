package com.example.assessment.network;

import com.example.assessment.network.models.MediaMetadata;
import com.example.assessment.network.models.Medium;
import com.example.assessment.network.models.News;
import com.example.assessment.network.models.Result;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewsDeserializer implements JsonDeserializer<News> {
    @Override
    public News deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();

        System.out.println(jsonObject.get("status").getAsString());

        final JsonArray jsonResultsArray = jsonObject.get("results").getAsJsonArray();
        final List<Result> results = new ArrayList<>(jsonResultsArray.size());
        for (int i = 0; i < results.size(); i++) {
            final JsonElement jsonResult = jsonResultsArray.get(i);
            results.add(getResult(jsonResult));
        }

        final News news = new News();
        news.status = jsonObject.get("status").getAsString();
        news.copyright = jsonObject.get("copyright").getAsString();
        news.numResults = jsonObject.get("num_results").getAsInt();
        news.results = results;



        return news;
    }

    private Result getResult(JsonElement jsonResult){

        Result result = new Result();


        JsonObject obj = jsonResult.getAsJsonObject();

        System.out.println(obj.get("url").getAsString());

        result.url = obj.get("url").getAsString();
        result.adxKeywords = obj.get("adx_keywords").getAsString();
        result.column = obj.get("column").getAsString();
        result.section = obj.get("section").getAsString();
        result.byline = obj.get("byline").getAsString();
        result.type = obj.get("type").getAsString();
        result.title = obj.get("title").getAsString();
        result._abstract = obj.get("abstract").getAsString();
        result.publishedDate = obj.get("published_date").getAsString();
        result.source = obj.get("source").getAsString();
        result.id = obj.get("id").getAsLong();
        result.assetId = obj.get("asset_id").getAsLong();
        result.views = obj.get("views").getAsLong();



        // String arrays
        result.desFacet = getStringArray(obj.get("des_facet").getAsJsonArray());
        result.orgFacet = getStringArray(obj.get("org_facet").getAsJsonArray());
        result.perFacet = getStringArray(obj.get("per_facet").getAsJsonArray());
        result.geoFacet = getStringArray(obj.get("geo_facet").getAsJsonArray());

        // media
        final JsonArray jsonMediaArray = obj.get("media").getAsJsonArray();
        final List<Medium> mediaList = new ArrayList<>(jsonMediaArray.size());
        for (int i = 0; i < mediaList.size(); i++) {
            final JsonElement mediaObj = jsonMediaArray.get(i);
            mediaList.add(getMedia(jsonResult));
        }

        result.media = mediaList;

        return result;
    }

    private Medium getMedia(JsonElement jsonResult){

        Medium media =  new Medium();

        JsonObject obj = jsonResult.getAsJsonObject();
        media.type = obj.get("type").getAsString();
        media.subtype = obj.get("subtype").getAsString();
        media.caption = obj.get("caption").getAsString();
        media.copyright = obj.get("copyright").getAsString();
        media.approvedForSyndication = obj.get("approved_for_syndication").getAsInt();

        // media arrays
        final JsonArray jsonMediaArray = obj.get("media-metadata").getAsJsonArray();
        final List<MediaMetadata> mediaList = new ArrayList<>(jsonMediaArray.size());
        for (int i = 0; i < mediaList.size(); i++) {
            final JsonElement mediaObj = jsonMediaArray.get(i);
            mediaList.add(getMediaMetadata(jsonResult));
        }
        media.mediaMetadata = mediaList;

        return media;
    }

    private MediaMetadata getMediaMetadata(JsonElement jsonResult){

        MediaMetadata media =  new MediaMetadata();

        JsonObject obj = jsonResult.getAsJsonObject();
        media.url = obj.get("url").getAsString();
        media.format = obj.get("format").getAsString();
        media.height = obj.get("height").getAsInt();
        media.width = obj.get("width").getAsInt();


        return media;
    }

    private List<String> getStringArray(JsonArray jsonStringArray){
        List<String> strings = new ArrayList<>(jsonStringArray.size());
        for (int i = 0; i < jsonStringArray.size(); i++) {
            final JsonElement element = jsonStringArray.get(i);
            strings.add(element.getAsString());
        }
        return strings;
    }

}
