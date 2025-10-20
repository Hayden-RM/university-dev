/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 *
 * Test:
 * - Reads in and executes on a URM the free-text example program given above
 * - Reads in and executes on a URM the free-text any program which does not
 *   terminate (but does not crash)
 *
 **/

import myGrammarURM.*;
import java.io.IOException;
import java.nio.file.*;
import org.antlr.v4.runtime.*;

public class Test {

    public static void main(String[] args) {

        String src;
        if (args.length == 0) {

            // Provided Test * Testing URM + specificity such as case insensitivity
            runTest("Provided test : Expectation -> regs[2,9,2]", """
                                                                            conFig[9,7]
                                                                            JUMP(1,2,6)
                                                                            succ(2)
                                                                            SUCc(3)
                                                                            jump(1,2,6)
                                                                            JuMp(1,1,2)
                                                                            TrAnSfEr(3,1)
                                                                            """ );

            // Non-terminating test * Testing a non-terminating URM that does not crash
            runTest("Non-terminating test : Expectation -> does not terminate (but does not crash)", """
                                                                                                                succ(1)
                                                                                                                jump(1,1,1)
                                                                                                                """);



        } else {
            try {
                src = Files.readString(Path.of(args[0]));
            } catch (IOException e) {
                System.err.println("Failed to read file: " + e.getMessage());
                return;
            }
        }

    }

    // Test helper
    private static void runTest(String testName, String src){
        System.out.println(" ~~~~~~~ Running " + testName + " ~~~~~~~");
        try{
            URMLexer lex = new URMLexer(CharStreams.fromString(src));
            URMParser parser = new URMParser(new CommonTokenStream(lex));
            URMParser.ProgramContext tree = parser.program();

            URMBuilder b = new URMBuilder();
            URMachine m = b.build(tree);
            System.out.println("program size = " + m.size());

            // m.run() : Trace : is set 'true' by default
            // m.run() : Step limit : 'Long.MAX_VALUE' by default, set manually in line below for step limit.
            m.run();
            System.out.println(m);
            System.out.println("\n");

        }catch(Exception e){
            System.err.println("Test failed with exception: " + e.getMessage());
            System.out.println("\n");

        }
    }


}
