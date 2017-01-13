# This is a summary of the book Programming in Scala by Odersky, Spoon & Venners

## Table of contents:

1. [Chapter 1: A Scalable Language](#Chapter1)
2. [Chapter 2: First steps in Scala](#Chapter2)
3. [Chapter 3: Next Steps in Scala](#Chapter3)
4. [Chapter 4: Classes and Objects](#Chapter4)
5. [Chapter 5: Basic Types and Operations](#Chapter5)
6. [Chapter 6: Functional Objects](#Chapter6)
7. [Chapter 7: Built-In Control Structures](#Chapter7)
8. [Chapter 8: Functions and Closures](#Chapter8)
9. [Chapter 9: Control Abstraction](#Chapter9)
10. [Chapter 10: Composition and Inheritance](#Chapter10)
11. [Chapter 11: Scala's hierarchy](#Chapter11)
12. [Chapter 12: Traits](#Chapter12)
13. [Chapter 13: Packages and Imports](#Chapter13)
14. [Chapter 14: Assertions and Unit Testing](#Chapter14)
15. [Chapter 15: Case Classes and Pattern Matching](#Chapter15)
16. [Chapter 16: Working with Lists](#Chapter16)
17. [Chapter 17: Collections](#Chapter17)
18. [Chapter 18:Stateful Objects](#Chapter18)
19. [Chapter 19: Type Parameterization](#Chapter19)
20. [Chapter 20: Abstract Members](#Chapter20)
21. [Chapter 21: Implicit Conversions and Parameters](#Chapter21)
22. [Chapter 22: Implementing Lists](#Chapter22)
23. [Chapter 23: For Expressions Revisited](#Chapter23)
24. [Chapter 24: Extractors](#Chapter24)
25. [Chapter 25: Annotations](#Chapter25)
26. [Chapter 26: Working with XML](#Chapter26)
27. [Chapter 27: Modular Programming Using Objects](#Chapter27)
28. [Chapter 28: Object Equality](#Chapter28)
29. [Chapter 29: Combining Scala and Java](#Chapter29)
30. [Chapter 30: Actors and Concurrency](#Chapter30)
31. [Chapter 31: Combinator Parsing](#Chapter31)
32. [Chapter 32: GUI Programming](#Chapter32)

# Chapter 1: A Scalable Language<a name="Chapter1"></a>
Scala is great.

# Chapter 2: First steps in Scala<a name="Chapter2"></a>
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

# Chapter 3: Next Steps in Scala<a name="Chapter3"></a>
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

# Chapter 4: Classes and Objects<a name="Chapter4"></a>
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

# Chapter 5: Basic Types and Operations<a name="Chapter5"></a>
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

# Chapter 6: Functional Objects<a name="Chapter6"></a>
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

# Chapter 7: Built-In Control Structures<a name="Chapter7"></a>
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

# Chapter 8: Functions and Closures<a name="Chapter8"></a>
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
 In the example, the function value `a` is an instance of a class generated automatically by the Scala compiler from `sum
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
def echo(args: String*) = for (elem <- args) println(elem)
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

# Chapter 9: Control Abstraction<a name="Chapter9"></a>
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

# Chapter 10: Composition and Inheritance<a name="Chapter10"></a>
The `abstract` keyword in scala serves the same purpose than in java, although an abstract method in an abstract class 
 don't need the abstract modifier like in java. Methods with implementation are called concrete. In scala 
 _parameterless methods_ are the ones without parentheses like `def method:...`, the ones like `def method():...` are 
 called empty-paren methods. By convention in scala parameterless methods are used when mutable state of the enclosing
 object is not changed inside the method, empty parentheses are appropriate for methods that has side effects because otherwise invocations of that method would look like a field selection.
 `scala.AnyRef` is the equivalent of `java.lang.Object` and in Scala, fields and methods belong to the same namespace. This makes it possible for a field to override a parameterless method therefore you can't define a field and a method with the 
 same name in a class. 
In scala you can use modifiers like _var, val, override, protected and private_ to the primary constructor arguments
 i.e.: `class example(private val test:Int = 5){...}`
 the _override_ keyword is needed if a concrete member of a parent class is overriden, and optional if it is an 
 abstract member. Scala like any OOP provides polymorphism, although the method invoked in a class is determined at 
 runtime depending on the actual type of the instance. the _final_ modyfier as in Java indicates that a method can't 
 be overriden or a class can be subclassed. 
  
# Chapter 11: Scala's hierarchy<a name="Chapter11"></a>
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

# Chapter 12: Traits<a name="Chapter12"></a>
Traits extends from `AnyRef`, and can define method implementations in the body. To use them, use the keyword `extends` (in this case the class inherits the trait superclass) or `with`: `class Frog extends Animal with hasTail with hasLegs {...}`. Traits in scala can declare fields and maintain state. You can do pretty much the same than a class with the exception of having class parameters like in `class square(side: Int){...}` and having not bounded superclasses like in the case of calling a `toString` method (the class would call the superclass toString that can be whichever class extends the trait). The `Ordered[T]` trait has a method `compare` which is similar to the java one. 
If a Trait extends a class or trait, it can only be mixed into a class that extends the same class than the trait. A trait can call to a `super` method in a method declared `abstract` because the trait is bounded dynamically:

```scala
trait Doubling extends IntQueue {
    abstract override def put(x: Int) { super.put(2 * x) } //Look at the abstract override modifier!
}
```

It is possible to have anonymous mixing classes like this: `val queue = new BasicIntQueue with Doubling with Filtering`. The order in the trait mixing is important, the traits foremost on the right take effect first. When you call a method on a class with mixins, the method in the trait furthest to the right is called first. If that method calls super, it invokes the method in the next trait to its left, and so on. When mixed, traits and classes are linearized, meaning that when you instantiate a class with new, Scala takes the class and all of its inherited classes and traits and puts them in a single, linear order. In the example before, the `super` of `Filtering` is `Doubling` which has `BasicIntQueue` as its `super`. Use cases:
 
 * If the behavior will not be reused use a class
 * If it might be reused in multiple, unrelated classes, make it a trait
 * If you plan to distribute it in compiled form, and you expect outside groups to write classes inheriting from it, you might lean towards using an abstract class
 * If efficiency is very important, lean towards using a class
 * If you still do not know, after considering the above, then start by making it as a trait
 
# Chapter 13: Packages and Imports<a name="Chapter13"></a>
Scala allows you to place classes in different packages in the same file like this:

```scala
package com{
    package main{ class TestA{...} // this is the class com.main.TestA }
    package test{ class TestB{val member= new main.TestA} //This is the class com.test.TestB }
}
```

In the example above, `TestB` can refer to the class `com.main.TestA` as `main.TestA` just like the code inside methods of a class can refer to other methods of that class without a prefix. Inner scope hide packages of the same name that are defined in an outer scope:

```scala
// In file launch.scala
package launch { class Booster3 }
// In file bobsrockets.scala
package bobsrockets {
  package navigation {
    package launch { class Booster1 }
    class MissionControl {
      val booster1 = new launch.Booster1
      val booster2 = new bobsrockets.launch.Booster2
      val booster3 = new _root_.launch.Booster3
    } 
  }
  package launch {class Booster2} 
  }
```

All scala packages belong to the package `_root_`, which can be seen in the instantiation of the Booster3 class.
Import statements in scala can appear anywhere, even inside a class declaration:

```scala
 def showFruit(fruit: Fruit) {
    import fruit._ //Imports all members of the parameter fruit
    println(name +"s are "+ color)
}
import java.util.regex
class AStarB { val pat = regex.Pattern.compile("a*b") // Accesses java.util.regex.Pattern }
```

Imports can also import packages, this is shown above with the `AStarB` class. It is also possible to give an alias to an import:
`import fruit.{Apple => McIntosh, Orange}` in the example above we can instantiate the class `fruit.Apple` or `McIntosh`. If we want to exclude a class from the import we can do `import fruit.{Pear =>_,_} //imports all members except Pear`.
Scala imports implicitly the packages `java.lang._`, `scala._` and `Predef._`. Scala supports access modifiers for members of packages, classes, or objects. In comparison with java:

* `private`: is treated similarly to java
* `protected`: Access is more restrictive, a protected member is only accessible from subclasses of the class in which the member is defined
* `public`: All non private, non protected members are public.

In addition to this, it is possible to define the scope of the modifier with a qualifier. i.e:

```scala
    package com.showing
    class ShowA{
        private [ShowA] functionShow(){...} // this is visible up to ShowA
        protected [showing] functionShowing(){...} // this is visible in the package showing and classes inside this package
        private [this] showB(){...} // visible only in the object
    }
```

A class shares all its access rights with its companion object and vice versa. Thus this is allowed:

```scala
  class Rocket {
    import Rocket.fuel
    private def canGoHomeAgain = fuel > 20
  }
  object Rocket {
    private def fuel = 10
    def accessPrivate(rocket: Rocket) {
      rocket.canGoHomeAgain
    }
  }
```
 
# Chapter 14: Assertions and Unit Testing<a name="Chapter14"></a>
The `assert(condition)` or `asser(condition, explanation) //explanation is returned if the assert is not true` methods throws AssertionErrors if the condition is not true, and can be used to check preconditions that needs to hold true in order to execute tests, or checks in methods.
The `ensuring(condition)` takes one argument, a predicate function that takes a result type and returns Boolean. ensuring will pass the result to the predicate. If the predicate returns true, ensuring will return the result. Otherwise, ensuring will throw an AssertionError. Assertions (and ensuring checks) can be enabled and disabled using the JVM’s -ea and -da command-line flags.
 
 * ScalaTest: In Scala test you can write test by extending `org.scalaTest.Suit` class and defining methods that start with the keyword `test` and execute them with the `execute()` method:

```scala
import org.scalaTest.Suite
class TestSuite extends Suite{
    def testThisThing(){...}
}
//Execute this as (new TestSuite).execute()
```

By using `org.scalatest.FunSuite` you can pass a function value to be executed and an argument to its `test` method which is the name of the test to be executed `test("The test name"){//the function value}`. To see meaningful messages in the test reports generated by the assertion, you can use the triple equals like `assert(parameter ===3)` and the arguments passed will be displayed in the reporting. Or you can use the scalaTest `expect(value)` method. You can also capture expected exceptions like this:

```scala
intercept(ClassOf[NullPointerException]){...}
```

 * JUnit and TestNG: With Junit, you can either extend `TestCase` and write a `testXXX` method, or extend the `JUnit3Suite` and write the test case using ScalaTest assertions. You can run the `JUnit3Suite` with the ScalaTest runner (even java code if you use the `JUnit3WrapperSuite` class). Alternatively, you can use TestNG like this:

```scala
import org.testng.annotations.Test
class ElementTests {
@Test def verifyUniformElement() {...}
}
```

As with the `JUnit3Suite` with TestNG we have the `TestNGSuite` trait to use ScalaTest syntax. 

In BDD (behaviour driven development) we can use the trait `Spec` which has describers and specifiers. A describer is writen like `"string" -- {...}`an describes the “subject” being specified and tested. A specifier, written as `"string" - {...}`, specifies a small bit of behavior of that subject (in the string) and provides code that verifies that behavior (in the block). Each specifier is run as a scala test.

```scala
class ElementSpec extends Spec {
    "An Element" -- {
        "should be equal to three" - {...}
    }
}
```

More Specs are provided to write test closer to natural language. ScalaCheck allos the developer to specify properties the code must obey. It takes a function value as an argument:

```scala
test("elem result should have passed width", (w: Int) => w > 0 ==> (elem('x', w, 3).width == w))
```

The `==>` symbol is an implication operator. It implies that whenever the left hand expression is true, the expression on the right must hold true. To make multiple property checks in a single test use `org.scalatest.prop.Checkers` like this:

```scala
class ElementSuite extends JUnit3Suite with Checkers {
  def testUniformElement() {
    check((w: Int) => w > 0 ==> (elem('x', w, 3).width == w))
    check((h: Int) => h > 0 ==> (elem('x', 2, h).height == h))
  } 
}
```

# Chapter 15: Case Classes and Pattern Matching<a name="Chapter15"></a>
Case classes are special constructs in scala to which the language adds conveniences to the class that includes:

* A factory method with the name of the class. i.e:

```scala
case class Example(x:String)
val ex = Example("x")
```

* All arguments in the parameter list of the class gets a `val` modifier and are maintained as fields.
* Natural implementations for the `toString`, `hashCode`, and `equals` methods.

Case classes supports pattern matching, which is writen as:

```scala
expr match{
    case <pattern> => <return>
    ...
}
```

A match expression is evaluated by trying each of the patterns in the order they are written, the first that matches is executed. Matches are different thant the switch cases in java in that they result in expressions, there is no "fall through" if a match is found (no `break` needed) and if there is no match a MatchError is thrown (you can put a `case _ => ...` to cover the default case). The right hand side expression can be left blank resulting in a () expression.

Types of patterns:

* Wildcard pattern: Matches any object and also serves to ignore a part of an object you don't care about: `case e:Sum(_,_)=>...`
* Constant pattern: Matches only itself: `case true => ...`
* Variable pattern: Matches any object and binds the object to the variable: `case varObject => varObject.toString` a simple name starting with a lowercase letter is taken to be a pattern variable; all other references are taken to be constants (for example if we import the Pi number and use it as a constant match). This is true unless we enclose the variable in backtips.
* Constructor pattern: It consist of a name an a series of patterns within parentheses. If it is a case class first the object is checked to see if it belongs to the case class indicated, and if so if the patterns supplied as parameters matches the constructor parameters (scala supports deep matches).
* Sequence pattern: Matches again sequence types like Array or List, you can specify the number of elements like `case List(0,_,3) => ...` or not by using `_*` like `case Array(0, _*) => ...`
* Tuple pattern: A pattern like `case (a,b,c) => ...` matches any 3-tuple.
* Typed pattern: For type test and type cast like the ones on the Exception handling. `case e:Exception => ...` or `case m:Map[_,_] => ...` you can't search for maps like `Map[String, String]` as there is type erasure of the arguments. As in java, Arrays are typed and can be type checked.
* Variable binding: You can bind a pattern to a variable by writing an @ after the variable name and then the pattern to bind the variable to, like `case UnOp("abs", e @ UnOp("abs", _)) => e`. In this case e is a pattern like `UnOp("abs", _)`.

A pattern variable can only appear once in a pattern, so if you need certain conditions to proceed with a case (for example the two arguments to a sum has to be equal) you can use pattern guards like this: `case e:Sum(x,y) if x == y => ...`. A pattern guard starts with if and follows with a boolean expression. 

A sealed class can only by extended by classes in the same file where the superclass is defined, which is useful for pattern matching. If you match against case classes that inherit from a sealed class, the compiler will flag missing combinations of patterns with a warning message. This is define like this: `sealed abstract class example`. If you know a certain cases will not be used, you can add a `case _ => throw new Exception...` to avoid the compiler warnings or add an @unchecked annotation to the object that is pattern matched like this: `(e: @unchecked) match{...}`.

An _Option_ object carries an optional value, that when requested can result in a _Some(x)_ object if the requested object exists or a _None_ object if it doesn't, for example when requested an object by key in a scala Map, the result is _Some(x)_. You can pattern match it like this:

```scala
 def getSomeValue(e:Some[String]) : String = e match{
        case Some(x) => x
        case None => "?"
 }
```

Patterns can be used in:

* Variable definitions: it is possible to assign each part of an assignment to an identifier using pattern match. i.e.

```scala
val exp = Expresion(Number(0), ValString("3")
val Expresion(number, valString) = exp // we can access Number(0) by number and ValString("3") by valString
```

* Case sequences as partial functions: A case function is a function literal that can be used anywhere a function literal can be used. A case sequence has multiple entry points (the right hand side expression), each with their own list of parameters (the pattern for each case) instead of a single entry point like for other function literals. A type `PartialFunction` is a function that covers a non-exhaustive pattern match, and has a method `isDefinedAt(...)` that can be used when we need to check if a match is covered or not. 
* Patterns in for expressions:Patterns can be used to match again the elements traversed in a for loop. i.e.

```scala
val results = List(Some("apple"), None, Some("orange"))
for (Some(fruit) <- results) println(fruit) // Will print "apple" and "orange"
```

# Chapter 16: Working with Lists<a name="Chapter16"></a>
Lists in scala are immutable (elements cannot be reassigned), lists have a recursive structure (arrays are flat). List are covariante, this means that if T is subtype of S, then `List[T]` is a subtype of `List[S]`. All lists are build from _Nil_ (which represents the empty list) and _::_ which represent a list who's first element is the one on the left of the operator followed by the elements of the right operator. i.e we can build a list containing the first two natural numbers as `1::(2::Nil)` or `1::2::3` (concatenates to the right). All operations on Lists can be expressed in terms of the following methods defined in the List class:

* head returns the first element of a list (for non empty list, Exception is thrown otherwise)
* tail returns a list consisting of all elements except the first (for non empty list, Exception is thrown otherwise)
* isEmpty returns true if the list is empty

It is possible to use pattern match with list using a patter like _List(...)_ or to match parts of the lists using _::_ and _Nil_. i.e. the patter _a::b::rest_ matches lists that has at least 2 elements.

#### First order (methods that does not take functions as arguments) methods in the class List:

* Concatenation: written as _:::_ it takes two lists as operands and returns a new List containing the elements of the left operand followerd with the right hand operand elements. As _::_, _:::_ concatenates to the right.
* Length: `List(3,4,5).length`, similar to java. Although this is an expensive operation who's cost is proportional to the size of the list.
* Init and Last: returns the first and last element of a list. Throws exception if the list is empty. This operation cost is proportional to the list size.
* Reverse: Reverses the order of the elements of a lists. As Lists are immutable, it returns a new one.
* take: `List(1,2,3).take(2)` returns a `List(1,2)`. `List(1,2,3).take(6)` returns a `List(1,2,3)`
* drop: `List(1,2,3).drop(2)` returns a `List(3)`
* splitAt: `List(1,2,3).splitAt(2)` returns `(List(1,2), List(3))`
* apply: `List(1,2,3).apply(2)` returns `2`
* zip: takes two lists and returns a List containing pairs like `(x0,y0), (x1,y1)...` the method zipWithIndex would zip every element with its index.
* toString and mkString: the toString returns the canonical representation of the list, mkstring(pre, sep, post) will return the elements of the list separated with the op element, prefixed with the pre element and postfixed with the post element. `abcde mkString ("[", ",", "]")` returns `[a,b,c,d,e]`
* toArray: converts the list in an Array. toList would to the opposite in the Array class.
* copyToArray: this methods accepts an Array, and an integer. This would copy the list in the array passed starting at the position defined by the integer.
* elements: returns an iterator of the list 

#### Higher order methods in the class List:

* map: Takes a function and applies it to all elements on the list:
* flatMap: similar to map but it takes a function returning a list of elements, it applies the function to all elements and returns the concatenation of the results.
* foreach: similar to map but it takes a function that returns unit and it applies it to all the members of the list, returning a unit.
* filter: receives a function `T => Boolean`. If the function returns false then the element is filter out.
* partition: similar to filter but it returns a pair of lists, one with the elements that returned true and the other with the elements that returned false.
* find: similar to filter but returns the first elements that satisfies the predicate. Returns an `Option` value.
* takeWhile: The operation xs takeWhile p takes the longest prefix of list xs such that every element in the prefix satisfies p. `List(1, 2, 3, -4, 5) takeWhile (_ > 0) //returns List(1,2,3)`
* dropWhile: The operation xs takeWhile p drops the longest prefix of list xs such that every element in the prefix satisfies p.
* span: a combination of the previous two. xs span p equals (xs takeWhile p, xs dropWhile p). 
* forall: xs forall p is returns true if all elements in xs satisfy p:( T => Boolean)
* Folding lists: the `/:` operator combines the elements of a list with some operator `(0 /: xs) (_ + _) // equivalent to 0 + xs1 + xs2 + ...`. IThe fold left operation is defined as `(z /: List(a, b, c)) (op) equals op(op(op(z, a), b), c)`. In a similar manner, the `\:` operator is defined as `List(a, b, c) :\ z) (op) equals op(a, op(b, op(c, z)))`
* sort: 

#### Methods of the list object (The ones defined in the companion object of the class List):

* apply: Takes a collection of numbers and returns a list. `List(1,2) // List[Int]`
* range: Creates a List containing a range of numbers `List.range(1,4) //returns a List(1,2,3)`. It also accepts a defined step `List.range(1,9,2) //resturns List(1,3,5,7)`
* make: `List.make(x:Int, y:Object)` creates a uniform list with x number of y objects on it.
* unzip: Takes a list of pairs and returns a pair of lists one with the `_.1` elements and the second with the `_.2` elements.
* flatten:takes a list of lists and concatenates all element lists of the main list.
* concat: Concatenates the element lists that are given directly as repeated parameters `List.concat(List(a,b), List(c)) // return List (a,b,c)`
* map2: Takes two lists and a function that maps two elements values to a result `List.map2(List(10, 20), List(3, 4, 5)) (_ * _) //return List(30,80)`
* forall2: similar to the previous `List.forall2(List("abc", "de"), List(3, 2)) (_.length == _) //returns true`
* exists2: similar to the previous `List.exists2(List("abc", "de"),List(3, 2)) (_.length != _) // returns false`

# Chapter 17: Collections<a name="Chapter17"></a>
The main trait in scala collections is Iterable, which defines a method `def elements :Iterator[A]`. The Iterator is the mechanism to iterate the Iterable collections. An Iterator can be traversed only once. The Iterator has the methods `def hasNext:Boolean` and `def next:A`.
The trait Seq extends from Iterable, their elements are order and can be requested (for example access the 4th element).

#### Sequences
* Lists: Not efficient in accessing random positions as it has to iterate until that position, works well for iterations
* Arrays: Good for accessing arbitrary positions.
* List Buffers: Suitable for appending to the tail or head of the List. provides constant time append (with the `+=` operator) and prepend (with the `+:` operator) operations. A `scala.collections.mutable.ListBuffer` can return a List if the method `toList` is invoked.
* Array Buffers: Like an array, except that you can additionally add and remove elements from the beginning and end of the sequence. Available as `scala.collection.mutable.ArrayBuffer`
* Queues: for FIFO collections in two flavours mutable and immutable. To append an element or a List of element to an immutable queue the method `enqueue` is provided with overloaded signature. To remove an element from the head of the queue the method `dequeue` is provided, this method returns a pair with the element at the head and the rest of the queue with the element removed. On mutable queues, the method `+=` serves to append an element, the `++=` to append a list, and the `dequeue` will just remove the element from the head and return it.
* Stacks: For a LIFO collection, also mutable and Immutable. `pop` To extract an element, `top` to get the head element without removing it and `push` to add it.
* RichString: it is a `Seq[Char]`, there is an implicit conversion between string in the predef object.

#### Sets and Maps
* Sets: Collection that ensures that at most one of each object, as determined by ==, will be contained in the set at any one time. 
* Maps: To associate a value with each element of a collection.
* SortedSet: Collections that has an iterator which returns elements in a particular order. Implemented by the TreeSet, needs elements that implements the trait Ordered or an implicit is available to convert the elements.
* SortedMap: similar to the SortedSet, implemented by the TreeMap, and ordered accorded to the keys.
* Synchronized sets and maps: It is possible to create a synthetic synchronized map class like this:
    - `new HashMap[String, String] with SynchronizedMap[String, String]`
    - `new mutable.HashSet[Int] with mutable.SynchronizedSet[Int]`

Scala would interpret the `a+=b` method in immutable collections (which does not support this method) as `a=a+b`. I f you declare a set as a var and use a += on it, then a new collection with the element will be returned and the var pointer will be reassigned. The same applies to methods ending in `=`.
Sometimes you may want to create a collection but specify a different type from the one the compiler would choose, this can be done by defining the type when you define the collection like in `val init=mutable.Set[Int](43)`, or adding the elements of a set to the type of set you want like in `val treeSet = TreeSet[String]() ++ colors //colors is a Set`. Initializing Lists from other collections is easier (just call toList), same for Arrays with the toArray metho, it can be slow for large collections as elements will be copied using the Iterator. You can convert a mutable collection to an immutable collection and viceversa by invoking the ++ method on an empty collection of the desired type and adding the elements of the mutable/immutable collection to it. Tuples can combine objects of different types, thus tuples do not inherit from Iterable. 

# Chapter 18:Stateful Objects<a name="Chapter18"></a>
In scala, every reassignable var that is a non private member of a class gets a setter (`x_=` being x the name of the var) and a getter (just `x`). The getters and setters generated has the same visibility than the original var. In addition, you can also choose to define a getter and a setter directly instead of defining a var, for example:

```scala
class Time {
  private[this] var m = 0
    def hour: Int = h
    def hour_=(x: Int) { h = x }
    def minute: Int = m
    def minute_=(x: Int) { m = x }
}
```

To initialize a field with a zero value that will depend on the field's type, we can use `= _`. You can define a _type member_ inside a class with the keyword *type* followed by the definition. For example `type Action = () => Boolean` defines a type Action that is a parameterless function that returns a boolean.

# Chapter 19: Type Parameterization<a name="Chapter19"></a>
In Scala it is possible to hide the primary constructor by adding a private modifier in front of the class parameter list: `class Test private (...){//body}`.
This constructor can be accessed only from within the class itself and its companion object. A class can also be hided and only export a trait that reveals the public interface of the class, like `private class <name> ...`. A parameterized type is called a type constructor because with it you can construct a type by specifying a type parameter (for example a parameterized trait `Example` is a type constructor that can construct different types such as `Example[String]`, `Example[Int]`...). In Scala, generic types have by default nonvariant (or, “rigid”) subtyping, thus you can't pass a `Example[T]` to a method that needs a `Example[F]` being T subtype of F. This behaviour can be changed if you define the class like `Example[+F]`. Prefixing a formal type parameter with a + indicates that subtyping is covariant (flexible) in that parameter. If instead you define the class like `Example[-T]`, means that the class is contravariant subtyped, meaning that if T is a subtype of type S, this would imply that `Example[S]` is a subtype of `Example[T]`. For example contravariance in a function passed as a parameter makes sense as any operation defined in that function would be accessible by a subtype of it. 
Scala treats arrays as nonvariant, so as opposite with java, you would have to cast an array of one type to its supertype using the `asInstanceOf[type]` method. It is possible to give a method a lower bound for a type parameter, for example given the example below, the type `U` is a supertype of the type `T`: 

```scala
class Example[+T]  {
def method[U >: T](x: U) = {...} //Accepts a type T or any of its supertypes
}
```

An upper bound, specified as `<:` for example in a method like `def getSortedList[T<:Ordered[T]](..)` you are specifying that `T` has to be a subtype of `Ordered[T]`.

# Chapter 20: Abstract Members<a name="Chapter20"></a>
Type of Abstract members of a class:

* Abstract type: type declared to be a member of a class or trait, without specifying a definition.
* Abstract val: Gives a name and type, but no value. i.e. `val name:String`. The subclasses will give a value for the abstract member. Any implementation must be a val definition. An abstract method can be implemented by a val by a subclass, but not viceversa.
* Abstract var: Declared like the vals, just a name and type. The reason to have abstract vars is because the come with getters and setters if declared as a member of a class definition.
   
Abstract vals are important in traits because they don't have constructors to pass parameters to. IF we instantiate a trait as an anonymous class, the expressions passed to initialize the abstract members are not evaluated before the instantiation of the class, but during the instantiation of the anonymoys class (after the initialization of the trait). Therefore if any requirement must be met, it may fail as the trait is evaluated with the abstract members to the default values, and then the anonymous class initializes with the expressions passed. A class parameter argument is evaluated before it is passed to the class constructor (unless the parameter is by-name). An implementing val definition in a subclass, by contrast, is evaluated only after the superclass has been initialized. There is two solutions for robust initialization:

* Pre-initialized fields: The initialization section comes before the mention of the trait. Both separated by a with. i.e.:

```scala
class Example (member:Int) extends {
member=1//initialization code
} with TraitExample //The constructor class is initialized and made available to the trait.
```

Pre initialized fields are initialized before calling the class constructor and cannot refer to the object being constructed.

* lazy vals: With the lazy modifier, the initializing expression on the right-hand side will only be evaluated the first time the val is used. A lazy val is never evaluated more than once. Lazy vals are an ideal complement to functional objects, where the order of initializations does not matter,

With abstract types you can define behaviour like this:

```scala
class Food
abstract class Animal {
    type SuitableFood <: Food
    def eat(food: SuitableFood)
}
```

In scala, classes can have path dependent types, which is of the form `<instance name>.<type>`, i.e. _example.TypeExample_. Path dependent types are alias to the real types behind. Path dependent types are similar to inner classes in java, except for the fact they refer to outer objects, not outer classes. 
Enumerations are one application of path dependent objects, in scala an enumeration is defined by extending scala.Enumeration like this:

```scala
class Color extends Enumeration{
val Red = Value
val Green = Value // also val Red, Green = Value will work
}
```

Enumeration defines an inner class named Value, and the same named parameterless Value method returns a fresh instance of that class. In the above example `Red` and `Green` types are `Color.Value`. In scala, inner objects are referenced like `Outer#Inner`, the '.' is reserved for path-dependent types.
You can neither create an instance of an abstract type, nor have an abstract type as a supertype of another class.

# Chapter 21: Implicit Conversions and Parameters<a name="Chapter21"></a>
Implicit conversions are normal methods prepended with the `implicit` modifier, the compiler will try to insert an implicit to fix a type error. Implicit conversions are governed by the following general rules:

* Marking Rule: Only definitions marked implicit are available. You can mark a variable, function, or object definition with the `implicit` keyword, and only those definitions marked with `implicit` will be tried to fix type errors.
* Scope Rule: An inserted implicit conversion must be in scope as a single identifier, or be associated with the source or target type of the conversion. The implicit conversion must be in scope as a single identifier, which means the compiler won't try to expand something like `someVariable.convert(x)`, but it will work if we do `import someVariable._` (as then it will be available as a single identifier). This rule has one exception, which is if we place the implicit in the companion object, then its said that we have the implicit associated with the object type.
* Non-Ambiguity Rule: An implicit conversion is only inserted if there is no other possible conversion to insert.
* One-at-a-time Rule: Only one implicit is tried. For sanity’s sake, the compiler does not insert further implicit conversions when it is already in the middle of trying another implicit.
* Explicits-First Rule: Whenever code type checks as it is written, no implicits are attempted. The compiler will not change code that already works.
* Naming an implicit conversion: The name of an implicit conversion matters only in two situations: if you want to write it explicitly in a method application, and for determining which implicit conversions are available at any place in the program. If you only want to import a particular implicit in a class, you can do so by `import <Class>.<implicit method name>`.

There are three places implicits are used in the language:

### Implicit conversion to an expected type.
The rule is whenever the compiler needs a type X but has type Y, it looks for an implicit to convert Y to X. The compiler will look for available implicits in scope to avoid a compiler error. The scala.Predef object, which is implicitly imported into every Scala program, defines implicit conversions that convert “smaller” numeric types to “larger” ones.

### Converting the receiver
Implicit conversions also apply to the receiver of a method call, which serves to interoperate with new types and to define new domain specific languages. For example in a map `Map("key" -> "value)`, the `->` is a method of the class `ArrowAsoc` in the package `scala.Predef`. Whenever you see someone calling methods that appear not to exist in the receiver class, they are probably using implicits.

### Implicit parameters
Implicits in parameter lists are of the form replace `someCall(a)` with `someCall(a)(b)`, thereby adding a missing parameter list to complete a function call. It is the entire last curried parameter list that’s supplied, not just the last parameter.  You can define an implicit parameter to a method call like:

```scala
object Greeter {
    def greet(name: String)(implicit prompt: PreferredPrompt) {
      println("Welcome, "+ name +". The system is ready." + prompt.preference) // The method can still be called like Greeter.greet("Bob")(bobsPrompt)
    }
}
```

You can then define something like `object JoesPrefs {implicit val prompt = new PreferredPrompt("Yes, master> ")}`, and then import the implicit as single identifier using `import JoesPref._`. With this implicit on scope, you can make a call like `Greeter.greet("Bob")` and it will work. As said, the compiler can pass an entire parameter list, not a single argument. Because the compiler selects implicit parameters by matching types of parameters against types of values in scope, implicit parameters usually have “rare” or “special” enough types that accidental matches are unlikely. As a style rule, it is best to use a custom named type in the types of implicit parameters.
Implicit parameters are not usually called by name in the code of the methods, the programmers usually lets the compiler to complete the methods. Scala provides a convenience to shortened the methods called view bounds:
`def maxList[T](elements: List[T])(implicit orderer: T => Ordered[T]): T ={...}` becomes `def maxList[T <% Ordered[T]](elements: List[T]): T ={...}`. Where `T <% Ordered[T]` means that T can be treated as `T` or as `Ordered[T]`. This is different from an upper bound (`<:`) as in `T<:Ordered[T]`, where `T` _is_ an `Ordered[T]`.
 
When debugging implicits, if the compiler doesn't find an implicit, write the conversion explicitly to see if that indicates an Error (i.e. `val chars:List[Char] = str2CharList("abc")`). In this case the compiler might tell you the return type is wrong, or if the error goes away might be due to an scope problem. Also the `-Xprint:typer` option to the compiler displays useful information about the implicits used.

# Chapter 22: Implementing Lists<a name="Chapter22"></a>
List is an abstract class in the package scala with two subclasses for :: and Nil. The class List is defined as `abstract class List[+T]` which means is covariant, so you can assign a `List[Int]` to a `List[Any]`. The list class defines three abstract methods:
```scala
def isEmpty: Boolean
def head: T
def tail: List[T]
```
From which all the methods in list can be implemented.

* Nil Object: Defines an empty list. `isEmpty` returns always true and the `head` and `tail` method returns a NoSuchElementException.
* :: Object: The cons class represents a non-empty list. It is constructed with a head element an a tail list, which are returned by the `head` and `tail` methods.

```scala
final case class ::[T](hd: T, tl: List[T]) extends List[T] {
    def head = hd
    def tail = tl
    override def isEmpty: Boolean = false
}
```

Because the methods `scala.::` and `scala.:::` ends in a colon, they are bound to the right operand, such as s::d is treated as d.::s. This method is defined as:

```scala
def ::[U >: T](x: U): List[U] = new scala.::(x, this)
``` 

so we can always use `::` to add a superclas of the current list to the list, returning a list of the superclass type. The method `:::` is defined as:

```scala
def :::[U >: T](prefix: List[U]): List[U] =
    if (prefix.isEmpty) this
    else prefix.head :: prefix.tail ::: this
```

List buffers let you accumulate the elements of a list. To do this, you use an operation such as “buf += elem”, which appends the element elem at the end of the list buffer buf. After appending all elements, you can create a list by calling the `toList` method of the list buffer. ListBuffer is a class in package scala.collection.mutable. (+=) and the `toList` method of list buffer takes constant time. In scala lists, you can either construct lists incrementally by adding elements to the beginning of a list using ::, or you use a list buffer for adding elements to the end.

# Chapter 23: For Expressions Revisited<a name="Chapter23"></a>
All for expressions that yield a result are translated by the compiler into combinations of invocations of the higher-order methods map, flatMap, and filter. All for loops without yield are translated into a smaller set of higher-order functions: just filter and foreach. A for sequence is composed of:
 
* Generators: Of the form `pat <- expr` where `expr` typically returns a list, and pat is a pattern to match with. Every for expression starts with a generator. If there are several genera- tors in a for expression, later generators vary more rapidly than earlier ones.
* Definitions: Of the form `pat=expr`.
* Filters: Of the form `if expr`, drops the elements that doesn't match the condition.

Every for expression can be translated in terms of the three higher-order functions map, flatMap and filter:

* for expressions with one generator: `for (x <- expr1) yield expr2` is translated to `expr1.map(x => expr2)`
* for expressions starting with a generator and a filter: `for (x <- expr1 if expr2) yield expr3` is translated to `expr1 filter (x => expr2 ) map (x => expr3 )`
* for expressions starting with two generators: `for (x <- expr1; y <- expr2; seq) yield expr3` is translated to `expr1.flatMap(x => for (y <- expr2; seq) yield expr3)`
* Translating patterns in generators: `for (pat <- expr1) yield expr2` is translated to `expr1 filter { case pat => true case _ => false} map { case pat => expr2}`. A pattern-matching generator will never throw a MatchError.
* Translating definitions: `for (x <- expr1; y = expr2; seq) yield expr3` is translated to `for ((x, y) <- for (x <- expr1) yield (x, expr2); seq) yield expr3`.
* for loops without return: `for (x <- expr1) body` translates to `expr1 foreach (x => body)`

In the same sense, the operations map, flatmap and filter can be implemented it for. To support the full range of for expressions and for loops, you need to define map, flatMap, filter, and foreach as methods of your data type. 

# Chapter 24: Extractors<a name="Chapter24"></a>
Scala extractors let you define new patterns for preexisting types, where the pattern need not follow the internal representation of the type. An extractor in Scala is an object that has a method called unapply as one of its members. `unapply` match a value and take it apart.
 
 ```scala
 object EMail {
   def unapply(str: String): Option[(String, String)] = {
     val parts = str split "@"
     if (parts.length == 2) Some(parts(0), parts(1)) else None
   } 
 }
```

When a pattern is matched against an extractor, it invokes the `unapply` method and it returns either `Some(...)` or `None`, in which case the pattern matching will try the next pattern (or fails with a MatchError if there is none). The variable against which the extractor is matches can be of any type (it is casted to the argument of the `unapply` method). If an injection method is included, it should be the dual to the extraction method. For instance, a call of:
`EMail.unapply(EMail.apply(user, domain))` should be equal to  `Some(user, domain)`, although this condition is not enforced by scala.

In case a pattern in unapply does not bind any value, the method returns a boolean indicating success or failure. It is still possible to associate a variable with the whole pattern matched by it, to do so, we write the name of the variable followed by `@` and the pattern. i.e. `x @ patternExpression()`.
An Extractor can support vararg matching, to handle this case, Scala lets you define a different extraction method called `unapplySeq` specifically for vararg matching.i.e:

```scala
  object Domain {
    // The injection method (optional)
    def apply(parts: String*): String =
      parts.reverse.mkString(".")
      
    // The extraction method (mandatory)
    def unapplySeq(whole: String): Option[Seq[String]] =
      Some(whole.split("\\.").reverse)
}
```

The return type of `unapplySeq` must be `Option[Seq[T]]`, It’s also possible to return some fixed elements from an unapplySeq together with the variable part. This is expressed by returning all elements in a tuple, where the variable part comes last. Extractors enable patterns that have nothing to do with the data type of the object that’s selected on. This property is called _representation independence_ and provides independence (decoupling) between the pattern match and the instance type. If you write code for a closed application, case classes are usu- ally preferable because of their advantages in conciseness, speed and static checking. But if you need to expose a type to unknown clients, extractors might be preferable because they maintain representation independence.

Scala’s regular expression class resides in package `scala.util.matching`, a new regular expression value is created by passing a string to the Regex constructor.
 `val Decimal = """(-)?(\d+)(\.\d*)?""".r` gives you a regular expression, this is possible because there is a method named `r` in class RichString, which converts a string to a regular expression. Regex classes in scala gives several methods to deal with regular expressions:
 
* `regex findFirstIn str`: Finds first occurrence of regular expression regex in string str, returning the result in an Option type.
* `regex findAllIn str`: Finds all occurrences of regular expression regex in string str, return- ing the results in an Iterator.
* `regex findPrefixOf str`: Finds an occurrence of regular expression regex at the start of string
   str, returning the result in an Option type.
   
Every regular expression in Scala defines an extractor. The extractor is used to identify substrings that are matched by the groups of the regular expression.i.e.
`val Decimal(sign, integerpart, decimalpart) = "-1.23" //matches sign="-", integerpart="1", decimalpart=".23"`
 `val Decimal(sign, integerpart, decimalpart) = "1.0" //matches sign=null, integerpart="1", decimalpart=".0"`
You can use regex in for loops like this: `for (Decimal(s, i, d) <- Decimal findAllIn input)`

# Chapter 25: Annotations<a name="Chapter25"></a>
A tool that uses annotations (called meta-programming tool), can use annotations to perform different routines such as pretty printing, formatting... Annotations are of the form `@Annotation [val|var|def|class|object|trait|type] ...` and applies to the entire declaration of what follows it. They can apply to expressions as in `(e: @unchecked) match {...}`, and they can take arguments `@serial(1234) SomeClass`. 
Some Standard annotations in scala:

* @deprecated: Indicates that the code marked with it will be removed in the future. Emits a warning message to the developers using the code.
* @volatile: It informs the compiler that the variable in question will be used by multiple threads.
* @serializable: Indicates a class is binary serializable. By default, a class is not considered serializable.
* @SerialVersionUID(number): The serialize framework should store the number passed along with the serialized class. When you later reload that byte stream and try to convert it to an object, the framework can check that the current version of the class has the same version number as the version in the byte stream.
* @transient: The field annotated with this is not serialized at all. When the object is loaded, the field will be restored to the default value for the type of the field annotated as @transient.
* @scala.reflect.BeanProperty: If you add this annotation to a field, the compiler will automatically generate get and set methods for you. The generated get and set methods are only available after a compilation pass completes, you cannot call these get and set methods from code you compile at the same time as the annotated fields.
* @unchecked: It tells the compiler not to worry if the match expression in a pattern match seems to leave out some cases.

# Chapter 26: Working with XML<a name="Chapter26"></a>
Scala lets you type in XML as a literal anywhere that an expression is valid. The resul would be of type `scala.xml
.Elem`, other important classes in xml are:

* Node: Abstract superclass of all XML elements.
* Text: Class holding text inside a tag.
* NodeSeq: Sequence of nodes.

Scala allows you to insert scala code inside the xml code with curly braces `{}`. For example:
`<a> { if (yearMade < 2000) <old>{yearMade}</old> else xml.NodeSeq.Empty}</a>`
If the expression result to an scala type, it is converted to string before being inserted. To escape a curly brace 
inside an XML, simply duplicate the curly brace.
Some relevant methods in XML:

* `xmlElement.text`: To retrieve the text within the node
* Extracting subelements: to retrieve an element "b" inside a node, we call the method `\ "b"`. To do a deep search 
(elements within elements) use `\\` instead.
* Extracting attributes: use `\ @ "attributeName`. i.e. `<a><b color="red">text</b></a> \ "b" \ @ "color" // would 
return red`.

Using the previous example, we can write a deserializer like this:

```scala
def from XML(nodeElement:scala.xml.Node) : SomeClass = {
  val a =  nodeElement \ "a"
  val b = nodeElement \\ "b"
  val colorB = (nodeElement \\ "b" \@ "color").text
}
```

To convert xml to a file of bytes we can use the `saveFull` method, which as opposite of the `toString` method, 
keeps track of the encoding used:

* serialization: `scala.xml.XML.saveFull("file.xml", node, "UTF-8", true, null)`
* deserialization: `xml.XML.loadFile("therm1.xml") //no encoding passed`

In pattern matching with xml if you insert a {} escape, then the code inside the {} is not an expression but a 
pattern:

```scala
   node match {
      case <a>{contents}</a> => "It's an a: "+ contents
      case <b>{contents @ _*}</b> => "It's a b: "+ contents
      case _=> "Other thing"
   }
```

If you want to match any sequence of nodes inside a pattern, the pattern for “any sequence” of XML nodes is written 
`_*`, represented in the case for the "b" element. You can combine this syntax with for loops:

```scala
    catalog match {
        case <catalog>{therms @ _*}</catalog> =>
          for (prod @ <product>{_*}</product>  <-  therms)
            println("processing: " + (prod \ "description").text)
    }
```

# Chapter 27: Modular Programming Using Objects<a name="Chapter27"></a>
Programs can be divided into singleton objects, which you can think of as modules:  if a module is an object, then a template for a module is a class. Abstract classes can help to modularize the code by provide common code implementation while allowing the developer to define an specialization of some methods or properties inside the classes that extends from it.
Scala provides the _self type_ for the situation where a trait is located in a different trait from the one that uses it, so it is out of scope. Technically, a self type is an assumed type for this whea  never this is mentioned within the class. If you have a trait that is only ever used when mixed in with another trait or traits, then you can specify that those other traits should be assumed. For example:

```scala
trait SimpleExample {
    this: SelfTypeTraitExample =>
    object ExampleObject (
      SomeObjectInsideSelfTypeTraitExample
    )
  }
```

In the above example, the reference to the object `SomeObjectInsideSelfTypeTraitExample` is thought of as `this.SomeObjectInsideSelfTypeTraitExample`.
Sometimes, you may encounter a case where two types are the same but the compiler can’t verify it. You can often fix 
the problem using singleton types, which are defined like this `val someVal: example.type = example`. The “.type” on 
the end means that this is a singleton type.  A singleton type is extremely specific and holds only one object, in 
this case, whichever object is referred to by _example_.

# Chapter 28: Object Equality<a name="Chapter28"></a>
In Scala, object equality is tested with `==` and reference equality with `eq`. By default, `==` in Scala is 
inherited from the class `Any`, which defines the `equals` method with reference equality, therefore `==` is the same
 than `eq`. Some of the common pitfalls when implementing the equals method are:
 
* Defining equals with the wrong signature: The correct signature is `def equals(other: Any): Boolean`.
* Changing equals without also changing hashCode: The hashcode and equals contract is the same than in java.
* Defining equals in terms of mutable fields: If you define equals in terms of variable fields, the object can have an unexpected behavior once added to a collection. If you put such objects into collections, you have to be careful never to modify the depended-on state, and this is tricky.
* Failing to define equals as an equivalence relation: The equals method is reflexive for non-null objects `x.equals
(x)==true`, symmetric, transitive (if x=y and y=z then x=z), consistent, `x.equals(null)` with `x!=null` should always return 
false. It is possible to define a correct implementation of the object equality that also works with superclasses by explicitly state that objects of this class are never equal to objects of some superclass that implement a different equality method. To do so, you can implement:

```scala
def canEqual(other: Any): Boolean 
```

The method should return true if the other object is an instance of the class in which canEqual is (re)defined, false
 otherwise, for example:
  
```scala
def canEqual(other: Any) = other.isInstanceOf[MyClass]
```

When implementing equal methods for typed classes, remember that the type is erased and won't be able at runtime. You
 can pattern match the collection with something like `case Example[t] => ...` where the type `t` in lowercase 
 represent any type (it can also be represented like `case Example[_] => ...`). 
 
Some advices to write a correct implementation of the equals method:

* Consider create a `canEqual` method in non final classes.
* The canEqual method should yield true if the argument object is an instance of the current class.
* The argument to the `equals` method should be `Any`
* Write the body of the equals method as a single match expression. The match expression should have two cases, the first case should de- clare a typed pattern for the type of the class on which you’re defining the equals method.
* In the body of this case, write an expression that logical-ands to- gether the individual expressions that must be true for the objects to be equal.
* Include calls to the `canEqual` method, `super.equals(that)`, write an expression that logical-ands to- gether the individual expressions that must be true for the objects to be equal.
* For the second case of the match, write `case _ => false`.
* For the hashcode, include in the computation all relevant fields that are relevant for the equality, multiply the 
hash code of those fields by a prime.
* If the equals method invokes `super.equals(that)` as part of its calculation, you should start your hashCode calculation with an invocation of `super.hashCode`.

# Chapter 29: Combining Scala and Java<a name="Chapter29"></a>
As much as possible, Scala features map directly onto the equivalent Java features, others like traits has no direct 
equivalent. 

* For value types like Int, the compiler would try to transform those to int, when it is unsure like in the case of 
`List[Any]`, it relies on the wrapper classes.
* Singleton objects: There is no java equivalent, so the compiler uses a combination of static and instance methods. For every Scala singleton object, the compiler will create a Java class for the object with a dollar sign added to the end. The Java class also has a single static field named MODULE$ to hold the one instance of the class that is created at run time.
* Traits creates an interface of the same name.  If you make a Scala trait that includes only abstract methods, then that trait will be translated directly to a Java interface.
* Scala annotations (@deprecated, @volatile, @serializable) has their equivalent in java.
* All Scala methods are translated to Java methods that declare no thrown exceptions. In the case you need to mark an
 operation as susceptible of throwing exceptions, you can mark the method like this:
 
 ```scala
 @throws(classOf[IOException]) 
 def read() = in.read()
 ```

* Existing annotations from Java frameworks can be used directly in Scala code. i.e. using JUNIT annotations.
* To make an annotation that is visible to Java reflection, you must use Java notation and compile it with javac. You
 can inspect a class annotations in scala with:
 
 ```scala
 for {
     method <- Tests.getClass.getMethods
     if method.getAnnotation(classOf[Ignore]) == null //getAnnotation method for searches for an specific type
      ..
     }
 ```
 
All Java types have a Scala equivalent. This is necessary so that Scala code can access any legal Java class. For java wildcards such as `Iterator<?>`, scala uses something called existencial type. The general form of an existential type is as follows:

`type forSome { declarations }`

The interpretation is that the declared variables and types exist but are unknown, Scala will check that the program is sound even though the types and values in the forSome clause are unknown. To avoid initialization problems with existencial types consider the following:

* When passing an existential type into a method,move type parameters from the forSome clause to type parameters of the method.
* Instead of returning an existential type from a method,return an object that has abstract members for each of the types in the forSome clause.

# Chapter 30: Actors and Concurrency<a name="Chapter30"></a>
To implement an actor, you subclass `scala.actors.Actor` and implement the act method. You can also create an actor using a utility method named actor in object `scala.actors.Actor` and implement its `act` method. To start an actor call the start method. It is possible to define an actor by implementing the `actor` method in the Actor class. The actor defined like this starts immediately when it is defined, there is no need to call a separate start method. You can communicate with actors by sending them messages like this: `<actor instance> ! "<The message to send>"`. To define what an actor will do, implement the `receive` or `receiveWithin(timeout)`method, passing it a function. When an actor sends a message does not block, when it receives a message, the message stays in the mailbox until the actor calls the method receive. An actor will only process messages matching one of the cases in the partial function passed to receive:
```scala
receive {
  case x: Int => println("Got an Int: "+ x) //if we send an Int then the message will be processed, otherwise ignored
}
 ```
 If you want to use the current thread as an actor, use the `Actor.self` method. To be more efficient with thread conservation, instead of `receive` we can use `react`. This method returns `Nothing`, it just evaluate the message handler. The message handler you pass it must now both process that message and arrange to do all of the actor’s remaining work, which is usually done by calling the `act` method of the actor again. The `Actor.loop` function executes a block of code repeatedly, even if the code calls react. for example:
 
```scala
def act() {
  loop {
    react {
      case (name: String, actor: Actor) => actor ! getIp(name)
      case msg => println("Unhandled message: " + msg)
    }
  } 
}
```

Best practices writing actors:

* Actors should not block: If an actor is blocked, it may not notice requests sent to it. Do not block the actor, for example call `sleep` in an auxiliary actor to avoid blocking the current one.
* Communicate with actors only via messages: Do not call methods of the actors directly, communicate with the actors using the `!` method. Otherwise you need to lock over the methods and objects used in the direct call which is tricky.
* Prefer immutable messages: Actor models a share-nothing architecture, the best way to ensure that message objects are thread-safe is to only use immutable objects for messages. In general, it is best to arrange your data such that every unsynchronized, mutable object is “owned,” and therefore accessed by, only one actor. 
* Make messages self-contained: One way to simplify the logic of an actors program is to include redundant information in the messages, for example messages might have a reference to the actor that makes the call, so you can send a message back.


# Chapter 31: Combinator Parsing<a name="Chapter31"></a>
Parsers in scala can be used to process special purpose languages with their own defined syntax. JavaTokenParsers is a trait that provides the basic machinery for writing a parser and also provides some primitive parsers that recognize some word classes: identifiers, string literals and numbers. Scala’s parsing combinators are arranged in a hierarchy of traits, which are all contained in package `scala.util.parsing.combinator`. It defines parsers like the `floatingPointNumber` which recognizes a floating point number in the format of Java. This trait extends for the regex parser `RegexParsers` (which extends from the most generic `Parsers` parser). An example of a Json parser is below:

```scala
 import scala.util.parsing.combinator._
  class JSON extends JavaTokenParsers {
    def value : Parser[Any] = obj | arr |
                              stringLiteral |
                              floatingPointNumber |
                              "null" | "true" | "false"
def obj : Parser[Any] = "{"~repsep(member, ",")~"}"
def arr : Parser[Any] = "["~repsep(value, ",")~"]"
def member: Parser[Any] = stringLiteral~":"~value }
```

Where the `repsep` combinator parses a (possibly empty) sequence of terms that are separated by a given separator and the `~` explicit operator serves to indicate sequential composition of productions, the ~ operator produces as its result an instance of a case class with the same name. The results (productions) of the parsers has the following rules:

* Each parser written as a String, returns the string itself.
* Regular expression also returns the parsed string. 
* A sequential composition `P~Q` returns `~(<result of P>, <result of Q>)`, `P|Q` returns  `<result of P>|<result of Q>` whichever one succeed.
* Repetition of `P` returns a list of `<result of P>`.
* An `Option(P)` returns an scala option with `Some(P)` if succeeds or `None` if not. 

With this rules on mind, to transform the output, use the `^^` function, which has the form `P ˆˆ f`, whenever P returns with some result R, the result of `P ˆˆ f` is `f(R)` (i.e. `"true" ˆˆ (x => true) //returns a boolean true for the string "true"`). In addition to the `~` 
case class `case class ~[+A, +B](x: A, y: B) { override def toString = "("+ x +"~"+ y +")" }`, there is also `~>` and `<~` parser combinators. `~>` keeps only the result of its right operand, whereas `<~` keeps only the result of its left operand. A typical grammar production is composed of alternatives that have a parsing part and a transformation part, for example"

```scala
def member: Parser[(String, Any)] = stringLiteral~":"~value ˆˆ{ case name~":"~value => (name, value) } //returns a 
tuple with the parsed name and value separated by ":"
```

A parser type can be defined as : 

```scala
type Parser[T] = Input => ParseResult[T]
``` 

where `Parsed` is the trait contained in the package `scala.util.parsing.combinator.Parsers`. The type Input is:

```scala
 type Input = scala.util.parsing.input.Reader[Elem]
 type Elem //represent individual elements
```

The parse result is defined by:

```scala
 sealed abstract class ParseResult[+T]
 case class Success[T](result: T, in: Input) extends ParseResult[T] //The in, which refers to the input immediately following the part that the parser consumed.
 case class Failure(msg: String, in: Input) extends ParseResult[Nothing]
```

A clause such as “id =>” immediately after the opening brace of a class template defines the identifier id as an alias for this in the class.
Sequential composition is achieved with the method `~` defined as: `def ~ [U](q: => Parser[U]): Parser[~[T, U]]` where 
`~[T, U]` can also be denoted as `T~U`. With this operation we can define the `<~` and `~>` as:

```scala
def <~ [U](q: => Parser[U]): Parser[T] = (p~q) ˆˆ { case x~y => x }
def ~> [U](q: => Parser[U]): Parser[U] = (p~q) ˆˆ { case x~y => y }
```

In the methods above, we can see that the type of `q` is preceded by `=>` which means that the actual parser argument will be evaluated only when q is needed, which should only be the case after p has run. 
The trait `RegexParser` defines the following:

```scala
  implicit def literal(s: String): Parser[String] = ...
  implicit def regex(r: Regex): Parser[String] = ...
```

That's why you can write `"("~expr~")"` because `"("` is converted to `literal("(")` which returns a parser which method `~` is called with the argument `expr`. If a parser fails, among all failures, the one that occurred at the  latest position in the input is chosen. If there are several failure points at that latest position, the one that was visited last is chosen.
Many languages admit so-called “LL(1)” grammars.2 When a combinator parser is formed from such a grammar, it will never backtrack, i.e., the input position will never be reset to an earlier value. The combinator parsing framework allows you to express the expectation that a grammar is LL(1) explicitly, using a new operator ~!. This operator is like sequential composition ~ but it will never backtrack to “un-read” input elements that have already been parsed.
 
# Chapter 32: GUI Programming<a name="Chapter32"></a>
We can create a swing application by extending the class `scala.swing.SimpleGUIApplication`, the top method is called
 to populate the window. Relevant components are:

* Frames: Contains one child component.
* Panels: Can contain one or more child components.
* Buttons: Can perform actions on response to an event
* Label: Displays texts

Components have properties, which can be customized by the applications. Java and Scala have fundamentally the same "publish/subscribe" approach to event handling: Components may be publishers and/or subscribers. In Scala, subscribing to an event source source is done by the call `listenTo(source)` and unsubscribe using the `deafTo(source)` method. In Scala, an event is a real object that gets sent to subscribing components much like messages are sent to actors. To have your component react to incoming events you need to add a handler to a property called reactions.
Important properties of the components of swing are:

* contents: Fixes the children of a component in the tree.
* reactions: Defines the handlers of the component, determines how the component reacts to events.
