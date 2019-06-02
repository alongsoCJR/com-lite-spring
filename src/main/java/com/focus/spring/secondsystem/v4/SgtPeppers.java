package com.focus.spring.secondsystem.v4;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Clun Band";

    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + title + " BY " + artist);
    }
}
