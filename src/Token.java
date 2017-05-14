/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abv17
 */
public class Token {
    private Object compLex;
    private Object lexema;
    Token(Object cl, Object l){
        compLex = cl;
        lexema = l;
    }

    public Object getCompLex() {
        return compLex;
    }

    public Object getLexema() {
        return lexema;
    }

    public void setCompLex(Object compLex) {
        this.compLex = compLex;
    }

    public void setLexema(Object lexema) {
        this.lexema = lexema;
    }
    
}
