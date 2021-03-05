# ChatUtils  

Simple **client-side** mod that adds useful features as commands, such as a calculator.

## Usage

As of right now, there is only one command family: **/calc**
It consists of a few useful subcommands and a calculator, which supports:
- \- + * / ^ operators
- Usage of parenthesis to indicate priority
- Variables (including an "ans" variable, which represents the result of last calculation)
- Functions

For more information of which functions are supported, see [Javaluator](http://javaluator.sourceforge.net/en/home/).

### Commands

- Interpret the given equation
	 - **/calc \<equation\>**
	- e.g.: **/calc (3+pi) * sin(3)**
- Add a variable name so you can use it in equations
	- **/calc add \<variablename\> \<value\>**
	-  e.g.: **/calc add x 5** sets the value of "x" to 5
- Clear all variables from memory
	- **/calc clear**
- Calculate distance from player position to given x y z coordinates
	- **/calc dist \<x\> \<y\> \<z\>**
- Get a quantity of items as whole stacks + remainder
	- **/calc asstacks \<number of items\>**

## Installation  
  
This is a fabric mod and, as such, will require to be loaded with [Fabric Loader](https://fabricmc.net/).

### Mod dependencies:

1. [Fabric API](https://fabricmc.net/)
2. [Fabric Language Kotlin](https://www.curseforge.com/minecraft/mc-mods/fabric-language-kotlin)
3. [Cotton Client Commands](https://www.curseforge.com/minecraft/mc-mods/cotton-client-commands)
4. [OPTIONAL] If you want to use CurseForge as your mod manager for fabric mods, check out [Jumploader](https://www.curseforge.com/minecraft/mc-mods/jumploader)

#

<p><a title="Fabric Language Kotlin" href="https://minecraft.curseforge.com/projects/fabric-language-kotlin" target="_blank" rel="noopener noreferrer"><img style="display: block; margin-left: auto; margin-right: auto;" src="https://i.imgur.com/c1DH9VL.png" alt="" width="171" height="50" /></a></p>