#This is a summary of the book Programming in Scala by Odersky, Spoon & Venners

# Chapter 2: First steps in Scala
Function definitions starts with `def`, the compiler doesn't infer function parameter types, therefore they need to 
be declared. The _Unit_ type is similar to Java's _void_. If a function literal consists of one statement that takes 
a single argument, you need not explicitly name and specify the argument:
```scala
args.foreach(println)
// Is equivalent to:
args.foreach(x => println(x))
```
If there is more than one parameter on the function literal or you define the type of the parameter, then the 
parameter declaration needs to be enclosed by parentheses.

# Chapter 3: Next Steps in Scala
Scala transforms calls without method name like this `"hello"(i)` is transformed into `"hello".apply(i)`, but this 
only work if the object type define the `apply` method. When an assignment is made to a variable to which parentheses
 and one or more arguments have been applied, the compiler will transform that into an invocation of an update method
  like `arrayTest(2) = "hello"` is equivalent to `arrayTest.update(2, "hello")`.
The preferred way to define an array si `val array = Array("hello", "World")` which as seen is equivalent to `val 
array = Array.apply("hello", "World")`. `scala.List` object is immutable, it defines a method `:::` to concatenate 
two lists: `list1 ::: list2` return a new list with the values of list1 and list2. It also defines a method  `::` 
that prepend an element to the list and returns a new one `1 :: list1`.
Tuples are defined by enclosing values in parentheses `val tuple = (1, "Test")`, to access elements inside it use `
._<index>` for example: `tuple._1` returns the Integer 1. Sets are the root trait from where two type of sets are 
derived, mutable and immutable.  There is a `scala.collection.mutable.Hashset` and a `scala.collection.immutable
.Hashset` analogous for Maps.

# Chapter 4: Classes and Objects
Results of type _Unit_ in a method can remove the equals symbol and return type: `def something(...){...}`, this 
means that even if the last statement of the function has a result, it's value its converted to _Unit_ Semicolon is 
needed if you put more than a sentence in a single line. Line ending is treated as a semicolon except:
    
    1 The line in question ends in a word that would not be legal as the end of a statement, such as a period or an 
    infix operator.
    2 The next line begins with a word that cannot start a statement.
    3 The line ends while inside parentheses (...) or brackets [...], because these cannot contain multiple 
    statements anyway.

There is no static classes in scala, instead there are singleton objects marked with the word _Object_. When a 
singleton object shares the same name with a class, it is called that class’s _companion object_ which has to be 
defined in the same source file. A singleton object that has a different name than its companion object is called 
standalone object.
The trait _Application_ can't use command line arguments or if the program is multithread. Only used for simple and 
single threaded programs.

# Chapter 5: Basic Types and Operations
_Boolean_, _Byte_, _Short_, _Char_, _Int_, _Long_, _Float_ and _Double_ belongs to the package _scala.lang_, _String_
 belongs to _java.lang_, and both are automatically imported into every scala source file. Scala accepts literals 
 like in java, for example for Int type we can define `val a =0X4F`, same with octal (numbers beginning with 0) or 
 decimal base. If the number ends with _L_ or _l_ is a long, _f_ or _F_ for Float, and _d_ or _D_ for Double. To 
 define char, enclose the unicode character in single quotes, or an octal or hexadecimal number preceded by a _\_. 
 String literals are like in java: `val test="\\\"\'" //string value = \"'`, you can avoid escaping the characters by
  defining the string inside three double quotes: `println("""Some \ ' " characters""")`.Symbols are defined like 
  `'<symbol>`, writting the literal symbol twice would refer to the same object. 

Operators in basic types are method invocations. Most of the methods are overloaded, for example there is an add 
operation in `Int` that takes a Long as a parameter. So in `1 + 2`, `+` is a method, not an operator. Other methods 
can be used in operator notation, such as `"abcda" indexOf "b" `. For more than one argument passed to the method in 
operator notation, enclose them in parentheses `"abcda" indexOf ("b", 5)`. The operators can be prefixed `-7` or 
postfixed `3 toLong`, both are unary, they only take one operand. 
The only identifiers that can be used as *prefix* operators are `+, -, !, ~` but the resulted method name would be 
`unary_<method>`. For example `!var` would result in a method named `unary_!`.
Postfix operators doesn't need parentheses, because they take no arguments. In scala the convention is that if the 
method has side effects you use parentheses `println()`. 
For _*, +, -, /_ we can use infix operator notation like `1+2`. operands `&&, !, ||, <, >, <=, >=` are similar to 
java, in addition `&&, ||` are shortcircuited. 
Bitwise operations:
    1. `~`: inverts all bits in its operand
    2. `^`: Indentical bits yields 0
    3. `&`: bitwise ands all bits 0010 & 0001 = 0000
    4. `|`: bitwise or all bits 0010 | 0001 = 0011
Shift methods:
    1. `>>`: Shift right -1 >> 31 = -1
    2. `<<`: Shift left 1 << 2 = 4
    3. `>>>`: unsigned shift right -1 >>> 31 = 1
Object equality can be compared using `==` or `!=`, and it applies for all objects `List(1,2,3) == List(1,2,3) //this
 is true`, even different types `1 == 1.0 //true` Operators has different precedence as in `2+2*7 // = 14`, the 
 operator precedence is as follows:
```scala
(all other special characters) */%
+-
:
=! <> &
ˆ
|
(all letters)
(all assignment operators)
```
Associativity of the method is left to right except if the operand starts with `:` which the associativity is right 
to left. For each basic type described in this chapter, there is also a “rich wrapper” that provides several 
additional methods.

# Chapter 6: Functional Objects
If a class doesn't define a body, there is no need to put curly braces. In Java, classes have constructors, which can 
take parameters, whereas in Scala, classes can take parameters directly. To override a class method, put the keyword 
_override_ before the _def_ keyword.
Objects can use the `require(x:Boolean)` method of the _Predef_ class to check for preconditions, for example in a 
constructor:
```scala
class Rational(n: Int, d: Int) {
    require(d != 0)
}
```
To be able to access constructor parameters of an object by other object, we need to convert those members into fields
 like this:
```scala
class Rational(n: Int, d: Int) {
    require(d != 0)
    val num:Int = n
    val den:Int = d
}
```  
The `this` keyword has the same use than in Java, it refers to the class itself. Auxiliary constructors can be 
defined using the notation `def this(...)`. Auxiliary constructor must invoke another constructor of the same class 
as its first action, it can call the primary constructor (the one defined in the class signature), or another 
auxiliary constructor. The Scala compiler will place the code for the initializers of a class fields into 
the primary constructor in the order in which they appear in the source code. Class members can be defined as 
`privated`.
In scala the convention to give an identifier to a constant is merely that the first character should be upper case 
like in _PiNumber_. Operator identifiers are transformed into legal Java identifiers with embedded $ characters like 
`:->` becomes `$colon$minus$greater`. It is also possible to use any string as an identifier as long as it is 
enclosed with back ticks.
Scala’s process of overloaded method resolution is very similar to Java's. Implicit conversions serves to convert a 
type to another, and for an implicit conversion to work, it needs to be in scope (for example outside a class 
declaration but reachable in another part of the code). Example of implicit definition:
```scala
implicit def intToRational(x: Int) = new Rational(x)
```

# Chapter 7: Built-In Control Structures
Scala control structures `if`, `while`, `for`, `try` and `match` results in a value. Given this, is possible to 
initialize a value (not a variable) like:
```scala
val filename =
    if (!args.isEmpty) args(0)
    else "default.txt"
```
Loops in scala results in an Unit value `()`, which is the same result yield in a reassignment to a var, so you 
can't do this:
```scala
while((aux=readLine())!=null){...}
```
In for loops, you can add filtering like this:
```scala
for (
       file <- filesHere
       if file.getName.endsWith(".scala"); //Note the semicolon to separate if expressions
       line <- fileLines(file) //This results in an inner loop.
       if line.trim.matches(pattern)
     ) println(file +": "+ line.trim)
```
It is possible to produce a new collection as result of a for loop using the `yield` keyword:
```scala
def scalaFiles =
     for {
       file <- filesHere
       if file.getName.endsWith(".scala")
     } yield file
```
The type of the resulting collection is based on the kind of collections processed in the iteration clauses. The 
syntax of the expresion is `for clauses yield body`.
In scala, exception returns a result type, to handle the exceptions in scala you use pattern matching like this, 
however Scala doesn't require to handle checked exceptions:
```scala
try {
    val f = new FileReader("input.txt")
} catch {
    case ex: FileNotFoundException => // Handle missing file
    case ex: IOException => // Handle other I/O error
} finally{
    f.close()
}
```
The structure _try, catch, finally_ yields a value, which would be one of these three: 

* The one of the expression
* The catch clause if executed
* No value if an exception is thrown (even the finally result is dropped if this is the case)

_case_ structures in scala are like java _switch_, but scala allows for any type of constant to be used and returns a
value. Break is implicit in scala, so there is no need to add it at the end of the case statement. 
In scala you can define a variable in an inner scope that has the same name as a variable in an outer scope:
```scala
val a = 1; {
  val a = 2 // Compiles just fine
  println(a) 
}
println(a)
```

#Chapter 8: Functions and Closures
Scala allows to define functions inside functions that are only visible within those functions (like if they were 
private):
```scala
def funcA={
    def funcB(){
        ...
    }
    funcB
}
```
Local defined functions can access the parameters of the enclosing functions. 
Scala allows first-class functions, function values are objects, so you can store them in variables if you like:
`var increase = (x: Int) => x + 1` 
Every function value is an instance of some class that extends one of several FunctionN traits in package scala.
Scala compiler has a feature called target typing which allows the programmer to not define the type of the 
parameters, if the function is used with a typed parameter after the definition, the compiler can infer the type of 
the parameters in the function definition.
```scala
someNumbers.filter(x => x > 0)
```
If a parameter only appears once in the function literal definition, we can substitute it with a `_`, for example"
 ```scala
 val sumThree = (_:Int) + (_:Int)
 ```
 The first underscore represents the first parameter, the second underscore the second parameter, and so on. 
 It is also possible to replace an entire parameter list with an underscore `someNumbers.foreach(println _)`, when 
 you use an underscore in this way, you are writing a partially applied function.A partially applied function is an 
 expression in which you don’t supply all of the arguments needed by the function. For example
 ```scala
 def sum(a:Int,b:Int,c:Int) = a+b+c
 val a = sum _
 a(1,2,3) //res = 6
 ```
 In the example, the function value is an instance of a class generated automatically by the Scala compiler from `sum
  _`, the partially applied function expression. The class generated by the compiler has an apply method that takes 
  three arguments. The generated class’s apply method takes three arguments because three is the number of arguments 
 missing in the `sum _` expression.
 If you are writing a partially applied function expression in which you leave off all parameters, you can express it
  more concisely by leaving off the underscore if a function is required at that point in the code.
 ```scala
  someNumbers.foreach(println)
  ```
It is possible to do also something like this:
```scala
var some = 3
val addSome = (x:Int) => x + some
```
The some literal is called free variable, and needs to be on the scope of the function for it to compile. This type 
of functions are called closures. Changes made by a closure to a captured variable are visible outside the closure.
Given the case:
```scala
def makeIncreaser(more: Int) = (x: Int) => x + more
```
The value of more refers to the instance used when the closure was created:
```scala
val inc1 = makeIncreaser(1) //Increases 1
val inc10 = makeIncreaser(10) //Increases 10
```
Scala also has a syntax for repeated (var args alike) parameters:
```scala
def echo(args: String*) = for (arg <- args) println(arg)
```
If we pass an array to the previous function it will fail, to pass each element of _arr_ as its own argument, we need 
to write it like this
```scala
val arr = Array("a", "b")
echo(arr: _*)
```
Functions which calls themselves as their last action, are called tail recursive, the Scala compiler detects tail 
recursion and replaces it with a jump back to the beginning of the function, after updating the function parameters 
with the new values, although this optimization can be deactivated by passing `-g:notailcalls` argument to the scala 
shell or to the scalac compiler. Scala only optimizes directly recursive calls back to the same function making the call. 

#Chapter 9: Control Abstraction
Scala gives the programmer the ability to create his own control structures by the higher order functions (functions 
that accepts other functions as parameters). An example of a higher order function in scala is below:
```scala
 def filesMatching(query: String, matcher: (String, String) => Boolean) = {
     for (file <- filesHere; if matcher(file.getName, query)) yield file
 }
 ```
Higher order functions also helps to reduce client code, for example the `exists` method in the collections avois 
duplicating methods in the clients to check if there is a negative number, odd number, etc.. the implementation for 
the lates would be: 
`randomCollection.exists(_ % 2 == 1)`.
To make scala feel like it provides native language support, we can use a technique called currying. A curried function is applied to multiple argument lists, instead of just one. For example a curried sum of two numbers:
`def curriedSum(x: Int)(y: Int) = x + y`
The first function invocation takes a single Int parameter named x, and returns a function value for the second function.
To get a reference to this second function, we can use partially applied functions like this:
`val onePlus = curriedSum(1)_`
With higher order functions you can define your own control structures, for example: open a file, operate on it, and 
close the file:
```scala
def withPrintWriter(file: File, op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try { op(writer) } finally {  writer.close() }
}
```
If curly braces are used instead of parentheses to enclose the function parameters, it looks more like a built-in 
control structure, but this only works when there is just a single parameter, although with currying, you can rewrite
 the call to the previous function like this:
 ```scala
 def withPrintWriter(file: File)(op: PrintWriter => Unit) {
     val writer = new PrintWriter(file)
     try { op(writer) } finally {  writer.close() }
 }
 val file = new File("./test.txt")
 withPrintWriter(file){
    writer => writer.println(new java.util.Date)
 }
 ```
If you want to implement something more like if or while, but there is no value to pass into the code 
between the curly braces, you can use named parameters. For example this:
```scala
var assertionsEnabled = true
def myAssert(predicate: () => Boolean) = if (assertionsEnabled && !predicate()) throw new AssertionError

//Invokation
myAssert(() => 5 > 3)
```
can be replaced by:
```scala
var assertionsEnabled = true
def myAssert(predicate: => Boolean) = if (assertionsEnabled && !predicate()) throw new AssertionError

//Invokation
myAssert(5 > 3)
```

#Chapter 10: Composition and Inheritance
The `abstract` keyword in scala serves the same purpose than in java, although an abstract method in an abstract class 
don't need the abstract modifier like in java. Methods with implementation are called concrete. In scala 
_parameterless methods_ are the ones without parentheses like `def method:...`, the ones like `def method():...` are 
called empty-paren methods. By convention in scala parameterless methods are used when mutable state of the enclosing
object is not changed inside the method, empty parentheses are appropriate for methods that has side effects because otherwise invocations of that method would look like a field selection.
 `scala.AnyRef` is the equivalent of `java.lang.Object` and in Scala, fields and methods belong to the same namespace. This makes it possible for a field to override a parameterless method therefore you can't define a field and a method with the 
same name in a class. 
In scala you can use modifiers like _var, val, override, protected and private_ to the primary constructor arguments
i.e.:
`class example(private val test:Int = 5){...}`
the _override_ keyword is needed if a concrete member of a parent class is overriden, and optional if it is an 
abstract member. Scala like any OOP provides polymorphism, although the method invoked in a class is determined at 
runtime depending on the actual type of the instance. the _final_ modyfier as in Java indicates that a method can't 
be overriden or a class can be subclassed. 
  
#Chapter 11: Scala's hierarchy
Every class in scala extends from `Any`, every class has `Null` and `Nothing` as subclasses. `Any` contains methods like `==`, `!=` (both final), `equals`, `hashcode` and `toString`. From `Any` there is two subclasses and one marker trait: 

* `AnyVal`: For built in Values from where Byte, Short, Char, Int, Long, Float, Double, Boolean, and Unit extends, The first eight are instantiated as Java primitives and writen as literals in scala. i.e. 42 is an Int. You cannot create instances of this classes using `new` (value classes are declared final and abstract and cannot be instantiated). Implicit conversions are available from Byte to Short, from short to Int, from Char to Int from Int to Long, from Long to Float and from Float to Double. There are also implicit conversions from the classes to their 'Richer' subtypes, like from Int to RichInt, which add functionality to the classes.
* `AnyRef`: For all reference classes, it is equivalent to the java.lang.Object.
* `ScalaObject`: Marker trait that contains a single method, named $tag, which is used internally to speed up pattern matching.

In scala `==` is implemented as object equality, to use reference equality use `eq` or `ne` instead. `Nothing` is a subclass of every class in scala. It can be used to mark abnormal termination:
```scala
def error(message: String): Nothing = throw new RuntimeException(message)
def divide(x: Int, y: Int): Int =
    if (y != 0) x / y
    else error("can't divide by zero")
```
In the previous Example, `Nothing` is still an Integer, so the return type for both branches is Int.
 
#Chapter 12: Traits
