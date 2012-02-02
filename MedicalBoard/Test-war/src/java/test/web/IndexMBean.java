/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.web;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.xhtmlrenderer.pdf.ITextRenderer;
import ru.salavatmed.ejb.HazardSessionBean;
import ru.salavatmed.ejb.UserSessionBean;
import ru.salavatmed.entity.Hazard;
import ru.salavatmed.entity.User;
import ru.salavatmed.utils.TemplateHandler;
import test.web.dto.HazardDto;
import test.web.dto.PatientDto;

/**
 *
 * @author 77han
 */
@Named(value = "indexMBean")
@SessionScoped
public class IndexMBean implements Serializable {

    @EJB
    private HazardSessionBean hazardSessionBean;
    @EJB
    private UserSessionBean userSessionBean;
    @Resource(name = "mail/salavatmedMailSession")
    private Session mailSession;
    private User userDetails;
    private PatientDto patientDto = new PatientDto();
    private String username = "";
    private String appendix;
    private String paragraph;
    private String paragraphDescription;
    //private List<HazardDto> hazards = new ArrayList<HazardDto>();
    private List<SelectItem> hazardList;
    private int counter;
    private TemplateHandler handler;

    /** Creates a new instance of IndexMBean */
    public IndexMBean() {
    }

    @PostConstruct
    public void setUserDetailsByPrincipal() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        String string = externalContext.getUserPrincipal().getName();
        StringTokenizer tokenizer = new StringTokenizer(string, "=,");
        while (tokenizer.hasMoreTokens()) {
            String key = tokenizer.nextToken().trim();
            String value = tokenizer.nextToken().trim().toUpperCase();

            if ("CN".equals(key)) {
                username = value;
                break;
            }
        }
        this.userDetails = userSessionBean.getUserByUsername(username).get(0);
        this.patientDto.setFormOfProperty(this.userDetails.getAuthority().getFormOfProperty());
        this.patientDto.setOkved(this.userDetails.getAuthority().getOkved());
        this.patientDto.setHrManager(this.userDetails.getFullName());
        this.patientDto.setHrEmail(this.userDetails.getEmail());
        this.patientDto.setCompanyName(this.userDetails.getAuthority().getFullName());
        this.patientDto.setMedialogCode(this.userDetails.getAuthority().getMedialogCode());
        this.patientDto.setHazards(new ArrayList<HazardDto>());
        this.counter = 0;
    }

    public void setHazardsByAppendix() {
//        Logger logger = Logger.getAnonymousLogger();
//        logger.warning(this.getAppendix());
        hazardList = new ArrayList<SelectItem>();
        for (Hazard hazard : this.hazardSessionBean.getHazardByAppendix(this.appendix)) {
            getHazardList().add(new SelectItem(hazard.getParagraph(), hazard.getParagraph() + " - " + hazard.getName()));
        }
    }

    public void setHazard() {
        HazardDto hazardDto = new HazardDto();
        hazardDto.setUid(counter++);
        hazardDto.setAppendix(this.appendix);
        hazardDto.setParagraph(this.paragraph);
        hazardDto.setParagraphDescription(hazardSessionBean.getHazardByAppendixAndParagraph(this.appendix, this.paragraph).getName());
        this.patientDto.getHazards().add(hazardDto);
    }

    public void deleteTableRow(HazardDto hazardDto) {
//        Logger logger = Logger.getAnonymousLogger();
//        logger.warning(hazardDto.toString());
        patientDto.getHazards().remove(hazardDto);
    }

    private void sendAssignmentThroughEmail(PatientDto patientDto) throws IOException, DocumentException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String templatesFolder = ctx.getRealPath("/templates");
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setSubject("test", "utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(patientDto.getHrEmail()));

            //Body
            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();
            handler = new TemplateHandler(templatesFolder + "/emailBodyDocument.xhtml",
                    this.setAssignmentMap(patientDto));
            htmlPart.setContent(handler.templateProcessing(), "text/html;charset=utf-8");
            multipart.addBodyPart(htmlPart);

            //Assigment
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            handler = new TemplateHandler(templatesFolder + "/assignment.xhtml",
                    this.setAssignmentMap(patientDto));
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont(
                    templatesFolder + "/verdana.ttf",
                    BaseFont.IDENTITY_H,
                    true);
            renderer.setDocumentFromString(handler.templateProcessing());
            renderer.layout();
            renderer.createPDF(os);

            htmlPart = new MimeBodyPart();
            DataSource contractDataSource = new ByteArrayDataSource(os.toByteArray(), "application/pdf");
            htmlPart.setDataHandler(new DataHandler(contractDataSource));
            htmlPart.setFileName("Napravlenie.pdf");
            multipart.addBodyPart(htmlPart);

            //Personal
            htmlPart = new MimeBodyPart();
            XStream xstream = new XStream(new DomDriver("UTF-8")); // require XPP3 library
            os = new ByteArrayOutputStream();
            xstream.alias("PediatricPatientDto", PatientDto.class);
            DataSource personalDataSource = new ByteArrayDataSource(xstream.toXML(this.patientDto),"application/xml");
            htmlPart.setDataHandler(new DataHandler(personalDataSource));
            htmlPart.setFileName("PersonalData.mxml");
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException me) {
            Logger logger = Logger.getAnonymousLogger();
            logger.severe(me.getLocalizedMessage());
        }
    }

    // Returns the contents of the file in a byte array.
    private byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public void save() throws IOException, DocumentException {
        this.sendAssignmentThroughEmail(patientDto);
    }

    private HashMap setAssignmentMap(PatientDto patientDto) {
        HashMap<String, String> replaceValues = new HashMap<String, String>();
        replaceValues.put("[formOfProperty]", patientDto.getFormOfProperty());
        replaceValues.put("[okved]", patientDto.getOkved());
        replaceValues.put("[lastName]", patientDto.getLastName());
        replaceValues.put("[firstName]", patientDto.getFirstName());
        replaceValues.put("[patronymicName]", patientDto.getPatronymicName());
        replaceValues.put("[birthday]", patientDto.getBirthday());
        replaceValues.put("[departmentName]", patientDto.getDepartmentName());
        replaceValues.put("[post]", patientDto.getPost());
        replaceValues.put("[specialityName]", patientDto.getSpecialityName());
        replaceValues.put("[labourCondition]", patientDto.getLabourCondition());
        String hazards = "";
        for (HazardDto hazardDto : patientDto.getHazards()) {
            hazards = hazards + "<p>Прил.: " + hazardDto.getAppendix()
                    + ". Пункт: " + hazardDto.getParagraph()
                    + ". " + hazardDto.getParagraphDescription() + "</p>";
        }

        replaceValues.put("[hazards]", hazards);
        replaceValues.put("[companyName]", patientDto.getCompanyName());
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        replaceValues.put("[currentDate]", fmt.format(new Date()));
        return replaceValues;
    }

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
     * @return the patientDto
     */
    public PatientDto getPatientDto() {
        return patientDto;
    }

    /**
     * @param patientDto the patientDto to set
     */
    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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

//    /**
//     * @return the hazards
//     */
//    public List<HazardDto> getHazards() {
//        return hazards;
//    }
//
//    /**
//     * @param hazards the hazards to set
//     */
//    public void setHazards(List<HazardDto> hazards) {
//        this.hazards = hazards;
//    }
    /**
     * @return the hazardList
     */
    public List<SelectItem> getHazardList() {
        return hazardList;
    }

    /**
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}