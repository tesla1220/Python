package thefirstgroup.crud_practice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thefirstgroup.crud_practice.model.dto.EmployeeDTO;
import thefirstgroup.crud_practice.model.service.EmployeeService;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    private final MessageSource messageSource;

    @Autowired
    public EmployeeController(EmployeeService employeeService, MessageSource messageSource){
        this.employeeService = employeeService;
        this.messageSource = messageSource;
    }
    @GetMapping("/viewAll")
    public String viewAll(Model model){
        List<EmployeeDTO> employeelist = employeeService.findAllEmployees();

        model.addAttribute("employeeList", employeelist);
        return "viewAll";
    }

    @GetMapping("/searchEmployee")
    public String viewByEmployeeId(Model model){
        return "searchEmployee";
    }
    @PostMapping("/searchEmployee")
    public String searchEmployee(@RequestParam("empId") int empId, RedirectAttributes rttr, Locale locale, Model model){
        EmployeeDTO employee = employeeService.searchEmployee(empId);
        if(employee == null){
            System.out.println("Error");
            rttr.addFlashAttribute("failed",
                    messageSource.getMessage("failed", null, locale));
            System.out.println(rttr.getFlashAttributes());
            return "redirect:/error";
        }
        model.addAttribute("employee", employee);
        return "viewByEmpId";
    }
    @GetMapping("delete")
    public void delete() {}

    @PostMapping("/delete")
    public String deleteEmployeeById(@RequestParam("empId") int empId, RedirectAttributes rttr, Locale locale) {
        employeeService.deleteEmployeeById(empId);
        rttr.addFlashAttribute("success Message", "성공");
//messageSource.getMessage("deleteEmp", null, locale)
        return "redirect:/";
    }

}
