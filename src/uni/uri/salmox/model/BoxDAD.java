/*- 
 * Classname:             BoxDAD.java 
 * 
 * Version information:   1.1 
 * 
 * Date:                  02/08/2014 - 08:31:07 
 * 
 * author:                Jonas Mayer (jmayer13@hotmail.com) 
 * Copyright notice:      (informações do método, pra que serve, idéia principal) 
 */
package uni.uri.salmox.model;

import uni.uri.salmox.util.Category;

/**
 * Classe modelo da Caixa do diretor administrativo, herda de caixa
 *
 * @see
 * @author Jonas Mayer (jmayer13@hotmail.com)
 */
public class BoxDAD extends Box {

    //code class box
    private final String CODE_CLASS_BOX = "600.02.";

    /**
     * Construtor sem argumentos
     */
    public BoxDAD() {
        super();
        this.setCodeClass(this.CODE_CLASS_BOX);
    }//fim do construtor sem argumentos

    /**
     * Converte texto para string
     *
     * @return <code>String</code> livro
     */
    @Override
    public String toString() {
        String text = "Caixa " + Category.DAD.getAbbreviation() + " - " + this.getCodeClass() + this.getCodeBoxSpecific()
                + ": " + this.getTitleBox() + " Responsáveis:" + this.getResponsibleBox() + " Observação:" + this.getObservationBox();
        return text;
    }//fim do método toString
}//fim da classe BoxDAD 

