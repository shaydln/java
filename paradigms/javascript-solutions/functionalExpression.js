"use strict";
const baseOperation = f => (...args) => (...val) => f(...args.map(arg => arg(...val)));
const add = baseOperation((a, b) => a + b);
const subtract = baseOperation((a, b) => a - b);
const divide = baseOperation((a, b) => a / b);
const multiply = baseOperation((a, b) => a * b);
const negate = baseOperation(a => -a);
const sinh = baseOperation(a => Math.sinh(a));
const cosh = baseOperation(a => Math.cosh(a));
const cnst = (num) => () => num;
const one = cnst(1);
const two = cnst(2);
const variable = (letter) => (x, y, z) => {
        switch (letter) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
            default:
                console.log("Unsupported symbol")
        }
};
// Тестовая программа должна вычислять выражение x2−2x+1, для x от 0 до 10.
let test = add(
    subtract(
      multiply(
          variable('x'),
          variable('x')
      ),
      multiply(
          cnst(2),
          variable('x')
      )
    ),
    cnst(1)
);
for (let i = 0; i <= 10; i++) {
    console.log(test(i, 0, 0));
}