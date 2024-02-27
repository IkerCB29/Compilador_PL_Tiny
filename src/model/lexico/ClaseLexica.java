package model.lexico;

public enum ClaseLexica {
    ENTERO("<int>"), 
    REAL("<real>"), 
    BOOLEAN("<bool>"), 
    STRING("<string>"), 
    IDENTIFICADOR, 
    LITERAL_ENTERO, 
    LITERAL_REAL, 
    LITERAL_TRUE("<true>"), 
    LITERAL_FALSE("<false>"), 
    LITERAL_STRING, 
    SUMA("+"), 
    RESTA("-"), 
    POR("*"), 
    DIV("/"), 
    MODULO("%"),
    AND("<and>"), 
    OR("<or>"), 
    NOT("<not>"), 
    MENOR("<"), 
    MAYOR(">"), 
    MENOR_IGUAL("<="), 
    MAYOR_IGUAL(">="), 
    IGUAL("=="),
    DIFERENTE("!="), 
    ASIGNACION("="), 
    PYC(";"), 
    PAP("("), 
    PCIERRE(")"), 
    LLAVE_APERTURA("{"), 
    LLAVE_CIERRE("}"),
    COR_APERTURA("["), 
    COR_CIERRE("]"), 
    COMA(","), 
    ACCESO("."), 
    EVALUA("@"), 
    EOF("EOF"), 
    CAMBIO_SEC("&&"),
    NULL("<null>"), 
    PROC("<proc>"),  
    IF("<if>"),  
    ELSE("<else>"), 
    WHILE("<while>"),  
    STRUCT("<struct>"),  
    NEW("<new>"), 
    DELETE("<delete>"),  
    READ("<read>"), 
    WRITE("<write>"), 
    NL("<nl>"), 
    TYPE("<type>"),  
    CALL("<call>"),  
    REFERENCIA("&"), 
    PUNTERO("^"), ;
	
    private final String image;
    
    public String getImage() {
         return image;
     }
    
    ClaseLexica() {
         image = toString();
     }

    ClaseLexica(String image) {
        this.image = image;  
     }
}
