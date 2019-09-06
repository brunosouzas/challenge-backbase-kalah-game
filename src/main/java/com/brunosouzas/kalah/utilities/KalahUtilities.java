package com.brunosouzas.kalah.utilities;

import org.springframework.web.util.UriTemplate;

public class KalahUtilities {

    public static String generateGameUrl(int gameId, String url) {
        return new UriTemplate(url).expand(gameId).toString();
    }
	
}
