/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ColaboradorDAO;
import dao.ColaboradorDAOImp;
import dao.EnderecoDAO;
import dao.EnderecoDAOImp;
import dao.FuncaoDAO;
import dao.FuncaoDAOImp;
import dao.MenuDAO;
import dao.PerfilDAO;
import dao.PerfilDAOImp;
import dao.PessoaDAO;
import dao.PessoaDAOImp;
import dao.UsuarioDAO;
import dao.UsuarioDAOImp;
import entidade.Colaborador;
import entidade.Endereco;
import entidade.Funcao;
import entidade.Menus;
import entidade.Perfil;
import entidade.Pessoa;
import entidade.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import util.Cripto;

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

//#####################################################################################################################################    
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

    public Colaborador getColab() {
        if(colab==null){
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

//#####################################################################################################################################
    public String salvar() {
        cdao = new ColaboradorDAOImp();
        pDAO = new PessoaDAOImp();

        FacesContext context = FacesContext.getCurrentInstance();

        setColab(colab);
        if (colab.getId() == null) {

            ArrayList<Endereco> enderecos = new ArrayList();
            enderecos.add(end);
            colab.setEnderecos(enderecos);

            usu.setPerfil(perfil);
            String senhaa = Cripto.criptoGraf(usu.getSenha());
            usu.setSenha(senhaa); //setar senha criptografada
            ArrayList<Usuario> usuarios = new ArrayList();
            usuarios.add(usu);
            
            colab.setUsuarios(usuarios);
            colab.setEnderecos(enderecos);
            colab.setFuncao(func);

            end.setPessoa(colab);
            usu.setColaborador(colab);

            try {
                pDAO.salva(colab);
                context.addMessage(null, new FacesMessage("Sapore", "Colaborador salvo com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar Colaborador!"));
            }

        } else {
            try {
                pDAO.altera(colab);
                context.addMessage(null, new FacesMessage("Sapore", "Colaborador alterado com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar alterar Colaborador!"));
            }
        }
        limpar();
        return "cadFuncionario.faces";
    }

//#####################################################################################################################################
    public void pesquisaLikeNome() {

        setColab(colab);
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            cdao = new ColaboradorDAOImp();
            List<Colaborador> colabs = cdao.pesquisaLikeNome(colab.getNome());
            model = new ListDataModel(colabs);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar pesquisar Colaborador!"));
        }

    }

//#####################################################################################################################################
    private void limpar() {
        colab = null;
        end = null;
        func = null;
        perfil = null;
        usu = null;
    }

//#####################################################################################################################################
    public String limpaPesquisa() {
        colab = null;
        model = null;
        return "pesqFuncionario";
    }

//#####################################################################################################################################
    public String novoColaborador() {
        limpar();
        colab = new Colaborador();
        return "cadFuncionario";
    }

//#####################################################################################################################################
    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            colab = (Colaborador) model.getRowData();
            setColab(colab);
            func = colab.getFuncao();

            UsuarioDAO uDAO = new UsuarioDAOImp();
            usu = uDAO.pesquisaByIdColab(colab.getId());
            perfil = usu.getPerfil();

            EnderecoDAO eDAO = new EnderecoDAOImp();
            end = eDAO.pesquisaByIdColab(colab.getId());

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar alterar Colaborador"));
        }


        return "cadFuncionario";
    }

//#####################################################################################################################################
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = colab.getNome();
        try {
            cdao = new ColaboradorDAOImp();
            colab = (Colaborador) model.getRowData();
            cdao.remove(colab);
            model = new ListDataModel(cdao.pesquisaLikeNome(nome));
            context.addMessage(null, new FacesMessage("Sapore", "Colaborador excluído com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar excluir Colaborador!"));
        }
        limpar();
        return "";
    }

//#####################################################################################################################################
    public List<SelectItem> getComboFuncao() {
        FacesContext context = FacesContext.getCurrentInstance();
        FuncaoDAO fdao = new FuncaoDAOImp();
        try {
            List<Funcao> funcoes = fdao.getTodos();
            List<SelectItem> listaCombo = new ArrayList<SelectItem>();
            for (Funcao func : funcoes) {
                listaCombo.add(new SelectItem(func.getId(), func.getNome()));
            }
            return listaCombo;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar pesquisar lista de funções!"));
            return null;
        }

    }

//#####################################################################################################################################
    public List<SelectItem> getComboPerfil() {
        FacesContext context = FacesContext.getCurrentInstance();
        PerfilDAO pdao = new PerfilDAOImp();
        try {
            List<Perfil> perfis = pdao.getTodos();
            List<SelectItem> listaCombo = new ArrayList<SelectItem>();
            for (Perfil perf : perfis) {
                listaCombo.add(new SelectItem(perf.getId(), perf.getNome()));
            }
            return listaCombo;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar pesquisar lista de perfis!"));
            return null;
        }
    }
}
