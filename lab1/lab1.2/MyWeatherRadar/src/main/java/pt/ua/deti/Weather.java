package pt.ua.deti;

/**
 * Hello world!
 *
 */

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import pt.ua.deti.IpmaCityForecast; //may need to adapt package name
import pt.ua.deti.IpmaService;

public class Weather
{
    //private static final int CITY_ID_AVEIRO = 1010500;

    private static int code = 1010500;

    public static void main( String[] args ) {

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        //Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID_AVEIRO);

        //to receive the city code as a parameter in the command line and print the forecast information

        if(args.length > 0){

            code = Integer.parseInt(args[0]);
        }

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                CityForecast firstDay = forecast.getData().listIterator().next();

                System.out.println("Latitude: " + forecast.getData().listIterator().next().getLatitude()+ "\nLongitude" + forecast.getData().listIterator().next().getLongitude()
                        + "\nmax temp for today: " + forecast.getData().listIterator().next().getTMax() + "Cº ," + "\nprobabilidade de precipitação: " +forecast.getData().listIterator().next().getPrecipitaProb() +"\n" + code);

                System.out.printf( "max temp for %s is %4.1f %n",
                        firstDay.getForecastDate(),
                        Double.parseDouble(firstDay.getTMax()));
            } else {
                System.out.println( "No results for this request!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}
