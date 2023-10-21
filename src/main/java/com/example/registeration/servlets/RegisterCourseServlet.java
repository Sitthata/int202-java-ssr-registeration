package com.example.registeration.servlets;

import com.example.registeration.models.CourseRegistered;
import com.example.registeration.models.CourseRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegisterCourseServlet", value = "/register")
public class RegisterCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        int semester = Integer.parseInt(parameterMap.get("semester")[0]);

        HttpSession session = request.getSession();
        CourseRegistered courseRegistered = (CourseRegistered) session.getAttribute("courseRegistered");
        if (courseRegistered == null) {
            courseRegistered = new CourseRegistered();
            session.setAttribute("courseRegistered", courseRegistered);
        } else {
            courseRegistered.removeAllRegisteredCourse(semester);
        }
        for (String subjectId : parameterMap.get("registeredSubject")) {
            courseRegistered.registerSubject(semester, CourseRepository.getSubject(semester, subjectId));
        }
        getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
    }
}