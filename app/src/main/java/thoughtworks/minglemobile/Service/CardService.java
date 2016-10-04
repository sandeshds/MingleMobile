package thoughtworks.minglemobile.Service;


import android.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.XML;

import java.io.IOException;

import thoughtworks.minglemobile.Utils.Constants;

public class CardService {
    public JSONArray getAllCards(String projectId) throws IOException {
        String cardsUrl = Constants.getAllCardsURL.replace("{}",projectId);

        HttpUriRequest request = new HttpGet(cardsUrl);
        String credentials = Constants.username + ":" + Constants.password; // hard coding this for now
        String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        request.addHeader("Authorization", "Basic " + base64EncodedCredentials);

        HttpClient httpclient = new DefaultHttpClient();
        httpclient.execute(request);

        HttpResponse httpResponse = httpclient.execute(request);
        HttpEntity entity = httpResponse.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");

        try {
            JSONArray cards = (XML.toJSONObject(responseString)).getJSONObject("cards")
                    .getJSONArray("card");
            return cards;
        } catch (JSONException je) {
            System.out.println(je.toString());
            return null;
        }
    }
}
