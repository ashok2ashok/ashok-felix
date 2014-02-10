package chapter08.code.listing.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import chapter08.code.listing.domain.Book;
import chapter08.code.listing.domain.User;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * AddBookController shows the add book form and handles requests for adding a
 * book to the catalog.
 * 
 * @author asarin
 * 
 */
@Controller(value = "addUserController")
@RequestMapping(value = "VIEW")
@SessionAttributes(types = User.class)
public class AddUserController {
	
	@ModelAttribute("user")
	public User getCommandObject() {
		return new User();
	}
	
	@RenderMapping(params = "myaction=addUserForm")
	public String showAddBookForm(RenderResponse response) {
		return "addUserForm";
	}
	
	@ExceptionHandler({ Exception.class })
	public String handleException() {
		return "errorPage";
	}
	
	@ActionMapping(params = "myaction=addUser")
	public void addUser(@ModelAttribute(value = "user") User user,
			BindingResult bindingResult, ActionResponse response, ActionRequest actionRequest,
			SessionStatus sessionStatus) throws PortalException, SystemException {
			System.out.println("Adding User");
			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			long[] roleIds = null;
			long[] userGroupIds = null;
			long[] groupIds = null;
			com.liferay.portal.model.User newUser = UserLocalServiceUtil.addUser(
						themeDisplay.getRealUserId(), 
						themeDisplay.getCompanyId(), 
						false, 
						"password", 
						"password", 
						true, 
						"empty", 
						user.getEmail(), 
						0L, 
						"", 
						themeDisplay.getLocale(),
						user.getFname(), 
						user.getMname(), 
						user.getLname(), 
						ParamUtil.getInteger(actionRequest, "prefixId"), 
						ParamUtil.getInteger(actionRequest, "suffixId"), 
						ParamUtil.get(actionRequest, "male", true), 
						1, 1, 1900, 
						"Job Title", 
						groupIds, 
						themeDisplay.getUser().getOrganizationIds(), 
						roleIds, 
						userGroupIds, 
						false, 
						ServiceContextFactory.getInstance(actionRequest));
				
			System.out.println("Create User Successfully in Liferay:"+newUser.getUserId());
			response.setRenderParameter("myaction", "books");
			sessionStatus.setComplete();
	}
	
	public static boolean hasPermission(ThemeDisplay themeDisplay,
			String permissionKey) {
		long scopeGroupId = themeDisplay.getScopeGroupId();
		String name = themeDisplay.getPortletDisplay().getRootPortletId();
		String primKey = themeDisplay.getPortletDisplay().getResourcePK();
		PermissionChecker permissionChecker = themeDisplay
				.getPermissionChecker();
		return permissionChecker.hasPermission(scopeGroupId, name, primKey,
				permissionKey);
	}
}