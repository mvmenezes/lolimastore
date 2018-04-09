package br.com.storeadmin.WS;

import br.com.storeadmin.model.Store.Address;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SearchCEPWS {


    public Address searchAddress(String CEP) {

        String url = "http://viacep.com.br/ws/" + CEP + "/json/";
        Address newAddress = null;

        // define os dados
        JSONObject obj = new JSONObject(getHttpGET(url));

        if (!obj.has("erro")) {
            newAddress = new Address();
            newAddress.setZipcode(obj.getString("cep"));
            newAddress.setStreet(obj.getString("logradouro"));
            newAddress.setAdditional(obj.getString("complemento"));
            newAddress.setDistrict(obj.getString("bairro"));
            newAddress.setCity(obj.getString("localidade"));
            newAddress.setState(obj.getString("uf"));

        }
        return newAddress;
    }

    public final String getHttpGET(String urlToRead) {
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (MalformedURLException | ProtocolException ex) {


        } catch (IOException ex) {

        }

        return result.toString();
    }


}
