package chapter08.code.listing.controller;

import javax.portlet.ActionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import chapter08.code.listing.domain.Book;
import chapter08.code.listing.service.BookService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.blogs.model.BlogsEntry;

/**
 * RemoveBookController handles removal of Book from the Catalog.
 * 
 * @author asarin
 *
 */
@Controller
@RequestMapping("VIEW")
public class RemoveBookController {
	@Autowired
	@Qualifier("myBookService")
	private BookService bookService;

	@ActionMapping(params="myaction=removeBook")
	public void removeBook(@RequestParam Long isbnNumber , ActionRequest request) throws PortalException, SystemException {
		bookService.removeBook(isbnNumber);
		System.out.println("Removing book from Resources");
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		ResourceLocalServiceUtil.deleteResource(
				themeDisplay.getCompanyId(), Book.class.getName(),
			    ResourceConstants.SCOPE_INDIVIDUAL, isbnNumber);
	}
	
	@ExceptionHandler({ Exception.class })
	public String handleException() {
		return "errorPage";
	}
}
