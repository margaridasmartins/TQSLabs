package ex2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

public class findAddressForLocationTest {

    @Test
    public void whenGoodAddress() throws ParseException, IOException, URISyntaxException {
        TqsHttpClient mockedhttpClient = mock(TqsHttpBasic.class);

        String resultjson="{\"info\":{\"statuscode\":0,\"copyright\": {\"text\":\"\\u00A9 2021 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2021 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-8.658}},\"locations\":[{\"street\":\"Parque Estacionamento da Reitoria - Univerisdade de Aveiro\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3810-193\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"displayLatLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=uXSAVwYWbf9tJmsjEGHKKAo0gOjZfBLQ&type=map&size=225,160&locations=40.6318025,-8.657881|marker-sm-50318A-1&scalebar=true&zoom=15&rand=-98728279\",\"roadMetadata\":null}]}]}";

        AddressResolver resolver = new AddressResolver(mockedhttpClient);


        when(mockedhttpClient.get(contains("location=40.631800%2C-8.658000"))).thenReturn(resultjson);

        Address result = resolver.findAddressForLocation(40.6318, -8.658);

        assertEquals( result, new Address( "Parque Estacionamento da Reitoria - Univerisdade de Aveiro", "Glória e Vera Cruz", "Centro", "3810-193", null) );

    }

    @Test
    public void whenBadCoordinate() throws IOException, URISyntaxException, ParseException {
        TqsHttpClient mockedhttpClient = mock(TqsHttpBasic.class);
        
        String badresultjson="{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\\u00A9 2021 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\\u00A9 2021 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-200.0}},\"locations\":[]}]}";
              
        AddressResolver resolver = new AddressResolver(mockedhttpClient);


        when(mockedhttpClient.get(contains("location=40.631800%2C-200.000"))).thenReturn(badresultjson);

        assertThrows( IndexOutOfBoundsException.class, ()->{resolver.findAddressForLocation(40.6318, -200.0);});
    }

}