# -*- coding: UTF-8 -*-

TokenTypeNumber = 0
TokenTypeBracketLeft = 1
TokenTypeBracketRight = 2
TokenTypeOpAnd = 3
TokenTypeOpOr = 4
TokenTypeOpNot = 5


class CustomToken(object):
    def __init__(self, type, value):
        self.type = type
        self.value = value

    def __str__(self):
        return "{}".format(self.value)


class Tokenizer(object):
    def __init__(self, s):
        self.source_code = s
        self.tokens = self.tokenize(s)
        self.index = -1
        self.iter_index = 0

    def next_token(self):
        self.index += 1

    def lookahead(self):
        if self.index >= len(self.tokens):
            return None

        return self.tokens[self.index]

    def match_token(self, token):
        la = self.lookahead()
        if la.type != token.type or la.value != token.value:
            raise Exception("syntax err: expect {}, found {}".format(la, token))
        self.next_token()

    def match_bracket_right(self):
        br = CustomToken(TokenTypeBracketRight, ")")
        self.match_token(br)

    def tokenize(self, s):
        if s == 0:
            return []

        tokens = []
        i = 0

        while i < len(s):
            x = s[i]
            if x.isspace():
                pass
            elif '0' <= x <= '9':
                n = 0
                while '0' <= x <= '9':
                    n = n * 10 + int(x)
                    i += 1
                    if i >= len(s):
                        break
                    x = s[i]

                i -= 1

                t = CustomToken(0, n)
                tokens.append(t)
            elif x == "(":
                t = CustomToken(1, x)
                tokens.append(t)
            elif x == ')':
                t = CustomToken(2, x)
                tokens.append(t)
            elif x == 'A' or x == 'a':
                and_str = ""
                and_str += x
                i += 1
                while i < len(s):
                    x = s[i]
                    if 'A' <= x <= 'z':
                        and_str += x
                        i += 1
                    else:
                        break

                if and_str.lower() != 'and':
                    raise Exception("syntax err: and expected")

                t = CustomToken(3, and_str)
                tokens.append(t)
                i -= 1
            elif x == 'O' or x == 'o':
                or_str = ""
                or_str += x
                i += 1
                while i < len(s):
                    x = s[i]
                    if 'A' <= x <= 'z':
                        or_str += x
                        i += 1
                    else:
                        break

                if or_str.lower() != 'or':
                    raise Exception("syntax err: or expected")

                t = CustomToken(4, or_str)
                tokens.append(t)
                i -= 1
            elif x == '!':
                t = CustomToken(TokenTypeOpNot, x)
                tokens.append(t)
            else:
                raise Exception("syntax err: x={}".format(x))

            i += 1
        return tokens

    def __iter__(self):
        return self

    def next(self):
        while self.iter_index < len(self.tokens):
            x = self.iter_index
            self.iter_index += 1
            return self.tokens[x]

        raise StopIteration


if __name__ == "__main__":
    expr = "200 and 39 or !20 or 40"
    tokenizer = Tokenizer(expr)
    for x in tokenizer:
        print(x)


