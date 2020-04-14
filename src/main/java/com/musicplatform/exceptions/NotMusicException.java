package com.musicplatform.exceptions;

public class NotMusicException extends Exception {

    public NotMusicException() {
        super("File is not music file.");
    }

}
