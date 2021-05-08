# -*- coding: UTF-8 -*-

import lexer


class Parser(object):
    def __init__(self, tokenizer):
        self.tokenizer = tokenizer
        self.post_suffix_expr = ""

    def translate(self):
        self.tokenizer.next_token()
        self.expr()
        return self.post_suffix_expr

    def expr(self):
        """
            expr = expr or term | term
            term = term and opnot | opnot
            opnot = ! opnot | factor
            factor = number | ( expr )

            expr = term expr_r
            expr_r = or term expr_r | ε

            term = opnot term_r
            term_r = and opnot term_r | ε

        """

        self.term()
        self.expr_r()

    def expr_r(self):
        token = self.tokenizer.lookahead()
        if token and token.value == "or":
            self.tokenizer.match_token(self.tokenizer.lookahead())
            self.term()
            self.post_suffix_expr += " or "

            self.expr_r()

    def term(self):
        self.op_not()
        self.term_r()

    def term_r(self):
        token = self.tokenizer.lookahead()
        if token and token.value == "and":
            tz.match_token(self.tokenizer.lookahead())
            self.op_not()
            self.post_suffix_expr += " and "

            self.term_r()

    def op_not(self):
        token = self.tokenizer.lookahead()
        if token.value == "!":
            tz.match_token(self.tokenizer.lookahead())
            self.op_not()
            self.post_suffix_expr += "!"
        else:
            self.factor()

    def factor(self):
        token = self.tokenizer.lookahead()
        if token and token.type == lexer.TokenTypeNumber:
            self.tokenizer.match_token(token)
            self.post_suffix_expr += " {} ".format(token.value)

        elif token and token.type == lexer.TokenTypeBracketLeft:
            self.tokenizer.match_token(token)
            self.expr()
            self.tokenizer.match_bracket_right()

        else:
            raise Exception("syntax err: {} found".format(token))


if __name__ == "__main__":
    s = "20 and (!(!20 and !!30) or 40)"

    tz = lexer.Tokenizer(s)
    parser = Parser(tz)
    post_suffix_expr = parser.translate()

    print(post_suffix_expr)
