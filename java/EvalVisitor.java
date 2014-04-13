import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.misc.NotNull;
// import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.*;

public class EvalVisitor extends ArithmeticBaseVisitor<Integer> {
	Map<String, Integer> memory = new HashMap<String, Integer>();

	public Integer visitAssignment(@NotNull ArithmeticParser.AssignmentContext ctx) {
		String id = ctx.ID().getText();
		int value = ctx.exp().accept(this);
		memory.put(id, value);

		return value;
	}

	// public Integer visitProgram(@NotNull ArithmeticParser.ProgramContext ctx){
	// 	// for (ArithmeticParser.ProgramContext context :
	// 	System.out.println("Visiting a program.");
	// 	return 0;
	// }


	public Integer visitIdExp(@NotNull ArithmeticParser.IdExpContext ctx){
		String id = ctx.ID().getText();

		if (memory.containsKey(id)) {
			return memory.get(id);
		} else {
			System.out.println("Undefined variable: " + id);
			return 0;
		}
	}

	public Integer visitOutput(@NotNull ArithmeticParser.OutputContext ctx) {
		String id = ctx.ID().getText();

		if (memory.containsKey(id)) {
			System.out.println(memory.get(id));
		} else {
			System.out.println("Undefined variable: " + id);
		}
		return 0;
	}

	public Integer visitArithExp(@NotNull ArithmeticParser.ArithExpContext ctx) {
		Integer left = ctx.exp(0).accept(this);
		Integer right = ctx.exp(1).accept(this);

		switch (ctx.op.getType()) {
		case ArithmeticParser.ADD:
			return left * right;
		case ArithmeticParser.SUB:
			return left - right;
		case ArithmeticParser.DIV:
			return left / right;
		case ArithmeticParser.MUL:
			return left * right;
		default:
			System.out.println("Unrecognized operator: " + ctx.op.getText());
			System.out.printf("In expession: %d %s %d\n", left, ctx.op.getText(), right);
			return 0;
		}
	}

	public Integer visitIntExp(@NotNull ArithmeticParser.IntExpContext ctx){
		return Integer.valueOf(ctx.INT().getText());
	}

	// public Integer visitErrorNode(ErrorNode e){
	// 	System.out.println("Something went wrong.");
	// 	return 0;
	// }

	// public Integer visitTerminal(TerminalNode t) {
	// 	System.out.println("Visiting a terminal node.");
	// 	return 0;
	// }

	// public Integer visitChildren(RuleNode r) {
	// 	System.out.println("Visiting children.");
	// 	return 0;
	// }


	// public Integer visit(ParseTree t) {
	// 	System.out.println("Visiting a tree.");

	// 	return 0;
	// }
}
