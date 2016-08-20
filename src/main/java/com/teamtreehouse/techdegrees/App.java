package com.teamtreehouse.techdegrees;


import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/api/v1", (req, res) -> "Hello!");

        post("/api/v1", (req, res) -> "Hello!");

        delete("/api/v1", (req, res) -> "Hello!");

    }

}
