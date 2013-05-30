package com.zcwl.ps.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Hugo
 * 
 */
@Controller
public class PictureController {
	
	
	@RequestMapping("/api/pic/postData.do")
	public String postData(ModelMap model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,String content) {
		
		
		
		String temp = request.getHeader("content");
		
		System.out.println("Headers: "+temp);
		System.out.println(content);
		
//		try {
//			
//			InputStream is =request.getInputStream();
//			
//			InputStreamReader reader = new InputStreamReader(is);
//			
//			System.out.println(reader.toString());
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return "common/xmlresult";
	}

	/**
	 * 
	 * @param model
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/api/pic/upload.do")
	public String groupSend(ModelMap model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {

		int resultCode = 0;
		String resultMsg = null;
		
		long MAX_SIZE = 10 * 1024 * 1024;// 设置上传文件最大为 10M

		String basePath = session.getServletContext().getRealPath("/image/");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 4);
		factory.setRepository(new File(basePath));

		ServletFileUpload upload = new ServletFileUpload(factory);

		boolean b = ServletFileUpload.isMultipartContent(request);
		if (!b) {
			resultMsg = "对不起，不是文件上传表单！";
		}
		upload.setSizeMax(MAX_SIZE);

		// 从request得到 所有 上传域的列表
		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			if (e instanceof SizeLimitExceededException) {
				resultMsg = "文件尺寸超过规定大小";
			}
			e.printStackTrace();
		}
		// 没有文件上传
		if (fileList == null || fileList.size() == 0) {
			resultMsg = "请选择上传文件";
		} else {
			Iterator fileItr = fileList.iterator();
			while (fileItr.hasNext()) {
				FileItem fileItem = null;
				String path = null;
				long size = 0;
				// 得到当前文件
				fileItem = (FileItem) fileItr.next();

				// 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
				if (fileItem == null || fileItem.isFormField()) {
					continue;
				}
				// 得到文件的完整路径
				path = fileItem.getName();
				// 得到文件的大小
				size = fileItem.getSize();
				// 得到文件的扩展名(无扩展名时将得到全名)

				// 原来的文件名
				path = basePath +File.separator+ path;
				try {
					// 保存文件
					fileItem.write(new File(path));
					response.setStatus(200);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);

		return "common/xmlresult";
	}
}