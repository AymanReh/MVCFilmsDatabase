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


@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//This post request only gets the user id and sends it to the filmsDAO method deleteFilm to immediately delete a film, no parameters are needed since the
	//jsp automatically sends the id of the film and it automatically sends the user to a page which says Deleted film when the film is sent to the 
	//method inside of FilmDAO called deleteFilm.
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		Film f = new Film();
		f.setId(id);
		FilmDAO dao = new FilmDAO();
		PrintWriter out = resp.getWriter();
		
		try {
			dao.deleteFilm(f);
			out.write("Deleted Film");
			
		}catch(SQLException e){
			e.printStackTrace();			
		}
	}

	//This get request sends the user to the delete page using a request dispatcher.
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	RequestDispatcher rd = req.getRequestDispatcher("delete.html");
	rd.include(req, resp);
	}

}
