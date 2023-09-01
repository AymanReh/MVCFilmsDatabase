package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.FilmDAO;
import model.Film;



@WebServlet("/create")
public class create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//this get request sends the user to the insert films page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("create.html");
		dispatcher.include(request, response);
	}

	// this post request gets the title, year, director, stars and review through getParameters, it then sends this data to the database using the Film
	//f object, it sends the title, year, director, stars and review but it also sends the number 0, this does nothing since the database auto increments
	//the id for the film. I used a print writer to send a message to the user to tell them that a film has successfully been added to the database.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmDAO dao = new FilmDAO();
		String title = request.getParameter("title");
		int year = Integer.valueOf(request.getParameter("year"));
		String dir = request.getParameter("dir");
		String stars = request.getParameter("stars");
		String review = request.getParameter("review");
		Film f = new Film(0, title, year, dir, stars, review);
		PrintWriter out = response.getWriter();
		
		try {
			dao.insertFilm(f);
			out.write("Successfully inserted contact");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
	}				
	}