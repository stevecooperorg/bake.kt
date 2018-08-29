// define a grammar called Hello
grammar Bake;

cookbook: (recipe | ingredientType)+;

larder   : ingredientType+;

// eg >> (SRFl - self raising flour, g)
ingredientType: INGREDIENT code=INGREDIENT_CODE name=STRING units=STRING;

recipe: RECIPE ID pot+;

pot: POT_ID action;

action: INGREDIENT_AMOUNT* ID;

INGREDIENT: 'INGREDIENT';

INGREDIENT_AMOUNT: [0-9]+ INGREDIENT_CODE;

RECIPE: 'RECIPE';

COMMA: ',';

DASH: '-';

OP : '[';

CL : ']';

INGREDIENT_CODE: [A-Z] [A-Za-z] [A-Za-z] [A-Za-z]? ;

POT_ID: '.' ID;

ID  : [a-zA-Z] [a-zA-Z-]* ;
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
