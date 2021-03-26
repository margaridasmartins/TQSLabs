package ex2;
import java.io.IOException;

public interface TqsHttpClient {

    public String get(String url)throws IOException;
}