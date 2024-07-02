package com.desertgm.app.Services;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class UtillsService {

    public String truncate(String value, int maxLength) {
        if (value == null) {
            return null;
        }
        return cleanString(value).length() > maxLength ? cleanString(value).substring(0, maxLength) : cleanString(value);
    }

    public Date parseDate(String dateString, SimpleDateFormat format) {
        dateString = cleanString(dateString); // Limpa a string antes de usar

        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean isValidLong(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return parseLong(str) != null;
    }

    public boolean isDateValid(Date date) {
        if (date == null) {
            return true; // Considera datas vazias como válidas
        }
        Calendar cal = Calendar.getInstance();
        cal.set(1900, Calendar.JANUARY, 1); // Data mínima aceitável: 1º de janeiro de 1900
        Date minDate = cal.getTime();

        cal.set(2100, Calendar.DECEMBER, 31); // Data máxima aceitável: 31 de dezembro de 2100
        Date maxDate = cal.getTime();

        return date.after(minDate) && date.before(maxDate);
    }

    public Long parseLong(String value) {
        try {
            return value != null && !value.isEmpty() ? Long.parseLong(cleanString(value)) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }




    public int parseInt(String value) {
        try {
            return value != null && !value.isEmpty() ? Integer.parseInt(cleanString(value)) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String cleanString(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String cleanedData = input.replaceAll("[^a-zA-Z0-9,\\s\"]", "").replaceAll("^\"|\"$", "");
        // Trata caso específico de campo vazio
        if (cleanedData.isEmpty()) {
            return "";
        }
        // Remove caracteres especiais e espaços
        return cleanedData;
    }
}
