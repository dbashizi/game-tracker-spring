package com.tiy;

import bsh.Interpreter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by localdom on 5/17/2016.
 */
@Controller
public class InterpreterController {

    @RequestMapping(path = "/runJava", method = RequestMethod.POST)
    public String runJava(Model model, HttpSession session, String javaCode) throws Exception {

        Interpreter interpreter = new Interpreter();
        String testJavaCode = "public boolean sleepIn(boolean weekday, boolean vacation) {\n" +
                "  if (!weekday || vacation) { \n" +
                "    return true; \n" +
                "  } else { \n" +
                "    return false; \n" +
                "  }\n" +
                "}\n";

        interpreter.eval(javaCode);
        Boolean result = (Boolean)interpreter.eval("sleepIn(false, true);");


        model.addAttribute("result", result);
        return "interpreter";
    }

    @RequestMapping(path = "/runJava", method = RequestMethod.GET)
    public String runJavaInput(Model model, HttpSession session) throws Exception {
        model.addAttribute("result", false);
        return "interpreter";
    }


}
