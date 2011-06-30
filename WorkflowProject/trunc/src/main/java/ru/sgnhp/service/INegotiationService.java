package ru.sgnhp.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.domain.NegotiationFileBean;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public interface INegotiationService extends IGenericService<NegotiationBean, Long> {

    public NegotiationBean createNewNegotiationProcess(Date dueDate, String curatorUid, 
            List<String> negotiatorUids, String negotiationType,
            Set<NegotiationFileBean> negotiationFileBeans);
}
