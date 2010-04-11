package ru.sgnhp.services;

import java.util.List;
import ru.sgnhp.entity.OutgoingFileBean;
import ru.sgnhp.entity.OutgoingMailBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****/
public interface IOutgoingFileService extends IGenericService<OutgoingFileBean, Long> {
    
    List<OutgoingFileBean> getFilesByOutgoingMail(OutgoingMailBean outgoingMailBean);
}
