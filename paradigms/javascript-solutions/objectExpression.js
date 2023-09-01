'use strict'
function Const(value) {
    this.value = value;
}
Const.prototype.evaluate = function() {
    return this.value;
}
Const.prototype.toString = function() {
    return this.value.toString();
}
Const.prototype.prefix = function() {
    return this.value.toString();
}
function Variable(letter) {
    this.letter = letter;
}
Variable.prototype.evaluate = function (x, y, z) {
    switch (this.letter) {
        case 'x': return x;
        case 'y': return y;
        case 'z': return z;
        default: console.log("Unsupported symbol")
    }
}
Variable.prototype.toString = function () {
    return this.letter;
}
Variable.prototype.prefix = function () {
    return this.letter;
}
const averageFunc = {
    args:[],
    operation: () => {},
    type:1,
    evaluate(x, y, z) {
        return this.operation.apply(null, this.args.map(arg => arg.evaluate(x, y, z)));
    },
    toString() {
        return this.args.join(" ") + " " + this.type;
    },
    prefix() {
        return "(" + this.type + " " + this.args.map(arg => arg.prefix()).join(" ") + ")";
    }
}
function createOperation(type, func, ...args) {
    let operation = Object.create(averageFunc);
    operation.args = args;
    operation.type = type;
    operation.operation = func;
    return operation;
}
const Add = function (left, right) {
    return createOperation("+", (a, b) => a + b, left, right);
}
const Divide = function (left, right) {
    return createOperation("/", (a, b) => a / b, left, right);
}
const Subtract = function (left, right) {
    return createOperation("-", (a, b) => a - b, left, right);
}
const Multiply = function (left, right) {
    return createOperation("*", (a, b) => a * b, left, right);
}
const ArcTan2 = function (left, right) {
    return createOperation("atan2", (a, b) => Math.atan2(a, b), left, right);
}
const Negate = function (val) {
    return createOperation("negate", val => -val, val);
}
const ArcTan = function (val) {
    return createOperation("atan", val => Math.atan(val), val);
}
const Sum = function (...val) {
    return createOperation("sum", (...val) => val.map((a) => a).reduce((acc, number) => acc + number, 0), ...val);
}
const Avg = function (...val) {
    return createOperation("avg", (...val) => val.map((a) => a).reduce((acc, number) => acc + number, 0) / val.length, ...val);
}
const vars = {'x':'x', 'y':'y', 'z':'z'};
const binary = {'+':Add, '-':Subtract, '*':Multiply, '/':Divide, 'atan2':ArcTan2};
const multi = {'sum':Sum, 'avg':Avg};
const unary = {'negate':Negate, 'atan':ArcTan};
function ParseError(message) {
    this.message = message;
    Error.call(this, this.message);
}
function parsePrefixExpr(expression, isPrefix) {
    const tokens = expression.trim().split(/(\(|\)|\s+)/).filter(token => !!token.trim());
    if (isPrefix) {
        tokens.reverse();
    }
    let i = 0;
    let answer = recursiveParse();
    if (i !== tokens.length-1) {
        throw new ParseError("Incorrect expression");
    }
    return answer;
    function recursiveParse() {
        for (; i < tokens.length; i++) {
            if (tokens[i] === '(') {
                let operation = tokens[++i];
                let stack = [];
                while (tokens[++i] !== ')' && i < tokens.length) {
                    stack.push(recursiveParse());
                }
                if (operation in binary) {
                    if (stack.length !== 2) {
                        throw new ParseError("Not enough operands for binary operation");
                    }
                    let right = stack.pop();
                    let left = stack.pop();
                    stack.push(binary[operation](isPrefix ? right : left, isPrefix ? left : right));
                } else if (operation in unary) {
                    if (stack.length !== 1) {
                        throw new ParseError("Not enough operands for unary operation");
                    }
                    stack.push(unary[operation](stack.pop()));
                } else if (operation in multi) {
                    stack.push(multi[operation](...stack));
                    stack = stack.splice(stack.length - 1,1);
                } else {
                    throw new ParseError("Unsupported operation " + operation);
                }
                return stack[0];
            } else if (tokens[i] in vars) {
                return new Variable(tokens[i]);
            } else if (!isNaN(tokens[i])) {
                return new Const(Number(tokens[i]));
            } else {
                throw new ParseError("Unsupported symbol");
            }
        }
    }
}
function parse(expression) {
    let stack = []
    let tokens = expression.trim().split(/\s+/);
    for (const el of tokens) {
        if (el in vars) {
            stack.push(new Variable(el));
        } else if (el in binary) {
            let right = stack.pop();
            let left = stack.pop();
            stack.push(binary[el](left, right));
        } else if (el in unary) {
            const val = stack.pop();
            stack.push(unary[el](val));
        } else {
            stack.push(new Const(Number(el)));
        }
    }
    return stack[0];
}
function parsePrefix(expression) {
    return parsePrefixExpr(expression, false);
}
