%{
#include <ctype.h>
#include <stdio.h>

int yylex(); /* supress warning */
%}

%%
SS : S
  ;

S : C C
  ;


C : 'c' C
  | 'd'
  ;

%%
yylex() {
  int c;
  while( (c = getchar()) == ' ' );

  return c;
}
