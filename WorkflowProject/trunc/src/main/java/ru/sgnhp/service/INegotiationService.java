package ru.sgnhp.service;

import java.util.List;
import java.util.Set;
import ru.sgnhp.domain.NegotiationBean;
import ru.sgnhp.domain.NegotiationFileBean;
import ru.sgnhp.domain.WorkflowUserBean;
import ru.sgnhp.dto.NegotiationDto;

/*****
 *
 * @author Alexey Khudyakov
 * @Skype: khudyakov.alexey
 *
 *****
 */
public interface INegotiationService extends IGenericService<NegotiationBean, Long> {

    public NegotiationBean createNewNegotiationProcess(NegotiationDto negotiationDto, Set<NegotiationFileBean> negotiationFileBeans);

    List<NegotiationBean> findNegotiationsByUser(WorkflowUserBean workflowUserBean);
}
