package Task1pack;

import Task1pack.UserForTask1;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
    public static UserForTask1 toCreateANewUser(UserForTask1 user) throws IOException, InterruptedException {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(user);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), UserForTask1.class);
    }
    public static UserForTask1 userUpdating(UserForTask1 user) throws IOException, InterruptedException {
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(user);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/2"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), UserForTask1.class);
    }
    public static int userRemoving() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/2"))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
    public static void getAllOfTheUsers() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse <String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
    public static void getUserWithInputtedId() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите id пользователя: ");
        int id = scanner.nextInt();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .header("Content-Type", "application/json/5")
                .GET()
                .build();
        HttpResponse <String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        scanner.close();
    }
    public static void getUserWithInputtedUsername() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите никнейм пользователя: ");
        String username = scanner.nextLine();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users?username=" + username))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String>response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        scanner.close();

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        UserForTask1 user = new UserForTask1( 0,"ejik", "ejik228", "qwasolya@gmail.com","Admon", "didnt find", +228322, "www.leningrad.spb.ru","tavtech sucks" );
        UserForTask1 user1 = new UserForTask1(2,"Alenka", "barboska", "qwasolya111@gmail.com","Admon 6/2", "lost in the space", +111222, "www.leningrad.atb.ru","nordson" );
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println((gson.toJson(toCreateANewUser(user))));
        System.out.println(gson.toJson(userUpdating(user1)));
        System.out.println(userRemoving());

        getAllOfTheUsers();
        getUserWithInputtedId();
        getUserWithInputtedUsername();
    }
}
