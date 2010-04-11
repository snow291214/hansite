package ru.sgnhp.dao;

import java.util.List;
import ru.sgnhp.entity.OutgoingFileBean;
import ru.sgnhp.entity.OutgoingMailBean;

/*****
 *
 * @author Alexey Khudyakov
 * @company "Salavatgazoneftehimproekt" Ltd
 *
 *****/
public interface IOutgoingFileDao extends IGenericDao<OutgoingFileBean, Long> {

    List<OutgoingFileBean> getFilesByOutgoingMail(OutgoingMailBean outgoingMailBean);
}
