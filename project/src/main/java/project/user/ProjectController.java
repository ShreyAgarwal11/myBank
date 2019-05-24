package project.user;

import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {
	@Autowired
	UserRepository userRepository; 
	
	@RequestMapping("/welcome")
	public String getWelcomePage(Model model) {
		model.addAttribute("signUpDetails", new User());
		return "welcomePage";
	}
	
	@RequestMapping(value="/welcome/user", method=RequestMethod.POST)
	public String addUser(@ModelAttribute("signUpDetails") User user) {
		userRepository.save(user);
		return "redirect:/user";
	}
	@RequestMapping("/user")
	public String userPage(Model model, User user) {
		return "userPage";
	}
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	@RequestMapping("/user/checkBalance/{customerId}")
	public ModelAndView balancePage(Model model, @PathVariable Long customerId) {
		ModelAndView mav = new ModelAndView("checkBalance");
		model.addAttribute("users", userRepository.findAll());
		return mav;
	}
	@RequestMapping("/user/accounts")
	public String accountPage(Model model) {
		model.addAttribute("details", userRepository.findAll());
		return "accounts";
	}
	@RequestMapping("/user/account/update/{customerId}")
	public ModelAndView updatePage(@PathVariable Long customerId,Model model) {
		ModelAndView mav = new ModelAndView("update");
		User user = userRepository.findById(customerId).get();
		mav.addObject("uDetails",user);
		return mav;
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String userPage(@ModelAttribute("uDetails") User user) {
		userRepository.save(user);
		return "redirect:/user";
	}
	@RequestMapping("/user/loans")
	public String loanPage(Model model) {
		model.addAttribute("ldetails", userRepository.findAll());
		return "loan";
	}
	@RequestMapping("/user/loan/addLoan")
	public String addLoanPage(Model model) {
		model.addAttribute("lRequest", new User());
		return"addLoan";
	}
	@RequestMapping(value="/addLoan", method=RequestMethod.PUT)
	public String addingLoan(@ModelAttribute("lRequest") User user) {
		userRepository.save(user);
		return "redirect:/user";
	}
	@RequestMapping("/user/payments")
	public String paymentPage(Model model) {
		model.addAttribute("payment", new User());
		return "payment";
	}
	@RequestMapping("/admin")
	public String adminPage() {
		return "admin";
	}
	@RequestMapping("/admin/userDetails")
	public String userDetailsPage(Model model) {
		model.addAttribute("userD", userRepository.findAll());
		return "userDetails";
	}
}
