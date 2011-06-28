package ru.sgnhp.dao.impl;

import ru.sgnhp.dao.INegotiationFileDao;
import ru.sgnhp.domain.NegotiationFileBean;

/*
 *****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public class NegotiationFileDaoImpl extends GenericDaoHibernate<NegotiationFileBean, Long> implements INegotiationFileDao{
    
    public NegotiationFileDaoImpl() {
        super(NegotiationFileBean.class);
    }
    
}
