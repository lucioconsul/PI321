package util;

import dao.BordaDAO;
import dao.BordaDAOImp;
import dao.TamanhoDAO;
import dao.TamanhoDAOImp;
import entidade.Borda;
import entidade.Tamanho;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Lucio
 */
@FacesConverter(value = "comboConverter", forClass = ComboConverter.class)
public class ComboConverter implements Converter, Serializable {


    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        if (arg2 != null) {

            try {
                TamanhoDAO tDAO = new TamanhoDAOImp();
                BordaDAO bDAO = new BordaDAOImp();
                Borda borda;
                Tamanho tamanho;
                tamanho = tDAO.pesquisaNome(arg2);
                if(tamanho != null){
                    return tamanho;
                }else{
                    borda = bDAO.pesquisaBorda(arg2);
                    if(borda != borda){
                        return borda;
                    }
                }
                
            } catch (Exception e) {
            }
            return null;
            }
        return null;

        }
        
    

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return null;
    }
}
