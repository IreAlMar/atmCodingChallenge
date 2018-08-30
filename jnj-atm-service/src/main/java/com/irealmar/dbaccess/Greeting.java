package com.irealmar.dbaccess;

/**
 * TODO: documentar.
 * @version 1.0
 */
public class Greeting {

    private final long id;
    private final String content;

    /**
     * TODO: documentar.
     * @param id id
     * @param content content
     */
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * TODO: documentar.
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * TODO: documentar.
     * @return content
     */
    public String getContent() {
        return content;
    }
}
