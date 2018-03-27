# Simple Calculator

The Simple Calculator evaluates expressions in a very simple integer expression language.  Input is accepted on the command line and the result computed and displayed on stdout. The calculator
is packaged as an executable jar file. The general syntax for using the calculator is as follows:

```bash
$ java -jar simple-calculator-<version>.jar [-l|--log-level <level>] [-h|--help] [-q|--quiet] <expression>
```
where<br/>
&nbsp;&nbsp;&nbsp;&nbsp;-l, --log-level - sets the log level to one of TRACE, DEBUG, INFO, WARN, ERROR, or OFF<br/>
&nbsp;&nbsp;&nbsp;&nbsp;-h, --help - displays a usage message<br/>
&nbsp;&nbsp;&nbsp;&nbsp;-q, --quiet - sets the log level to OFF. This is simply a convenient short cut for disabling logs.<br/>

### Expressions

The expression language uses a number of keywords corresponding to arithmetic operations and permits the assignment and evaluation of variables.An expression can be any one of the following:
* Literal values:  any valid integer.
* Variables: strings of one or more characters, where characters can be any of a-z or A-Z.
* Arithmetic operations: any one of add, sub, mult, or div as described above.
* A 'let' operation: values are assigned to variables and an expression is provided for evaluation

The table below describes the keywords and their function:

| Keyword | Description | Example |
|---------|-------------|--------------|
| add | '+' operation | add(1,1) == 2|
| sub | '-' operation | sub(2,1) == 1|
| mult | '*' operation | mult(2,2) == 6 |
| div | '/' operation | div(4,2) == 2|
| let | assigns variables | let(a, 10, mult(a, 2)) == 20 | 

The general syntax of the add, sub, mult, and div operations is as follows:

```bash
keyword(operand a, operand b)
```
where the operands can be literal values, variables (provided a value has been assigned by a previous let operation), or any other expression.  The examples shown below illustrate the usage
scenarios.  The general syntax of the let operation is as follows:

```bash
let(identifier, variable expression, arithmetic expression using the variable)
```


### Examples

Here are some examples of the query expression language at work:

| | Input | Output |
|-|-------|--------|
| 1 | add(1, 2) | 3 |
| 2 | add(1, mult(2, 3)) | 7 |
| 3 | mult(add(2, 2), div(9, 3)) | 12 |
| 4 | let(a, 5, add(a, a)) | 10 |
| 5 | let(a, 5, let(b, mult(a, 10), add(b, a))) | 55 |
| 6 | let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))) | 40 |

### How it Works

The calculator is based on a simple brute-force recursive descent parser that builds an expression tree based on the supplied expression.  Once the tree is complete, it is evaluated from
the bottom up, left to right, recursively until a single result is obtained.  Syntax validation is performed primarily as the tree is being built.  Here are some examples based on the expressions in the table, above:

```bash
Expression 1:
            add
            / \
           1   2
``` 

```bash
Expression 2:
             add
             / \
            1  mult
               /  \
              2    3
```


### Logging

The calculator provides a console-only logging capability based on log4j which logs at one of TRACE, DEBUG, INFO, WARN, or ERROR level, or not at all if logging is disabled via the '-q' command line option. The default log level is INFO.  Logging is provided  in this application to assist in trouble shooting. For normal operation, use the '-q' option to display only the result of the operation.  At DEBUG level or below, the constructed expression tree is logged.

#### Error Handling

Errors that occur during parsing or expression execution are displayed on stdout in the console.  If the log level is set to TRACE, a more detailed stack trace describing the error is also logged.

### Building this Project

This project is a basic Maven project, so simply cloning or downloading the project to any environment equipped with maven 3.3+ and JDK 1.8+ will allow the user to build using any of the standard Maven targets.

#### Third-Party Dependencies

As described in the project pom.xml, this project is dependent on the following third-party libraries:
* log4j 2: generic java logging framework
* Apache Commons CLI: command line parsing 
* JUnit 4.12: unit test framework

