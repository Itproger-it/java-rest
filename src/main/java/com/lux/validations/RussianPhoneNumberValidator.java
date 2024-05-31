package com.lux.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class RussianPhoneNumberValidator implements ConstraintValidator<RussianPhoneNumber, String> {

    @Override
    public void initialize(RussianPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return true; // Пустой номер считается валидным
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber russianNumber = phoneNumberUtil.parse(phoneNumber, "RU");
            return phoneNumberUtil.isValidNumberForRegion(russianNumber, "RU");
        } catch (NumberParseException e) {
            // Ошибка парсинга номера телефона
            return false;
        }
    }
}