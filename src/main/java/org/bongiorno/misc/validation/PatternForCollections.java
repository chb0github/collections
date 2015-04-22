package org.bongiorno.misc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.Class;import java.lang.String;import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MultiStringValidator.class)
@Documented
public @interface PatternForCollections {

    String message() default "{javax.validation.constraints.Pattern.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String regexp();


    /**
     * @return array of {@code Flag}s considered when resolving the regular expression
     */
    Pattern.Flag[] flags() default { };

}
