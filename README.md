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
neede if you put more than a sentence in a single line. Line ending is treated as a semicolon except:
    
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
The only identifiers that can be used as prefix 
operators are `+, -, !, ~` but the resulted method name would be `unary_<method>`. For example `!var` would result
 in a method named `unary_!`.
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
```
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
If a class doesn't define a body, there is no need to put curly braces In Java, classes have constructors, which can 
take parameters, whereas in Scala, classes can take parameters directly. To override a class method, put the keyword 
_override_ before the _def_ keyword.
Objects can use the `require(x:Boolean)` method of the _Predef_ class to check for preconditions, for example in a 
constructor:
```
class Rational(n: Int, d: Int) {
    require(d != 0)
}
```
To be able to access constructor parameters of an object by other object, we need to convert those members into fields
 like this:
```
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
declaration but reachable in another part of the code).

# Chapter 7: Built-In Control Structures
Scala control structures `if`, `while`, `for`, `try` and `match` results in a value. Given this, is possible to 
initialize a value (not a variable) like:
```
val filename =
    if (!args.isEmpty) args(0)
    else "default.txt"
```
Loops in scala results in an Unit value `()`, which is the same result yield in a reassignment to a var, so you 
can't do this:
```
while((aux=readLine())!=null){...}
```
In for loops, you can add filtering like this:
```
for (
       file <- filesHere
       if file.getName.endsWith(".scala"); //Note the semicolon to separate if expressions
       line <- fileLines(file) //This results in an inner loop.
       if line.trim.matches(pattern)
     ) println(file +": "+ line.trim)
```
It is possible to produce a new collection as result of a for loop using the `yield` keyword:
```
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
```
try {
    val f = new FileReader("input.txt")
} catch {
    case ex: FileNotFoundException => // Handle missing file
    case ex: IOException => // Handle other I/O error
} finally{
    f.close()
}
```
The structure _try, catch, finally_ yields a value, which would be the one of the expression, the catch clause if 
executed, and no value if an exception is thrown (even the finally result is dropped if this is the case).
_case_ structures in scala are like java _switch_, but scala allows for any type of constant to be used and returns a
value. Break is implicit in scala, so there is no need to add it at the end of the case statement. 
In scala you can define a variable in an inner scope that has the same name as a variable in an outer scope:
```
val a = 1; {
  val a = 2 // Compiles just fine
  println(a) 
}
println(a)
```

#Chapter 8: Functions and Closures
Scala allows to define functions inside functions that are only visible within those functions (like if they were 
private):
```
def funcA={
    def funcB(){
        ...
    }
    funcB
}
```
Local defined functions can access the parameters of the enclosing functions. 
Scala allows first-class functions, function values are objects, so you can store them in variables if you like:
`var increase = (x: Int) => x + 1` Every function value is an instance of some class that extends one of several 
FunctionN traits in package scala.