package chapter08.code.listing.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import chapter08.code.listing.domain.Book;
import chapter08.code.listing.service.BookService;
import chapter08.code.listing.utils.LongNumberPropertyEditor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.blogs.model.BlogsEntry;

/**
 * AddBookController shows the add book form and handles requests for adding a
 * book to the catalog.
 * 
 * @author asarin
 * 
 */
@Controller(value = "addBookController")
@RequestMapping(value = "VIEW")
@SessionAttributes(types = Book.class)
public class AddBookController {
	@Autowired
	@Qualifier("myBookService")
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@ModelAttribute("book")
	public Book getCommandObject() {
		return new Book();
	}

	@RenderMapping(params = "myaction=addBookForm")
	public String showAddBookForm(RenderResponse response) {
		return "addBookForm";
	}

	@InitBinder("book")
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Long.class, new LongNumberPropertyEditor());
	}

	@ExceptionHandler({ Exception.class })
	public String handleException() {
		return "errorPage";
	}

	@ActionMapping(params = "myaction=addBook")
	public void addBook(@Valid @ModelAttribute(value = "book") Book book,
			BindingResult bindingResult, ActionResponse response, ActionRequest request,
			SessionStatus sessionStatus) throws PortalException, SystemException {
		if (!bindingResult.hasErrors()) {
			bookService.addBook(book);
			System.out.println("Adding Book as Resource");
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
			ResourceLocalServiceUtil.addResources(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
				    Book.class.getName(), book.getIsbnNumber(),
				    false, false, false);
			response.setRenderParameter("myaction", "books");
			// --set the session status as complete to cleanup the model
			// attributes
			// --stored using @SessionAttributes, otherwise when you click
			// --'Add Book' button you'll see the book information pre-populated
			// -- because the getCommandObject method of the controller is not
			// --invoked
			sessionStatus.setComplete();
		} else {
			response.setRenderParameter("myaction", "addBookForm");
		}
	}
}