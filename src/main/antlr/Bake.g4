// define a grammar called Hello
grammar Bake;
larder   : ingredientType+;

// eg >> (SRFl - self raising flour, g)
ingredientType: OP INGREDIENT_CODE '-' .* ',' .* CL;

OP : '(';

CL : ')';

INGREDIENT_CODE: [A-Z] [A-Za-z] [A-Za-z] [A-Za-z] ;

//ID  : [a-zA-Z]+ ;
WS  : [ \t\r\n]+ -> skip ;

STRING
   : '"' (ESC | SAFECODEPOINT)* '"'
   ;

fragment ESC
   : '\\' (["\\/bfnrt] | UNICODE)
   ;

fragment UNICODE
   : 'u' HEX HEX HEX HEX
   ;

fragment SAFECODEPOINT
   : ~ ["\\\u0000-\u001F]
   ;

fragment HEX
   : [0-9a-fA-F]
   ;
