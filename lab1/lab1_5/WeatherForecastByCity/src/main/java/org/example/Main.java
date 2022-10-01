package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.example.IpmaCityForecast;
import org.example.IpmaService;
import org.example.CityForecast;

import java.util.HashMap;
import java.util.Map;


public class Main {

    //todo: should generalize for a city passed as argument
    //private static final int CITY_ID_AVEIRO = 1010500;
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final Map<String, Integer> places = new HashMap<>();
    private String city_name;


    public static void main(String[] args) {

        String city_name; //codigo da cidade
        Map<String, Integer> places = new HashMap<>();


        //weather forecast for a given city, receiving thecity name from the command line

        if(args.length > 0){

            String city_name = args[0];

        } else{
            System.out.print("O utilizador nao introduziu nome da cidade ");
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(city_name);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            for(CityForecast cidade : forecast.getData()) {
                String city = cidade.getCountry();
                int id = city.getGlobalIdLocal();
                places.put(city, id);
            }

            int id = 0;

            for(Map.Entry<String, Integer> entry : places.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(city_name)) {
                    id = entry.getValue();
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                    break;

                }
            }

                if (id != 0) {

                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://api.ipma.pt/open-data/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    service = retrofit.create(IpmaService.class);
                    callSync = service.getForecastForACity(id);

                    try {
                        apiResponse = callSync.execute();
                        forecast = apiResponse.body();

                        if (forecast != null) {

                            System.out.println("max tem for today is:" + forecast.getData().listIterator().next().getTMax() + "\nmin tem for today is:" + forecast.getData().listIterator().next().getTMin() + "\nlatitude: " + forecast.getData().listIterator().next().getLatitude() + "\nlongitude" + forecast.getData().listIterator().next().getLongitude() + "\n" + city_name);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("No results for this request!");
                }


                //System.out.println("Hello world!");
            }catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}