import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class ArithmeticInterpreter {
	public static void main(String[] args) throws Exception {

		for(String path : args){
			System.out.println("Generating parse tree for: " + path);

			ANTLRInputStream input = new ANTLRFileStream(path);

			ArithmeticLexer lexer = new ArithmeticLexer(input);

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			ArithmeticParser parser = new ArithmeticParser(tokens);

			ParseTree tree = parser.program(); // begin parsing at init rule
			System.out.println(tree.toStringTree(parser)); // print LISP-style tree

			System.out.println("Interpreted output: ");

			EvalVisitor eval = new EvalVisitor();

			eval.visit(tree);


			System.out.println("-------------------------------------");
		}
	}
}
