
/**
 * Copyright (C) 2014 Nebel TV (http://nebel.tv)
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
import com.nebel_tv.content.wrapper.IWrapper;
import com.nebel_tv.content.wrapper.Wrapper;
import com.nebel_tv.content.wrapper.WrapperTypes;
import com.nebel_tv.content.wrapper.WrapperUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class MediasService extends HttpServlet {

	private final IWrapper w = Wrapper.getWrapper(WrapperTypes.LIVE);

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			CORSUtil.fixCORS(request, response);
			final String n = request.getParameter("n");
			final String skip = request.getParameter("skip");
			final String category = request.getParameter("category");
			final String viewType = request.getParameter("viewType");
			final String viewTypePeriod = request.getParameter("viewTypePeriod");
			Integer nParsed = WrapperUtils.getInt(n);
			Integer skipParsed = WrapperUtils.getInt(skip);

			if (category != null) {
				/*
				 * @param n - number of items to return [integer]
				 * @param skip - number of how much items to skip [integer]
				 * @param category - media category (films, series, images, etc) [1,2,3...]
				 * or [string]
				 * @param viewType - type of returned media (latest, best, most
				 * commented....) [1,2,3...] or [string]
				 * @param viewTypePeriod - period of type (for example, best for
				 * today/week/month, etc..) ['today', 'week', 'month']
				 */
				out.print(w.getMedias(nParsed, skipParsed, category, viewType, viewTypePeriod));
			}
		} catch (Exception e) {
			System.out.println("e: " + e);
		} finally {
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
