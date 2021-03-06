(function(){function r(e,n,t){function o(i,f){if(!n[i]){if(!e[i]){var c="function"==typeof require&&require;if(!f&&c)return c(i,!0);if(u)return u(i,!0);var a=new Error("Cannot find module '"+i+"'");throw a.code="MODULE_NOT_FOUND",a}var p=n[i]={exports:{}};e[i][0].call(p.exports,function(r){var n=e[i][1][r];return o(n||r)},p,p.exports,r,e,n,t)}return n[i].exports}for(var u="function"==typeof require&&require,i=0;i<t.length;i++)o(t[i]);return o}return r})()({1:[function(require,module,exports){


const math = require('math')

const m1 = require('./module1')
const m2 = require('./module2')

m1.fun()
m2.fun()
console.log(math.add(100,200))

},{"./module1":2,"./module2":3,"math":4}],2:[function(require,module,exports){
let data = '我的模块1'
exports.fun = function(){
    console.log(data)
}
},{}],3:[function(require,module,exports){
let data = '我的模块2'
module.exports.fun = function(){
    console.log(data)
}
},{}],4:[function(require,module,exports){
(function() {
  if (typeof module !== "undefined" && module !== null) {
    module.exports && (module.exports = Math);
  }
  /**
   * @return wheter a Number x, has the same sign as another Number, y.
   * @example
   * Math.samesign(1,2)
   * //-> true
   * Math.samesign(-3, 4)
   * //-> false
   * @test
   * Math.samesign(5, 6)
   * //-> false
   * Math.samesign(-7, -8)
   * //-> true
   */
  Math.samesign = function(x, y) {
    return (x >= 0) !== (y < 0);
  };
  /**
   * @return {Number} a copy of Number x with the same sign of Number y.
   * @example
   * Math.copysign(1, -2)
   * //-> -1
   * @test
   * Math.copysign(-3, 4)
   * //-> 3
   * Math.copysign(5, 6)
   * //-> 5
   * Math.copysign(-7, -8)
   * //-> -7
   */
  Math.copysign = function(x, y) {
    if (Math.samesign(x, y)) {
      return x;
    } else {
      return -x;
    }
  };
  /**
   * @return {Number} sum of two Numbers.
   * @example
   * Math.add(1, 2)
   * //-> 3
   * @test
   * Math.add('three', 4)
   * //-> NaN
   */
  Math.add = function(a, b) {
    return (+a) + (+b);
  };
  /**
   * @return {Number} sum of an Array of Numbers.
   * @example
   * Math.sum([1, 2, 3])
   * //-> 6
   */
  Math.sum = function(nums) {
    return nums.reduce(Math.add);
  };
  /**
   * @return {Number} product of two Numbers.
   * @example
   * Math.(2, 3)
   * //-> 6
   */
  Math.mul = function(a, b) {
    return a * b;
  };
  /**
   * @return {Number} product of an Array of Numbers.
   * @example
   * Math.prod(2, 3, 4)
   * //-> 24
   */
  Math.prod = function(nums) {
    return nums.reduce(Math.mul);
  };
  /**
   * @return {Number} factorial of a Number.
   * @example
   * Math.factorial(4)
   * //-> 24
   * @test
   * Math.factorial(3)
   * //-> 6
   * Math.factorial(2)
   * //-> 2
   * Math.factorial(1)
   * //-> 1
   * Math.factorial(0)
   * //-> 1
   * Math.factorial(-1)
   * //-> Infinity
   */
  Math.factorial = function(n) {
    var _i, _results;
    if (n < 0) {
      return Infinity;
    } else if (n === 0) {
      return 1;
    } else {
      return Math.prod.apply(null, (function() {
        _results = [];
        for (var _i = 1; 1 <= n ? _i <= n : _i >= n; 1 <= n ? _i++ : _i--){ _results.push(_i); }
        return _results;
      }).apply(this, arguments));
    }
  };
  /**
   * Greatest Common Multipler
   * @return {Number} greatest common multipler of two Numbers.
   * @example
   * Math.gcd(493, 289)
   * //-> 17
   * @test
   * Math.gcd(493, -289)
   * //-> 17
   */
  Math.gcd = function(a, b) {
    var _ref;
    while (b) {
      _ref = [b, a % b], a = _ref[0], b = _ref[1];
    }
    return a;
  };
  /**
   * Least Common Multiplier
   * @return {Number} least common multiplier of two numbers.
   * @example
   * Math.lcm(4, 12)
   * //-> 12
   * @test
   * Math.lcm(6, 7)
   * //-> 42
   */
  Math.lcm = function(a, b) {
    return a / Math.gcd(a, b) * b;
  };
}).call(this);

},{}]},{},[1]);
