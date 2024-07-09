package Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Juan David
 * This code was created based on the website: http://incepttechnologies.blogspot.com.au/p/validation-in-jsf.html
 */
@FacesValidator(value = "EmailIDValidator")
public class EmailID implements Validator {

    static String emailRegex = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
    static Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    static Matcher matcher;

    public EmailID() {
        log("EmailID Constructor");
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            log("Validating submitted Email ID -- " + value.toString());
            matcher = pattern.matcher(value.toString());

            if (!matcher.matches()) {
                FacesMessage msg
                        = new FacesMessage("Transaction ID Error",
                                "Please provide a valid Email.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);

                throw new ValidatorException(msg);
            }
        }
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}