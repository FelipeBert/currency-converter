import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class CurrencyQuery {
    private static final Logger logger = LogManager.getLogger();
    private static String key = "00cb7b8a49aec6d0a4daed86";
    private static String partOfUrl = "https://v6.exchangerate-api.com/v6/" + key + "/latest/";

    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setPrettyPrinting()
            .create();

    public double conversionCalculation(String typeToConvert, String typeConverted, double value)
            throws IOException, InterruptedException {
        logger.info("Starting conversion from {} to {}", typeToConvert, typeConverted);
        if(typeConverted == null || typeToConvert == null){
            logger.warn("Invalid Prameter");
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if(value <= 0){
            logger.warn("Invalid Value");
            throw new IllegalArgumentException("Value must be a positive number");
        }

        String url = partOfUrl + typeToConvert;

        String json = sendRequest(url);
        ConversionData conversionData = gson.fromJson(json, ConversionData.class);

        Map<String, Double> conversions = conversionData.conversion_rates();

        Double conversionValue = conversions.get(typeConverted);
        if(conversionValue == null){
            logger.error("Error, Conversion not Found");
            throw new NullPointerException("Conversion rate not Found!");
        }

        logger.info("Conversion successful: {} {} to {} equals {}", value, typeToConvert, typeConverted, value * conversionValue);
        return value * conversionValue;
    }

    private String sendRequest(String url) throws IOException, InterruptedException {
        logger.debug("Sending request to URL: {}", url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        }catch (IOException | InterruptedException e){
            logger.error("Error while sending request to URL: {}", url, e);
            throw e;
        }
    }
}
