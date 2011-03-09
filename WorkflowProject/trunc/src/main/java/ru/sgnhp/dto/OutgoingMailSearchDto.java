package ru.sgnhp.dto;

import java.io.Serializable;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class OutgoingMailSearchDto extends OutgoingMailDto implements Serializable {

    private int searchType;
    private static final long serialVersionUID = 9L;

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
