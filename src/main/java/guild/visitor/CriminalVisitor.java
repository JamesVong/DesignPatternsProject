package guild.visitor;

import guild.criminal.Criminal;
import guild.criminal.Gang;

public interface CriminalVisitor {
    void visit(Criminal criminal);
    void visit(Gang gang);
}
