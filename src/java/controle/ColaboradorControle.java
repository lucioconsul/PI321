/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ColaboradorDAO;
import dao.ColaboradorDAOImp;
import dao.EnderecoDAO;
import dao.EnderecoDAOImp;
import dao.MenuDAO;
import dao.MenuDAOImp;
import dao.PessoaDAO;
import dao.PessoaDAOImp;
import dao.UsuarioDAO;
import dao.UsuarioDAOImp;
import entidade.Colaborador;
import entidade.Menus;
import entidade.Pessoa;
import entidade.Colaborador;
import entidade.Endereco;
import entidade.Funcao;
import entidade.Perfil;
import entidade.Usuario;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;



/**
 *
 * @author Aluno
 */
@ManagedBean(name = "colabC")
@SessionScoped
public class ColaboradorControle {

    private Colaborador colab;
    private ColaboradorDAO cdao;
    private PessoaDAO pDAO;
    private Endereco end;
    private Funcao func;
    private Usuario usu;
    private Perfil perfil;
    private Pessoa pessoa;
    private List<Menus> menus;
    private MenuDAO mdao;
    private DataModel model;

    
    public PessoaDAO getpDAO() {        
        return pDAO;
    }

    public void setpDAO(PessoaDAO pDAO) {
        this.pDAO = pDAO;
    }

    public Pessoa getPessoa() {
        if (pessoa == null) {
            pessoa = new Pessoa();
        } 
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
    public Perfil getPerfil() {
        if (perfil == null) {
            perfil = new Perfil();
        }
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    
    public Funcao getFunc() {
        if (func == null) {
            func = new Funcao();
        }  
        return func;
    }

    public void setFunc(Funcao func) {
        this.func = func;
    }

    
    public Usuario getUsu() {
        if (usu == null) {
            usu = new Usuario();
        }        
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Endereco getEnd() {
        if (end == null) {
            end = new Endereco();
        }  
        return end;
    }

    public void setEnd(Endereco end) {              
        this.end = end;
    }

    public Colaborador getColab(){
        if (colab == null) {
            colab = new Colaborador();
        }
        return colab;
    }

    public void setColab(Colaborador colab) {
        this.colab = colab;
    }
    
    
    public ColaboradorDAO getCdao() {
        return cdao;
    }

    public void setCdao(ColaboradorDAO cdao) {
        this.cdao = cdao;
    }

    public List<Menus> getMenus() {
        return menus;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    
   
//##############################################################################        
    
    public String salvar() {
        cdao = new ColaboradorDAOImp();
        pDAO = new PessoaDAOImp();
        
        FacesContext context = FacesContext.getCurrentInstance();
        if (colab.getId() == null) {     
                        
            ArrayList<Endereco> enderecos = new ArrayList();
            enderecos.add(end);
            colab.setEnderecos(enderecos);
            
            usu.setPerfil(perfil);
            ArrayList<Usuario> usuarios = new ArrayList();
            usuarios.add(usu);
            
            colab.setUsuarios(usuarios);
            colab.setEnderecos(enderecos);
            colab.setFuncao(func);   
            
            end.setPessoa(colab);
            usu.setColaborador(colab);
            
            pDAO.salva(colab);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Colaborador Salvo Com Sucesso!", ""));
        } else {
            pDAO.altera(colab);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Colaborador Alterado Com Sucesso!", ""));
        }
        limpar();
        return "admin.faces";
    }
    
//##############################################################################            
      public void pesquisaLikeNome() {
        pDAO = new PessoaDAOImp();
        List<Pessoa> pessoas = pDAO.pesquisaLikeNome(pessoa.getNome());
        model = new ListDataModel(pessoas);
    }
    
//##############################################################################        

    private void limpar() {
        colab = null;
        end = null;
        func = null;
        perfil = null;
        usu = null;
    
    }
   
//##############################################################################            
    public String limpaPesquisa() {
        colab = null;
        model = null;
        return "pesqFuncionario";
    }
//##############################################################################                    
    public String novoColaborador() {
        limpar();
        colab = new Colaborador();
        //implementar a pesquisa de fornecedores
        return "cadFuncionario";
    }
    
//##############################################################################                        
        public String alterar() {
        colab = (Colaborador) model.getRowData();                
        setColab(colab);        
        func = colab.getFuncao();
        
        UsuarioDAO uDAO = new UsuarioDAOImp();
        usu = uDAO.pesquisaByIdColab(colab.getId());
        perfil = usu.getPerfil();       
        
        EnderecoDAO eDAO = new EnderecoDAOImp();
        end = eDAO.pesquisaByIdColab(colab.getId());
        
        
        return "cadFuncionario";
    }
    
//##############################################################################                            
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = pessoa.getNome();
        try {
            pDAO = new PessoaDAOImp();
            pessoa = (Pessoa) model.getRowData();            
            pDAO.remove(pessoa);
            model = new ListDataModel(pDAO.pesquisaLikeNome(nome));
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Colaborador excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
    }
    
}
