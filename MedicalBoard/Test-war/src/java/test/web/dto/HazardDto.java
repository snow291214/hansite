package test.web.dto;

import java.io.Serializable;

/**
 *
 * Khudyakov Alexey
 * Skype: khudyakov.alexey
 * Email: khudyakov.alexey@gmail.com
 * 
 */
public class HazardDto implements Serializable {
    private static final long serialVersionUID = 5L;
    private int uid;
    private String appendix;
    private String paragraph;
    private String paragraphDescription;

    /**
     * @return the appendix
     */
    public String getAppendix() {
        return appendix;
    }

    /**
     * @param appendix the appendix to set
     */
    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }

    /**
     * @return the paragraph
     */
    public String getParagraph() {
        return paragraph;
    }

    /**
     * @param paragraph the paragraph to set
     */
    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    /**
     * @return the paragraphDescription
     */
    public String getParagraphDescription() {
        return paragraphDescription;
    }

    /**
     * @param paragraphDescription the paragraphDescription to set
     */
    public void setParagraphDescription(String paragraphDescription) {
        this.paragraphDescription = paragraphDescription;
    }

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }
}
