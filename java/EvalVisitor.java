import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.misc.NotNull;
// import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public class EvalVisitor implements ArithmeticVisitor<Integer> {
	Map<String, Integer> memory = new HashMap<String, Integer>();

	public Integer visitAssignment(@NotNull ArithmeticParser.AssignmentContext ctx) {
		String id = ctx.ID().getText();
		int value = visit(ctx.exp());
		memory.put(id, value);

		return value;
	}

	public Integer visitProgram(@NotNull ArithmeticParser.ProgramContext ctx){


	}

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

	}

	public Integer visitArithExp(@NotNull ArithmeticParser.ArithExpContext ctx) {
		Integer left = visit(ctx.exp(0));
		Integer right = visit(ctx.exp(1));

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
	// }
}
