package com.laioffer.jupiter.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.Game;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");// return json type
        JSONObject game = new JSONObject();
        game.put("name", "World of Warcraft");
        game.put("developer", "Blizzard Entertainment");
        game.put("release_time", "Feb 11, 2005");
        game.put("website", "https://www.worldofwarcraft.com");
        game.put("price", 49.99);

        // Write game information to response body
        response.getWriter().print(game);// output


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Game.Builder builder = new Game.Builder();
        builder.setName("World of Warcraft");
        builder.setDeveloper("Blizzard Entertainment");
        builder.setReleaseTime("Feb 11, 2005");
        builder.setWebsite("https://www.worldofwarcraft.com");
        builder.setPrice(49.99);


        Game game = builder.build();
        response.getWriter().print(mapper.writeValueAsString(game));// transfer game object to json by Jackson.
    }
}
