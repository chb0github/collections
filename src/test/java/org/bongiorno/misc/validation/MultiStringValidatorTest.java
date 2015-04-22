package org.bongiorno.misc.validation;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Ignore
public class MultiStringValidatorTest {

    @Test
    public void testIsValid() throws Exception {
        TestClass testClass = new TestClass();
        testClass.validate = Arrays.asList("foo.baz.bat","something");

        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        Set<ConstraintViolation<TestClass>> results = validator.validate(testClass);
        assertEquals(0,results.size());

        testClass.validate = Arrays.asList("foo.baz[0].bat","something");
        results = validator.validate(testClass);
        assertEquals(1,results.size());
        System.out.println(results);

    }

    private static class TestClass {

        @PatternForCollections(regexp = "^[a-zA-Z0-9\\.]+$", message = "field names must not contain [] or any sort of indexing. Alpha-numeric or '.' (dot) only")
        private List<String> validate;
    }

}