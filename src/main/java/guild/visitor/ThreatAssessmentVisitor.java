package guild.visitor;

import guild.criminal.Criminal;
import guild.criminal.Gang;

public class ThreatAssessmentVisitor implements CriminalVisitor {
    private int assessedThreat = 0;

    public int getAssessedThreat() {
        return assessedThreat;
    }

    @Override
    public void visit(Criminal criminal) {
        this.assessedThreat = criminal.getThreatLevel();  // basic version
    }

    @Override
    public void visit(Gang gang) {
        this.assessedThreat = gang.getTotalThreatLevel(); // aggregate threat
    }
}
