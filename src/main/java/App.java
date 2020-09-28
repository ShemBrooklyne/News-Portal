import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;

import dao.Sql2oUserDao;
import exceptions.ApiExceptions;
import model.Department;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oDepartmentDao departmentDao;
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/newsportal.db;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();


        get("/departments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            System.out.println(departmentDao.getAll());
            return gson.toJson(departmentDao.getAll());//send it back to be displayed
        });

//        get("/restaurants/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
//            res.type("application/json");
//            int restaurantId = Integer.parseInt(req.params("id"));
//
//            if (restaurantDao.findById(restaurantId) == null){
//                throw new ApiExceptions(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
//            }
//            return gson.toJson(restaurantDao.findById(restaurantId));
//        });
//
//        get("/restaurants", "application/json", (req, res) -> {
//            System.out.println(restaurantDao.getAll());
//
//            if(restaurantDao.getAll().size() > 0){
//                return gson.toJson(restaurantDao.getAll());
//            }
//
//            else {
//                return "{\"message\":\"I'm sorry, but no restaurants are currently listed in the database.\"}";
//            }
//
//        });

//        get("foodtypes/:id", "application/json", (request, response) -> {
//            int target = Integer.parseInt(request.params("id"));
//            Foodtype foodtype =  foodtypeDao.findFoodById(target);
//            if(foodtype != null){
//                return gson.toJson(foodtype);
//            }else{
//                throw new Error("There currently no restaurants with the aid ID");
//            }
//        });
//
//
//
//        get("/restaurants/:id/foodtypes", "application/json", (req, res) -> {
//            int restaurantId = Integer.parseInt(req.params("id"));
//            Restaurant restaurantToFind = restaurantDao.findById(restaurantId);
//            if (restaurantToFind == null){
//                throw new Error(String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
//            }
//            else if (restaurantDao.getAllFoodtypesByRestaurant(restaurantId).size()==0){
//                return "{\"message\":\"I'm sorry, but no foodtypes are listed for this restaurant.\"}";
//            }
//            else {
//                return gson.toJson(restaurantDao.getAllFoodtypesByRestaurant(restaurantId));
//            }
//        });
//
//
//
//        get("/foodtypes/:id/restaurants", "application/json", (req, res) -> {
//            int foodtypeId = Integer.parseInt(req.params("id"));
//            Foodtype foodtypeToFind = foodtypeDao.findFoodById(foodtypeId);
//            if (foodtypeToFind == null){
//                throw new Error(String.format("No foodtype with the id: \"%s\" exists", req.params("id")));
//            }
//            else if (foodtypeDao.getAllRestaurantsForAFoodtype(foodtypeId).size()==0){
//                return "{\"message\":\"I'm sorry, but no restaurants are listed for this foodtype.\"}";
//            }
//            else {
//                return gson.toJson(foodtypeDao.getAllRestaurantsForAFoodtype(foodtypeId));
//            }
//        });
//
//        get("/restaurants/:id/sortedReviews", "application/json", (req, res) -> { //// TODO: 1/18/18 generalize this route so that it can be used to return either sorted reviews or unsorted ones.
//            int restaurantId = Integer.parseInt(req.params("id"));
//            Restaurant restaurantToFind = restaurantDao.findById(restaurantId);
//            List<Review> allReviews;
//            if (restaurantToFind == null){
////                throw new ApiException(404, String.format("No restaurant with the id: \"%s\" exists", req.params("id")));
//            }
//            allReviews = reviewDao.getAllReviewsByRestaurantSortedNewestToOldest(restaurantId);
//            return gson.toJson(allReviews);
//        });


        //POST REQUESTS

        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });




//        post("/restaurants/:restaurantId/reviews/new", "application/json", (req, res) -> {
//            int restaurantId = Integer.parseInt(req.params("restaurantId"));
//            Review review = gson.fromJson(req.body(), Review.class);
//            review.setCreatedat(); //I am new!
//            review.setFormattedCreatedAt();
//            review.setRestaurantId(restaurantId);
//            reviewDao.add(review);
//            res.status(201);
//            return gson.toJson(review);
//        });
//
//        post("/foodtypes/new", "application/json", (req, res) -> {
//            Foodtype foodtype = gson.fromJson(req.body(), Foodtype.class);
//            foodtypeDao.add(foodtype);
//            res.status(201);
//            return gson.toJson(foodtype);
//        });
//
//
//
//        post("/restaurant/:id/update", "application/json", (request, response) -> {
//            int restaurantId = Integer.parseInt(request.params("id"));
//            Restaurant target = restaurantDao.findById(restaurantId);
//            Restaurant update = gson.fromJson(request.body(), Restaurant.class);
//            return null;
//        });
//
//        post("/restaurants/:restaurantId/foodtype/:foodtypeId", "application/json", (req, res) -> {
//            int restaurantId = Integer.parseInt(req.params("restaurantId"));
//            int foodtypeId = Integer.parseInt(req.params("foodtypeId"));
//            Restaurant restaurant = restaurantDao.findById(restaurantId);
//            Foodtype foodtype = foodtypeDao.findFoodById(foodtypeId);
//
//            if (restaurant != null && foodtype != null){
//                //both exist and can be associated - we should probably not connect things that are not here.
//                foodtypeDao.addFoodtypeToRestaurant(foodtype, restaurant);
//                res.status(201);
//                return gson.toJson(String.format("Restaurant '%s' serves Foodtype '%s' which is a re-known Italian dish",restaurant.getName(), foodtype.getName()));
//            }
//            else {
//                throw new Error("Restaurant or Foodtype does not exist");
//            }
//        });

        //FILTERS
        exception(ApiExceptions.class, (exc, req, res) -> {
            ApiExceptions err = exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
}