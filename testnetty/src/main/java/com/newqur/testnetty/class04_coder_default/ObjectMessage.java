package com.newqur.testnetty.class04_coder_default;

import java.io.Serializable;

/**
 * @author charles-desktop
 */
public class ObjectMessage implements Serializable {
    private static final long serialVersionUID = -7543514952950971498L;
    private String id;
    private String content;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
