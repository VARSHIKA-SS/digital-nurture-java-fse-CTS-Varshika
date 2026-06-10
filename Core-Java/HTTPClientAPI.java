import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HTTPClientAPI {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.github.com/users/torvalds"))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(10))
                .build();
        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status: " + response.statusCode());
            System.out.println("\nResponse:");
            System.out.println(response.body());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
