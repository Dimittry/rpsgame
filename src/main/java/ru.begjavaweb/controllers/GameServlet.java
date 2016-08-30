package ru.begjavaweb.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import ru.begjavaweb.generators.SignGenerator;
import ru.begjavaweb.models.Result;
import ru.begjavaweb.helpers.WinnerFinder;

public class GameServlet extends HttpServlet {


	@Override
	public void doPost(HttpServletRequest req, 
		HttpServletResponse res) 
	{
		List<Result> result = new ArrayList<>();
		SignGenerator sg = new SignGenerator();

		String sign = sg.getGeneratedSign();
		String pick = req.getParameter("pick");

		WinnerFinder wf = new WinnerFinder(pick, sign);
		
		try {
			result.add(wf.getWinner());
		} catch(Exception e) {
			result.add(new Result(e.getMessage(), pick, sign));
		}
		
		redirectResultToClient(req, res, result);
	}

	private void redirectResultToClient(
		HttpServletRequest req,
		HttpServletResponse res,
		List<Result> result) 
	{
		try {
			HttpSession session = req.getSession();
			session.setAttribute("result", result);
			res.sendRedirect("result.jsp");
		} catch (IOException e) {}
	}
}