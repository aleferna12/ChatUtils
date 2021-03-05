package com.aleferna.chatutils

import com.aleferna.chatutils.commands.CalculatorCommand
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.DoubleArgumentType
import com.mojang.brigadier.arguments.DoubleArgumentType.getDouble
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType.getInteger
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.arguments.StringArgumentType.getString
import com.mojang.brigadier.tree.LiteralCommandNode
import io.github.cottonmc.clientcommands.ArgumentBuilders.argument
import io.github.cottonmc.clientcommands.ArgumentBuilders.literal
import io.github.cottonmc.clientcommands.ClientCommandPlugin
import io.github.cottonmc.clientcommands.CottonClientCommandSource


class CommandRegistrator : ClientCommandPlugin {
	override fun registerCommands(dispatcher: CommandDispatcher<CottonClientCommandSource>) {
		val calcNode: LiteralCommandNode<CottonClientCommandSource> = literal("calc")
			.then(argument("equation", StringArgumentType.greedyString())
				.executes { context -> CalculatorCommand.calc(context, getString(context, "equation")) })
			.build()

		val distNode: LiteralCommandNode<CottonClientCommandSource> = literal("dist")
			.then(argument("x", DoubleArgumentType.doubleArg())
				.then(argument("y", DoubleArgumentType.doubleArg())
					.then(argument("z", DoubleArgumentType.doubleArg())
						.executes { context -> CalculatorCommand.distTo(
							context,
							getDouble(context, "x"),
							getDouble(context, "y"),
							getDouble(context, "z")
						) })))
			.build()

		val asStacksNode: LiteralCommandNode<CottonClientCommandSource> = literal("asstacks")
			.then(
				argument("number of items", IntegerArgumentType.integer())
					.executes { context -> CalculatorCommand.asStacks(context, getInteger(context, "number of items")) })
			.build()

		val addVariableNode: LiteralCommandNode<CottonClientCommandSource> = literal("add")
			.then(argument("variable name", StringArgumentType.word())
				.then(argument("variable value", DoubleArgumentType.doubleArg())
					.executes { context -> CalculatorCommand.addVariable(
						context,
						getString(context, "variable name"),
						getDouble(context, "variable value")
					) }))
			.build()

		val clearVariablesNode: LiteralCommandNode<CottonClientCommandSource> = literal("clear")
			.executes(CalculatorCommand::clearVariables)
			.build()

		// Root of tree command and also solves equations
		dispatcher.root.addChild(calcNode)
		// Distance from player to coordinates
		calcNode.addChild(distNode)
		// Converts item quantity to stacks
		calcNode.addChild(asStacksNode)
		// Adds a named variable to the variable set
		calcNode.addChild(addVariableNode)
		// Clear all variables
		calcNode.addChild(clearVariablesNode)
	}
}