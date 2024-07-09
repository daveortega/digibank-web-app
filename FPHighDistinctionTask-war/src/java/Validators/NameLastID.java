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
@FacesValidator(value = "NameIDValidator")
public class NameLastID implements Validator {

    static String NameRegex = "^[a-z A-Z]{0,100}";
    static Pattern pattern = Pattern.compile(NameRegex, Pattern.CASE_INSENSITIVE);
    static Matcher matcher;

    public NameLastID() {
        log("NameID Constructor");
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            log("Validating submitted Name ID -- " + value.toString());
            matcher = pattern.matcher(value.toString());

            if (!matcher.matches()) {
                FacesMessage msg
                        = new FacesMessage("NameID ID Error",
                                "Name must not contain numeric or special values.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);

                throw new ValidatorException(msg);
            }
        }
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}