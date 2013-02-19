/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.Menus;
import entidade.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.panelmenu.PanelMenu;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

/**
 *
 * @author XP-PC
 */
@ManagedBean(name = "menuC")
@SessionScoped
public class MenusControle {

    private Usuario usu;
    private Menus menu;
    private PanelMenu pModel;
    private MenuModel mModel;     

//#####################################################################################################################################    
    
    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    public PanelMenu getpModel() {
        return pModel;
    }

    public void setpModel(PanelMenu pModel) {
        this.pModel = pModel;
    }

    public MenuModel getmModel() {
        return mModel;
    }

    public void setmModel(MenuModel mModel) {
        this.mModel = mModel;
    }    
    
    
//#####################################################################################################################################
//CONSTRUTOR chama o geraMenu
    
    public MenusControle() {
        geraMenu();
    }      

//#####################################################################################################################################
//metodo principal que cria os menus pais e chama os demais metodos que por sua vez criam os submenus
    
    public void geraMenu() {
        pModel = new PanelMenu();
        mModel = new DefaultMenuModel();
        //pego sessão
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        //pego usuario logado
        usu = (Usuario) session.getAttribute("autenticado");
        //pego lista de menus do usuario e percorro
        List<Menus> listaMenu = usu.getPerfil().getMenus();
        for (Menus menu : listaMenu) {
            //se o menu é pai, crio o submenu e seto a Label
            if (menu.getId_menu_pai() == null) {
                Submenu menuPai = new Submenu();
                menuPai.setLabel(menu.getLabel());
                menuPai.setIcon(menu.getIcone());                    
                //percorro uma lista de menus filhos dele
                List<Menus> listaSubMenu = getMenusFilhos(menu);
                for (Menus subMenuDaLista : listaSubMenu) {
                    //se o menu filho NAO tem filhos (netos), faço dele um menuItem
                    if (!verificaSub(subMenuDaLista)) {
                        MenuItem menuItem = new MenuItem();
                        menuItem.setValue(subMenuDaLista.getLabel());
                        menuItem.setAjax(false);
                        menuItem.setUrl(subMenuDaLista.getUrl());
                        menuItem.setIcon(subMenuDaLista.getIcone());
                        menuPai.getChildren().add(menuItem);
                        //se o menu filho TEM filhos (netos), monto eles:    
                    } else {
                        Submenu menuFilhoDoMeio = new Submenu();
                        menuFilhoDoMeio.setLabel(subMenuDaLista.getLabel());
                        //chamo metodo que vai montar os submenus e subitens
                        menuFilhoDoMeio = geraSubMenu(subMenuDaLista);
                        menuPai.getChildren().add(menuFilhoDoMeio);
                    }
                }
        
                mModel.addSubmenu(menuPai);
                pModel.setModel(mModel);
            }
        }
    }

//#####################################################################################################################################
//metodo recursivo. ele chama ele mesmo até terminar de montar a arvore passando por todas as gerações

    public Submenu geraSubMenu(Menus menuProcuraFilho) {
        Submenu submenu = new Submenu();
        submenu.setLabel(menuProcuraFilho.getLabel());
        //percorro a lista de filhos
        for (Menus m : getMenusFilhos(menuProcuraFilho)) {
            //verifico se o filho tem filho. se nao tem, vira menuItem
            if (!verificaSub(m)) {
                MenuItem mi = new MenuItem();
                mi.setValue(m.getLabel());
                mi.setUrl(m.getUrl());
                submenu.getChildren().add(mi);
                //se tem filhos, chama novamente este mesmo método
            } else {
                submenu.getChildren().add(geraSubMenu(m));
            }
        }
        return submenu;
    }

//#####################################################################################################################################
//metodo que pega os menus filhos

    public List<Menus> getMenusFilhos(Menus mf) {
        List<Menus> menusFilhos = new ArrayList();
        //pego sessão
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        //pego usuario logado
        usu = (Usuario) session.getAttribute("autenticado");
        //pego lista de menus do usuario e percorro
        List<Menus> listaMenu = usu.getPerfil().getMenus();
        for (Menus menus1 : listaMenu) {
            Menus menus = (Menus) menus1;
            if (mf.getId().toString().equals(menus.getId_menu_pai())) {
                menusFilhos.add(menus);
            }
        }
        return menusFilhos;
    }

//#####################################################################################################################################
//verifica se tem submenus

    public boolean verificaSub(Menus mu) {
        boolean flag = false;
        //pego sessão
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        //pego usuario logado
        usu = (Usuario) session.getAttribute("autenticado");
        //pego lista de menus do usuario e percorro
        List<Menus> listaMenu = usu.getPerfil().getMenus();

        for (Menus menu : listaMenu) {
            if (mu.getId().toString().equals(menu.getId_menu_pai())) {
                flag = true;
            }
        }
        return flag;
    }    

}