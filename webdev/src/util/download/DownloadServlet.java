package util.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = "C:/dev/upload";
		String filename = request.getParameter("filename");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", 
			"attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
		byte[] data = new byte[1024 * 100];
		
		InputStream is = new BufferedInputStream(
				new FileInputStream(uploadPath + "/" + filename));
		OutputStream os = new BufferedOutputStream(
				response.getOutputStream());
		while(is.read(data) != -1) {
			os.write(data);
		}
		if (os != null) try {os.flush(); os.close();} catch(Exception e){}
		if (is != null) try {is.close();} catch(Exception e){}
	}
}
