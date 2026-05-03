package com.caldeira.projetofinal.user.validators;

import com.caldeira.projetofinal.user.models.request.UserRequestModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRequestValidatorTests {
    UserRequestValidator userRequestValidator = new UserRequestValidator();

    @Test
    void shouldValidateValidUserRequest() {
        UserRequestModel validModel = new UserRequestModel();
        validModel.setFirstName("Carolina");
        validModel.setLastName("Oliveira");

        assertDoesNotThrow(() -> userRequestValidator.validate(validModel));
    }

    @Test
    void shouldThrowExceptionForNullFirstName() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName(null);
        invalidModel.setLastName("Oliveira");

        assertThrows(IllegalArgumentException.class, () -> userRequestValidator.validate(invalidModel));
    }

    @Test
    void shouldThrowExceptionForShortFirstName() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName("Ca");
        invalidModel.setLastName("Oliveira");

        assertThrows(IllegalArgumentException.class, () -> userRequestValidator.validate(invalidModel));
    }

    @Test
    void shouldThrowExceptionForNullLastName() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName("Carolina");
        invalidModel.setLastName(null);

        assertThrows(IllegalArgumentException.class, () -> userRequestValidator.validate(invalidModel));
    }

    @Test
    void shouldThrowExceptionForShortLastName() {
        UserRequestModel invalidModel = new UserRequestModel();
        invalidModel.setFirstName("Carolina");
        invalidModel.setLastName("Ol");

        assertThrows(IllegalArgumentException.class, () -> userRequestValidator.validate(invalidModel));
    }
}
