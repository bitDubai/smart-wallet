package com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.exception.CantGetBufferedReader;
import com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.exception.CantGetInputStream;
import com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.exception.CantGetJsonObject;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.DealsWithErrors;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;



public class HTTPJson implements DealsWithErrors {
    private BufferedReader bufferedReader;
    private InputStream inputStream;
    private JSONObject jsonObject;
    private String json = "";

    public HTTPJson() {

    }

    ErrorManager errorManager;

    public JSONObject getJSONFromUrl(String url) {
        bufferedReader = null;
        inputStream = null;
        jsonObject = null;
        try {
            inputStream= getInputStream(url);
        } catch (CantGetInputStream cantGetInputStream) {
            cantGetInputStream.printStackTrace();
        }
        try {
            bufferedReader = getBufferedReader(inputStream);
        } catch (CantGetBufferedReader cantGetBufferedReader) {
            cantGetBufferedReader.printStackTrace();
        }
        try {
            jsonObject=getJsonObject(bufferedReader);
        } catch (CantGetJsonObject cantGetJsonObject) {
            cantGetJsonObject.printStackTrace();
        }
        return jsonObject;
    }

    public InputStream getInputStream(String url) throws CantGetInputStream{
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
            throw new  CantGetInputStream(CantGetInputStream.DEFAULT_MESSAGE,e,"Probably the url is invalid",null);
        }

        return inputStream;
    }
    public BufferedReader getBufferedReader(InputStream is) throws CantGetBufferedReader{
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new CantGetBufferedReader(CantGetBufferedReader.DEFAULT_MESSAGE,e,"Probably the InputStream parameter is not correct",null);
        }

        return bufferedReader;
    }
    public JSONObject getJsonObject(BufferedReader reader) throws CantGetJsonObject{
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
               stringBuilder.append(line + "\n");
                }
            inputStream.close();
            json = stringBuilder.toString();
            jsonObject = new JSONObject(json);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CantGetJsonObject(CantGetJsonObject.DEFAULT_MESSAGE,e,"Probably the Json object obtained not correct or is not within the expected format",null);
        }


        return jsonObject;
    }

    @Override
    public void setErrorManager(ErrorManager errorManager) {this.errorManager = errorManager; }
}