package org.bongiorno.misc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Pattern;
import java.util.function.Predicate;

import static java.util.stream.StreamSupport.stream;

public class MultiStringValidator implements ConstraintValidator<PatternForCollections, Iterable<String>> {

    private Predicate<String> matchPredicate;

    @Override
    public void initialize(PatternForCollections annotation) {
        Pattern.Flag[] flags = annotation.flags();
        int intFlags = 0;
        for (Pattern.Flag flag : flags) {
            intFlags = intFlags | flag.getValue();
        }

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(annotation.regexp(), intFlags);
        this.matchPredicate = pattern.asPredicate();
    }

    @Override
    public boolean isValid(Iterable<String> value, ConstraintValidatorContext context) {
        //By convention, @NotNull is the only validation that rejects null values
        return value == null || stream(value.spliterator(), false).allMatch(matchPredicate);
    }
}
