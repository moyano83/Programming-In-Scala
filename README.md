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
 i.e.: `class example(private val test:Int = 5){...}`
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
 
#Chapter 13: Packages and Imports
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
 
#Chapter 14: Assertions and Unit Testing
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

#Chapter 15: Case Classes and Pattern Matching
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

#Chapter 16: Working with Lists
Lists in scala are immutable (elements cannot be reassigned), lists have a recursive structure (arrays are flat). List are covariante, this means that if T is subtype of S, then `List[T]` is a subtype of `List[S]`. All lists are build from _Nil_ (which represents the empty list) and _::_ which represent a list who's first element is the one on the left of the operator followed by the elements of the right operator. i.e we can build a list containing the first two natural numbers as `1::(2::Nil)` or `1::2::3` (concatenates to the right). All operations on Lists can be expressed in terms of the following methods defined in the List class:

* head returns the first element of a list (for non empty list, Exception is thrown otherwise)
* tail returns a list consisting of all elements except the first (for non empty list, Exception is thrown otherwise)
* isEmpty returns true if the list is empty

It is possible to use pattern match with list using a patter like _List(...)_ or to match parts of the lists using _::_ and _Nil_. i.e. the patter _a::b::rest_ matches lists that has at least 2 elements.

####First order (methods that does not take functions as arguments) methods in the class List:

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

####Higher order methods in the class List:

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

####Methods of the list object (The ones defined in the companion object of the class List):

* apply: Takes a collection of numbers and returns a list. `List(1,2) // List[Int]`
* range: Creates a List containing a range of numbers `List.range(1,4) //returns a List(1,2,3)`. It also accepts a defined step `List.range(1,9,2) //resturns List(1,3,5,7)`
* make: `List.make(x:Int, y:Object)` creates a uniform list with x number of y objects on it.
* unzip: Takes a list of pairs and returns a pair of lists one with the `_.1` elements and the second with the `_.2` elements.
* flatten:takes a list of lists and concatenates all element lists of the main list.
* concat: Concatenates the element lists that are given directly as repeated parameters `List.concat(List(a,b), List(c)) // return List (a,b,c)`
* map2: Takes two lists and a function that maps two elements values to a result `List.map2(List(10, 20), List(3, 4, 5)) (_ * _) //return List(30,80)`
* forall2: similar to the previous `List.forall2(List("abc", "de"), List(3, 2)) (_.length == _) //returns true`
* exists2: similar to the previous `List.exists2(List("abc", "de"),List(3, 2)) (_.length != _) // returns false`

#Chapter 17: Collections
The main trait in scala collections is Iterable, which defines a method `def elements :Iterator[A]`. The Iterator is the mechanism to iterate the Iterable collections. An Iterator can be traversed only once. The Iterator has the methods `def hasNext:Boolean` and `def next:A`.
The trait Seq extends from Iterable, their elements are order and can be requested (for example access the 4th element).

####Sequences
* Lists: Not efficient in accessing random positions as it has to iterate until that position, works well for iterations
* Arrays: Good for accessing arbitrary positions.
* List Buffers: Suitable for appending to the tail or head of the List. provides constant time append (with the `+=` operator) and prepend (with the `+:` operator) operations. A `scala.collections.mutable.ListBuffer` can return a List if the method `toList` is invoked.
* Array Buffers: Like an array, except that you can additionally add and remove elements from the beginning and end of the sequence. Available as `scala.collection.mutable.ArrayBuffer`
* Queues: for FIFO collections in two flavours mutable and immutable. To append an element or a List of element to an immutable queue the method `enqueue` is provided with overloaded signature. To remove an element from the head of the queue the method `dequeue` is provided, this method returns a pair with the element at the head and the rest of the queue with the element removed. On mutable queues, the method `+=` serves to append an element, the `++=` to append a list, and the `dequeue` will just remove the element from the head and return it.
* Stacks: For a LIFO collection, also mutable and Immutable. `pop` To extract an element, `top` to get the head element without removing it and `push` to add it.
* RichString: it is a `Seq[Char]`, there is an implicit conversion between string in the predef object.

####Sets and Maps
* Sets: Collection that ensures that at most one of each object, as determined by ==, will be contained in the set at any one time. 
* Maps: To associate a value with each element of a collection.
* SortedSet: Collections that has an iterator which returns elements in a particular order. Implemented by the TreeSet, needs elements that implements the trait Ordered or an implicit is available to convert the elements.
* SortedMap: similar to the SortedSet, implemented by the TreeMap, and ordered accorded to the keys.
* Synchronized sets and maps: It is possible to create a synthetic synchronized map class like this:
    - `new HashMap[String, String] with SynchronizedMap[String, String]`
    - `new mutable.HashSet[Int] with mutable.SynchronizedSet[Int]`

Scala would interpret the `a+=b` method in immutable collections (which does not support this method) as `a=a+b`. I f you declare a set as a var and use a += on it, then a new collection with the element will be returned and the var pointer will be reassigned. The same applies to methods ending in `=`.
Sometimes you may want to create a collection but specify a different type from the one the compiler would choose, this can be done by defining the type when you define the collection like in `val init=mutable.Set[Int](43)`, or adding the elements of a set to the type of set you want like in `val treeSet = TreeSet[String]() ++ colors //colors is a Set`. Initializing Lists from other collections is easier (just call toList), same for Arrays with the toArray metho, it can be slow for large collections as elements will be copied using the Iterator. You can convert a mutable collection to an immutable collection and viceversa by invoking the ++ method on an empty collection of the desired type and adding the elements of the mutable/immutable collection to it. Tuples can combine objects of different types, thus tuples do not inherit from Iterable. 

#Chapter 18:Stateful Objects
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

#Chapter 19: Type Parameterization
In Scala it is possible to hide the primary constructor by adding a private modifier in front of the class parameter list: `class Test private (...){//body}`.
This constructor can be accessed only from within the class itself and its companion object. A class can also be hided and only export a trait that reveals the public interface of the class, like `private class <name> ...`. A parameterized type is called a type constructor because with it you can construct a type by specifying a type parameter (for example a parameterized trait `Example` is a type constructor that can construct different types such as `Example[String]`, `Example[Int]`...). In Scala, generic types have by default nonvariant (or, “rigid”) subtyping, thus you can't pass a `Example[T]` to a method that needs a `Example[F]` being F subtype of T. This behaviour can be changed if you define the class like `Example[+T]`. Prefixing a formal type parameter with a + indicates that subtyping is co- variant (flexible) in that parameter. If instead you define the class like `Example[-T]`, means that the class is contravariant subtyped, meaning that if T is a subtype of type S, this would imply that `Example[S]` is a subtype of `Example[T]`. For example contravariance in a function passed as a parameter makes sense as any operation defined in that function would be accessible by a subtype of it 
Scala treats arrays as nonvariant, so as oposite with java, you would have to cast an array of one type to its supertype using the `asInstanceOf[type]` method.
It is possible to give a method a lower bound for a type parameter, for example given the example below, the type `U` is a supertype of the type `T`: 

```scala
class Example[+T]  {
def method[U >: T](x: U) = {...}
}
```

An upper bound, specified as `<:` for example in a method like `def getSortedList[T<:Ordered[T]](..)` you are specifying that `T` has to be a subtype of `Ordered[T]`.