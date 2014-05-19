package com.wwhs.fileuploadapp;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.activation.FileDataSource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.util.PortalUtil;
import com.wwhs.fileuploadapp.bean.UploadBean;


@Controller("springFileController")
@RequestMapping(value = "VIEW")
public class FileUploadController {
	
	@ModelAttribute("uploadBean")
	public UploadBean getCommandObject() {
		System.out
				.println("uploadbean created");
		return new UploadBean();
	}
	
	@RenderMapping
	public String showHomePage(RenderRequest request, RenderResponse response) {
		
		System.out.println("FileUploadController");
		return "home";
	}
	
	@ActionMapping(params="action=fileUpload")
	public void fileUpload(ActionRequest request, ActionResponse response) throws IOException {
		
/*		Enumeration<String> enum1 = request.getParameterNames();
        while(enum1.hasMoreElements()){
        	System.out.println(enum1.nextElement());
        }*/
		HttpServletRequest origrequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
//		PortalUtil.getUploadServletRequest(request);
		Enumeration<String> eum1= origrequest.getParameterNames();
		while(eum1.hasMoreElements()){
//			System.out.println(eum1.nextElement());
			System.out.println(origrequest.getParameter(eum1.nextElement()));
		}
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(origrequest);
		System.out.println(isMultipartContent);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(origrequest);
			System.out.println("Number of fields: " + fields.size() + "<br/><br/>");
			Iterator<FileItem> it = fields.iterator();
			
			while (it.hasNext()) {
			
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					System.out.println("<td>regular form field</td><td>FIELD NAME: " + fileItem.getFieldName() + 
							"<br/>STRING: " + fileItem.getString()
							);
					System.out.println("</td>");
				} else {
					System.out.println("<td>file form field</td><td>FIELD NAME: " + fileItem.getFieldName() +
							"<br/>STRING: " + fileItem.getString() +
							"<br/>NAME: " + fileItem.getName() +
							"<br/>CONTENT TYPE: " + fileItem.getContentType() +
							"<br/>SIZE (BYTES): " + fileItem.getSize() +
							"<br/>TO STRING: " + fileItem.toString()
							);
					System.out.println("</td>");
				}
				System.out.println("</tr>");
			}
			System.out.println("</table>");
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
//        System.out.println(bean.getFiledata().getOriginalFilename());
		
	}
	
	@ResourceMapping("uploadMe")
	public ModelAndView getDateTime(ResourceRequest request, ResourceResponse response,@ModelAttribute("uploadBean") UploadBean bean) throws IOException {
		
		System.out.println("hello ajax call!!");
		HttpServletRequest origrequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(origrequest);
		System.out.println(isMultipartContent);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(origrequest);
			System.out.println("Number of fields: " + fields.size() );
			Iterator<FileItem> it = fields.iterator();
			
			while (it.hasNext()) {
			
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				System.out.println(isFormField);
				if (isFormField) {
					System.out.println("in if");
//					System.out.println(fileItem.getSize());
//					System.out.println( fileItem.getFieldName()); 
//						System.out.println(fileItem.getString());	
						
					
				} else {
					System.out.println("in else");
//					System.out.println(fileItem.getFieldName()); 
//					System.out.println(fileItem.getString()); 
							System.out.println(fileItem.getName() );
							System.out.println(fileItem.getContentType()); 
							 System.out.println(fileItem.getSize() );
//							 System.out.println(	fileItem.toString());
							
					
					
				}
				
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
       
		return new ModelAndView("home");
	}
	
	
}
