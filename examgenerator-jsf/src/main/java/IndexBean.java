import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.dao.ProfessorDAO;
import br.com.devdojo.examgenerator.persistence.model.support.Token;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexBean implements Serializable {

    private String message = "Working";
    private final ProfessorDAO mProfessorDAO;

    @Inject
    public IndexBean(ProfessorDAO professorDAO) {
        mProfessorDAO = professorDAO;
    }

    public void checkProfessor() {
        mProfessorDAO.getProfessorById(1L);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
