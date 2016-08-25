package ru.begjavaweb.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import ru.begjavaweb.generators.SignGenerator;

public class GameServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, 
		HttpServletResponse res) 
	{
		List<String> result = new ArrayList<>();
		SignGenerator sg = new SignGenerator();

		String sign = sg.getGeneratedSign();
		String pick = req.getParameter("pick");
		
		result.add(sign);
		result.add(pick);
		
		redirectResultToClient(req, res, result);
	}

	private void redirectResultToClient(
		HttpServletRequest req,
		HttpServletResponse res,
		List<String> result) 
	{
		try {
			HttpSession session = req.getSession();
			session.setAttribute("result", result);
			res.sendRedirect("result.jsp");
		} catch (IOException e) {}
	}
}