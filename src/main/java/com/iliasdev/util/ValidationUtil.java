package com.iliasdev.util;

import com.iliasdev.dto.NewMatchDTO;
import com.iliasdev.exception.InvalidParameterException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public void validate(NewMatchDTO newMatchDTO) {
        String player1 = newMatchDTO.getPlayerOneName();
        String player2 = newMatchDTO.getPlayerTwoName();

        if(player1 == null || player1.isBlank()) {
            throw new InvalidParameterException("Player one name is blank");
        }

        if(player2 == null || player2.isBlank()) {
            throw new InvalidParameterException("Player two name is blank");
        }

        if(player1.equalsIgnoreCase(player2)) {
            throw new InvalidParameterException("Player's names are equal");
        }

        if (!player1.matches("[a-zA-Z ]+") || !player2.matches("[a-zA-Z ]+")) {
            throw new InvalidParameterException("Player's name can only contain letters and space");
        }
    }

}
