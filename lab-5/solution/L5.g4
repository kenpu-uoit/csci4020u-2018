grammar L5;

@members {
  int toInt(String s) {
    try {
      return Integer.parseInt(s);
    } catch(Exception e) {
      return 0;
    }
  }
}

start : expr {System.out.println($expr.v);};

expr returns [int v]
    : x=expr '+' y=expr { $v = $x.v + $y.v; }
    | x=expr '-' y=expr { $v = $x.v - $y.v; }
    | '(' x=expr ')'    { $v = $x.v; }
    | NUMBER            { $v = toInt($NUMBER.text); }
    ;

NUMBER : [0-9]+;
WS : [ \t\r\n]+ -> skip;


