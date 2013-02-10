/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Lucio
 */


@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")// chave estrangeira pro join
public class Cliente extends Pessoa implements Serializable{
    
    // static nao pertence ao objeto, compartilha a variavel // final é pq nunca mais vai mudar. 1L = 1 long
    private static final long serialVersionUID = 1L; 
    
    

    
}
