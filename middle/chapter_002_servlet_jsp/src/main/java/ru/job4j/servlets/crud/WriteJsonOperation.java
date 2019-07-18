package ru.job4j.servlets.crud;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class WriteJsonOperation implements Operation {
    @Override
    public void apply(Validate logicLayer, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonPerson> people = logicLayer.getPeople();
        try (OutputStream out = resp.getOutputStream()) {
            mapper.writeValue(out, people);
        }
    }
}
