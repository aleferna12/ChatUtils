package com.aleferna.chatutils.commands

import com.fathzer.soft.javaluator.DoubleEvaluator
import com.fathzer.soft.javaluator.StaticVariableSet
import com.mojang.brigadier.context.CommandContext
import io.github.cottonmc.clientcommands.CottonClientCommandSource
import net.minecraft.client.MinecraftClient
import net.minecraft.text.LiteralText
import net.minecraft.util.Formatting
import net.minecraft.util.math.Vec3d


object CalculatorCommand{
	val evaluator = DoubleEvaluator()
	var variables = StaticVariableSet<Double>()

	fun calc(context: CommandContext<CottonClientCommandSource>, equation: String): Int {
		val result = evaluator.evaluate(equation, variables)
		context.source.sendFeedback(
			formatEquation("$equation = ")
				.append(LiteralText(result.toString()).formatted(Formatting.YELLOW))
		)
		variables.set("ans", result)
		return 1
	}

	fun distTo(context: CommandContext<CottonClientCommandSource>, x: Double, y: Double, z: Double): Int {
		val dist = MinecraftClient.getInstance().player?.pos?.distanceTo((Vec3d(x, y, z)))?.toInt()
		context.source.sendFeedback(
			LiteralText("That's ")
				.append(LiteralText(dist.toString()).formatted(Formatting.YELLOW))
				.append(LiteralText(" blocks away from your current position"))
		)
		return 1
	}

	fun asStacks(context: CommandContext<CottonClientCommandSource>, itemCount: Int): Int {
		val out = "64 * " + (itemCount / 64).toString() + " + " + (itemCount % 64).toString()
		context.source.sendFeedback(
			LiteralText("That's ")
				.append(LiteralText(out).formatted(Formatting.YELLOW))
				.append(LiteralText(" items"))
		)
		return 1
	}

	fun addVariable(context: CommandContext<CottonClientCommandSource>, variableName: String, value: Double): Int {
		variables.set(variableName, value)
		context.source.sendFeedback(
			LiteralText(variableName).formatted(Formatting.AQUA)
				.append(LiteralText(" = ").formatted(Formatting.WHITE))
				.append(LiteralText(value.toString()).formatted(Formatting.YELLOW))
		)
		return 1
	}

	fun clearVariables(context: CommandContext<CottonClientCommandSource>): Int {
		variables = StaticVariableSet()
		return 1
	}

	fun formatEquation(equation: String): LiteralText {
		val text = LiteralText("")
		for (char in equation) {
			when {
				char.isLetter() -> {
					text.append(LiteralText(char.toString()).formatted(Formatting.AQUA))
				}
				char in "+-/*^" -> {
					text.append(LiteralText(char.toString()).formatted(Formatting.GREEN))
				}
				char in "()" -> {
					text.append(LiteralText(char.toString()).formatted(Formatting.LIGHT_PURPLE))
				}
				else -> {
					text.append(LiteralText(char.toString()))
				}
			}
		}
		return text
	}
}
