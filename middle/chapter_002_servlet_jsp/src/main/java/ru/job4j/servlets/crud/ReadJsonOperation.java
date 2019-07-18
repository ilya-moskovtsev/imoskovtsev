package ru.job4j.servlets.crud;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReadJsonOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();

        String json = req.getParameter("json");
        JsonPerson inPerson = mapper.readValue(json, JsonPerson.class);
        logicLayer.add(inPerson);
    }
}
