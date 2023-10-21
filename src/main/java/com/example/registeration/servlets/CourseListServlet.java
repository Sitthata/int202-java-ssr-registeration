package com.example.registeration.servlets;

import com.example.registeration.models.CourseRepository;
import com.example.registeration.models.Semester;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "courseListServlet", value = "/course-list")
public class CourseListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("semesters", Semester.getAllSemesterText());
        getServletContext().getRequestDispatcher("/course_list.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String semesterStr = request.getParameter("semester");
        request.setCharacterEncoding("utf-8");
        if (semesterStr.isBlank() || semesterStr.isEmpty()) {
            doGet(request, response);
        }
        int semester = Integer.parseInt(semesterStr);
        request.setAttribute("semesters", Semester.getAllSemesterText());
        request.setAttribute("selectedSemester", semester);
        request.setAttribute("subjects", CourseRepository.getSubjects(semester));
        getServletContext().getRequestDispatcher("/course_list.jsp").forward(request, response);
    }
}
