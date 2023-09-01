package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.FilmDAO;
import model.Film;


@WebServlet("/controller")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// This piece of code is a get request and it immediately runs when the user clicks the view our movies button on the index page,
	//this piece of code loads all of the movies
	// through by first retrieving the database in the dao object. And the arraylist object on line 26, this retrieves all the films.
	// next a request dispatcher is used to load the website to the user. The dao object and allFilm object are called in the JSP using a GET Request
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilm = dao.getAllFilms();
		request.setAttribute("films", allFilm);
		RequestDispatcher rd = request.getRequestDispatcher("films.jsp");
		rd.include(request, response);
		
	}
// This is a post request and only runs when a form in the jsp is ran, this code is used to search for a film in the repository, it get the database object
	//and the format parameter using the request.getparameter function, next it sees weather the format is equal to either id, title or year and if it is
	// equal to any of those it runs if statements inside of them. Each if statement puts the parameter in either a string or a int depending on what the
	// format is and sends the data through the dao object. next it updates the page using a request dispatcher to search for a film.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FilmDAO dao = new FilmDAO();
		String format = request.getParameter("format");
		String data = request.getParameter("data");
		if(format.equals("id")) {
			int id = Integer.valueOf(data);
			ArrayList<Film> specFilm =  dao.getFilmByID(id);
			request.setAttribute("films", specFilm);
			RequestDispatcher rd = request.getRequestDispatcher("films.jsp");
			rd.include(request, response);
			}
		if(format.equals("title")) {
			String title = data;
			ArrayList<Film> titleFilm =  dao.getFilmByTitle(title);
			request.setAttribute("films", titleFilm);
			RequestDispatcher rd = request.getRequestDispatcher("films.jsp");
			rd.include(request, response);
			}
		if(format.equals("year")) {
			int year = Integer.valueOf(data);
			ArrayList<Film> yearFilm =  dao.getFilmByYear(year);
			request.setAttribute("films", yearFilm);
			RequestDispatcher rd = request.getRequestDispatcher("films.jsp");
			rd.include(request, response);
			}
		}
	}
